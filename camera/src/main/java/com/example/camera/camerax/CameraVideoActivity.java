package com.example.camera.camerax;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Surface;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.video.MediaStoreOutputOptions;
import androidx.camera.video.Quality;
import androidx.camera.video.QualitySelector;
import androidx.camera.video.Recorder;
import androidx.camera.video.Recording;
import androidx.camera.video.VideoCapture;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.camera.R;
import com.google.common.util.concurrent.ListenableFuture;
import com.wang.logtools.KLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class CameraVideoActivity extends AppCompatActivity {
    private String TAG = CameraVideoActivity.class.getSimpleName();
    private final String FILENAME = "yyyy-MM-dd-HH-mm-ss-SSS";
    private ProcessCameraProvider mProcessCameraProvider;
    private Recording mRecording;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_video);

        // 加入PreviewView到布局
        PreviewView preview_view = findViewById(R.id.preview_view);

        // 创建预览
        Preview preview = new Preview.Builder()
                .setTargetRotation(Surface.ROTATION_0)
                .build();
        // 设置预览的输出
        preview.setSurfaceProvider(preview_view.getSurfaceProvider());

        ArrayList<Quality> qualities = new ArrayList<>();
        qualities.add(Quality.UHD);
        qualities.add(Quality.FHD);
        qualities.add(Quality.HD);
        qualities.add(Quality.SD);
        QualitySelector qualitySelector = QualitySelector.fromOrderedList(qualities);

        KLog.d(TAG, "qualitySelector: " + qualitySelector);

        Recorder recorder = new Recorder.Builder().setQualitySelector(qualitySelector).build();


        String name = "CameraX-recording-" + new SimpleDateFormat(FILENAME, Locale.US).format(System.currentTimeMillis()) + ".mp4";
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Video.Media.DISPLAY_NAME, name);
        MediaStoreOutputOptions mediaStoreOutput = new MediaStoreOutputOptions
                .Builder(CameraVideoActivity.this.getContentResolver(), MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
                .setContentValues(contentValues)
                .build();

        VideoCapture<Recorder> videoCapture = VideoCapture.withOutput(recorder);
        videoCapture.setTargetRotation(Surface.ROTATION_0);

        // 绑定生命周期
        ListenableFuture<ProcessCameraProvider> cameraProviderListenableFuture = ProcessCameraProvider.getInstance(this);
        try {
            mProcessCameraProvider = cameraProviderListenableFuture.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        mProcessCameraProvider.bindToLifecycle(CameraVideoActivity.this, CameraSelector.DEFAULT_BACK_CAMERA, preview, videoCapture);

        findViewById(R.id.stop_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mRecording.stop();
            }
        });

        findViewById(R.id.video).setOnClickListener(view -> {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mRecording = videoCapture.getOutput()
                    .prepareRecording(CameraVideoActivity.this, mediaStoreOutput)
                    .withAudioEnabled()
                    .start(ContextCompat.getMainExecutor(CameraVideoActivity.this), videoRecordEvent -> {
                        //  VideoRecordEvent.EVENT_TYPE_STATUS 用于录制统计信息，例如当前文件的大小和录制的时间跨度。
                        //  VideoRecordEvent.EVENT_TYPE_FINALIZE 用于录制结果，会包含最终文件的 URI 以及任何相关错误等信息
                        KLog.d(TAG, "RecordingStats :" + videoRecordEvent.getRecordingStats());
                        KLog.d(TAG, "DurationLimitMillis :" + videoRecordEvent.getOutputOptions().getDurationLimitMillis());
                        KLog.d(TAG, "Location :" + videoRecordEvent.getOutputOptions().getLocation());
                        KLog.d(TAG, "FileSizeLimit :" + videoRecordEvent.getOutputOptions().getFileSizeLimit());

                    });

        });
    }
}