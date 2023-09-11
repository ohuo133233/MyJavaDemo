package com.example.camera.camerax;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.extensions.ExtensionsManager;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import com.example.camera.utils.PoorFrameTracker;
import com.example.camera.R;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class CameraProviderActivity extends AppCompatActivity {
    private Camera mCamera;
    private ProcessCameraProvider mProcessCameraProvider;
    private String TAG = CameraProviderActivity.class.getSimpleName();
    private final String FILENAME = "yyyy-MM-dd-HH-mm-ss-SSS";
    private final String PHOTO_TYPE = "image/jpeg";
    private ImageCapture mImageCapture;
    private ImageCapture.OutputFileOptions outputFileOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_provider);
        // 加入PreviewView到布局
        PreviewView preview_view = findViewById(R.id.preview_view);
        // 获取ProcessCameraProvider  List
        ListenableFuture<ProcessCameraProvider> cameraProviderListenableFuture = ProcessCameraProvider.getInstance(this);
        // 创建预览
        Preview preview = new Preview.Builder().build();
        // 设置预览的输出
        preview.setSurfaceProvider(preview_view.getSurfaceProvider());
        // 设置摄像头
        CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;


        PoorFrameTracker.getInstance().startTrack();


        mImageCapture = new ImageCapture.Builder()
                .setTargetRotation(Surface.ROTATION_0)
                .build();


        mImageCapture = new ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .setTargetRotation(Surface.ROTATION_0)
                .build();


        try {
            mProcessCameraProvider = cameraProviderListenableFuture.get();
            mCamera = mProcessCameraProvider.bindToLifecycle(this, cameraSelector, preview, mImageCapture);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }


        findViewById(R.id.take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                takePicture();
            }
        });
        findViewById(R.id.flash).setOnClickListener(view -> {
            //  FLASH_MODE_OFF
            //  FLASH_MODE_ON
            //  FLASH_MODE_AUTO

            mImageCapture = new ImageCapture.Builder()
                    .setTargetRotation(Surface.ROTATION_0)
                    .setFlashMode(ImageCapture.FLASH_MODE_AUTO)
                    .build();
        });

        findViewById(R.id.capture_mode).setOnClickListener(view -> {
            // CAPTURE_MODE_MINIMIZE_LATENCY：缩短图片拍摄的延迟时间。
            // CAPTURE_MODE_MAXIMIZE_QUALITY：提高图片拍摄的图片质量。

            // CAPTURE_MODE_ZERO_SHOT_LAG：零快门延迟。
            // isZslSupported可用于查询设备是否支持该模式。但是，此模式还取决于用例配置和闪存模式设置。如果 VideoCapture 已绑定或闪光灯模式未关闭或 OEM 扩展已打开，则此模式将自动禁用。

            mImageCapture = new ImageCapture.Builder()
                    .setTargetRotation(Surface.ROTATION_0)
                    .setFlashMode(ImageCapture.FLASH_MODE_AUTO)
                    .build();
        });

        ListenableFuture<ExtensionsManager> instanceAsync = ExtensionsManager.getInstanceAsync(this, mProcessCameraProvider);


    }


    private void takePicture() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            Log.d(TAG, "> Build.VERSION_CODES.P");
            ContentValues contentValues = new ContentValues();
            String filenameFormat = new SimpleDateFormat(FILENAME, Locale.US).format(System.currentTimeMillis());
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, filenameFormat);
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, PHOTO_TYPE);
            String appName = getResources().getString(R.string.app_name);
            contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/" + appName);
            Log.d(TAG, "contentValues: " + contentValues);
            outputFileOptions = new ImageCapture.OutputFileOptions.Builder(getContentResolver(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues).build();
        } else {
            Log.d(TAG, "< Build.VERSION_CODES.P");
            File file = new File(getCacheDir().getPath() + File.separator + System.currentTimeMillis() + ".jpg");
            outputFileOptions = new ImageCapture.OutputFileOptions.Builder(file).build();
        }

        mImageCapture.takePicture(outputFileOptions, ContextCompat.getMainExecutor(this), new ImageCapture.OnImageSavedCallback() {
            @Override
            public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {

                ImageView imageView = findViewById(R.id.image);
                Uri savedUri = outputFileResults.getSavedUri();
                Log.d(TAG, "savedUri： " + savedUri);

                // 通过 ContentResolver 打开图像 Uri 并获取输入流
                try {
                    InputStream inputStream = getContentResolver().openInputStream(savedUri);

                    if (inputStream != null) {
                        // 将输入流转换为 Bitmap
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        // 使用 bitmap 进行后续的图像处理，比如显示在 ImageView 中
                        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
                        // toBitmap 只有1.3.0-alpha04才有
                        imageView.post(() -> imageView.setImageBitmap(scaledBitmap));

                        // 最后别忘了关闭 inputStream 和回收 bitmap
                        inputStream.close();
                        bitmap.recycle();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {
                Log.d(TAG, "onError： " + exception);
            }
        });

    }


}