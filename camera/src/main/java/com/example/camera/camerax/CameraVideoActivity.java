package com.example.camera.camerax;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
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
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class CameraVideoActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = CameraVideoActivity.class.getSimpleName();
    private final String FILENAME = "yyyy-MM-dd-HH-mm-ss-SSS";
    private ProcessCameraProvider mProcessCameraProvider;
    private Camera mCamera;
    private Recording mRecording;

    // 切换分辨率按钮
    private Button SD;
    private Button HD;
    private Button FHD;
    private Button UHD;

    private PreviewView mPreviewView;
    private Preview mPreview;

    private VideoCapture<Recorder> mVideoCapture;
    private Recorder mRecorder;
    private ArrayList<Quality> mQualities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_video);

        SD = findViewById(R.id.SD);
        HD = findViewById(R.id.HD);
        FHD = findViewById(R.id.FHD);
        UHD = findViewById(R.id.UHD);


        Button start_video = findViewById(R.id.start_video);
        Button stop_video = findViewById(R.id.stop_video);

        SD.setOnClickListener(this);
        HD.setOnClickListener(this);
        FHD.setOnClickListener(this);
        UHD.setOnClickListener(this);

        start_video.setOnClickListener(this);
        stop_video.setOnClickListener(this);


        // 加入PreviewView到布局
        mPreviewView = findViewById(R.id.preview_view);

        // 创建预览
        mPreview = new Preview.Builder().setTargetRotation(Surface.ROTATION_0).build();

        // 设置预览的输出
        mPreview.setSurfaceProvider(mPreviewView.getSurfaceProvider());


        // 绑定生命周期
        ListenableFuture<ProcessCameraProvider> cameraProviderListenableFuture = ProcessCameraProvider.getInstance(this);
        try {
            mProcessCameraProvider = cameraProviderListenableFuture.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        mCamera = mProcessCameraProvider.bindToLifecycle(CameraVideoActivity.this, CameraSelector.DEFAULT_BACK_CAMERA, mPreview);

        // 获取支持的分辨率
        List<Quality> supportedQualities = QualitySelector.getSupportedQualities(mCamera.getCameraInfo());
        // Quality.UHD，适用于 4K 超高清视频大小 (2160p)
        // Quality.FHD，适用于全高清视频大小 (1080p)
        // Quality.HD，适用于高清视频大小 (720p)
        // Quality.SD，适用于标清视频大小 (480p)
        KLog.d(TAG, "supportedQualities: " + supportedQualities);

        // 根据手机支持的分辨率设置按钮显示
        for (int i = 0; i < supportedQualities.size(); i++) {
            Quality quality = supportedQualities.get(i);
            if (quality == Quality.SD) {
                SD.setVisibility(View.VISIBLE);
            }
            if (quality == Quality.HD) {
                HD.setVisibility(View.VISIBLE);
            }
            if (quality == Quality.FHD) {
                FHD.setVisibility(View.VISIBLE);
            }
            if (quality == Quality.UHD) {
                UHD.setVisibility(View.VISIBLE);
            }
        }


        // 查询相机支持的最高录制分辨率；如果所有请求分辨率都不受支持，则授权 CameraX 选择最接近 Quality.SD 分辨率的分辨率
        QualitySelector qualitySelector = QualitySelector.fromOrderedList(supportedQualities);
        KLog.d(TAG, "qualitySelector: " + qualitySelector);
        Toast.makeText(this, "默认使用机器最大分辨率录制", Toast.LENGTH_SHORT).show();

        mRecorder = new Recorder.Builder()
                .setQualitySelector(qualitySelector)
                .build();


        mVideoCapture = VideoCapture.withOutput(mRecorder);
        mVideoCapture.setTargetRotation(Surface.ROTATION_0);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SD:
                updateQuality(Quality.SD);
                updateConfig();
                break;
            case R.id.HD:
                updateQuality(Quality.HD);
                updateConfig();
                break;
            case R.id.FHD:
                updateQuality(Quality.FHD);
                updateConfig();
                break;
            case R.id.UHD:
                updateQuality(Quality.UHD);
                updateConfig();
                break;
            case R.id.start_video:
                startVideo();
                break;
            case R.id.stop_video:
                mRecording.resume();
                break;
        }
    }

    public void startVideo() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            return;
        }


        String name = "CameraX-recording-" + new SimpleDateFormat(FILENAME, Locale.US).format(System.currentTimeMillis()) + ".mp4";
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Video.Media.DISPLAY_NAME, name);

        MediaStoreOutputOptions mediaStoreOutput = new MediaStoreOutputOptions.Builder(CameraVideoActivity.this.getContentResolver(),
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
                .setContentValues(contentValues)
                .build();

        mRecording = mVideoCapture.getOutput().prepareRecording(CameraVideoActivity.this, mediaStoreOutput)
                .withAudioEnabled()
                .start(ContextCompat.getMainExecutor(CameraVideoActivity.this), videoRecordEvent -> {
            //  VideoRecordEvent.EVENT_TYPE_STATUS 用于录制统计信息，例如当前文件的大小和录制的时间跨度。
            //  VideoRecordEvent.EVENT_TYPE_FINALIZE 用于录制结果，会包含最终文件的 URI 以及任何相关错误等信息
            KLog.d(TAG, "RecordingStats :" + videoRecordEvent.getRecordingStats());
            KLog.d(TAG, "DurationLimitMillis :" + videoRecordEvent.getOutputOptions().getDurationLimitMillis());
            KLog.d(TAG, "Location :" + videoRecordEvent.getOutputOptions().getLocation());
            KLog.d(TAG, "FileSizeLimit :" + videoRecordEvent.getOutputOptions().getFileSizeLimit());

        });

    }


    public void updateQuality(Quality quality) {
        mQualities.clear();
        mQualities.add(quality);
        KLog.d(TAG, "qualities: " + mQualities);
        // 查询相机支持的最高录制分辨率；如果所有请求分辨率都不受支持，则授权 CameraX 选择最接近 Quality.SD 分辨率的分辨率
        QualitySelector qualitySelector = QualitySelector.fromOrderedList(mQualities);
        mRecorder = new Recorder.Builder().setQualitySelector(qualitySelector).build();

        mVideoCapture = VideoCapture.withOutput(mRecorder);
    }

    public void updateConfig() {
        mCamera = mProcessCameraProvider.bindToLifecycle(CameraVideoActivity.this, CameraSelector.DEFAULT_BACK_CAMERA, mPreview, mVideoCapture);
    }


}