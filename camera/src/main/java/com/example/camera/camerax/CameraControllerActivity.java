package com.example.camera.camerax;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.view.LifecycleCameraController;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import com.example.camera.R;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.Executors;

public class CameraControllerActivity extends AppCompatActivity {
    private static final String TAG = CameraControllerActivity.class.getSimpleName();
    private LifecycleCameraController mCameraController;
    private PreviewView mPreviewView;
    private final String FILENAME = "yyyy-MM-dd-HH-mm-ss-SSS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_controller);

        // 加入PreviewView到布局
        mPreviewView = findViewById(R.id.preview_view);
        // 获取mCameraController
        mCameraController = new LifecycleCameraController(this);
        mCameraController.setCameraSelector(CameraSelector.DEFAULT_FRONT_CAMERA);
        // 绑定生命周期
        mCameraController.bindToLifecycle(this);
        // 和PreviewView绑定
        mPreviewView.setController(mCameraController);


        findViewById(R.id.take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                takePicture2();
            }
        });

    }


    private void takePicture1() {

        mCameraController.takePicture(ContextCompat.getMainExecutor(this), new ImageCapture.OnImageCapturedCallback() {
            @Override
            public void onCaptureSuccess(@NonNull ImageProxy image) {
                super.onCaptureSuccess(image);
                Log.d(TAG, "onCaptureSuccess");

                int format = image.getFormat();
                Log.d(TAG, "image.getFormat " + format);
                if (format == 256) {
                    // 在适当的位置，从 ImageProxy 获取 JPEG 数据
                    ImageProxy.PlaneProxy plane = image.getPlanes()[0];
                    ByteBuffer buffer = plane.getBuffer();
                    byte[] jpegData = new byte[buffer.remaining()];
                    buffer.get(jpegData);
                    // 将 JPEG 数据解码为 Bitmap
                    Bitmap bitmap = BitmapFactory.decodeByteArray(jpegData, 0, jpegData.length);
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
                    // 现在您可以使用生成的 Bitmap 进行后续操作
                    ImageView imageView = findViewById(R.id.image);
                    imageView.post(() -> imageView.setImageBitmap(scaledBitmap));
                }

            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {
                super.onError(exception);
                Log.d(TAG, "onError： " + exception);
            }
        });

    }

    private void takePicture2() {
        ContentValues contentValues = new ContentValues();
        String filenameFormat = new SimpleDateFormat(FILENAME, Locale.CHINA).format(System.currentTimeMillis());
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, filenameFormat);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
        String appName = getResources().getString(R.string.app_name);
        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/" + appName);

        Log.d(TAG, "contentValues: " + contentValues);

        ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.
                OutputFileOptions.Builder(getContentResolver(),
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues).
                build();

        mCameraController.takePicture(outputFileOptions, Executors.newSingleThreadExecutor(), new ImageCapture.OnImageSavedCallback() {
            @Override

            public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                Log.d(TAG, "onImageSaved");

//                ImageView imageView = findViewById(R.id.image);
//                File file = outputFileOptions.getFile();
//                Bitmap bitmap = BitmapFactory.decodeFile(file.getName());
//                // toBitmap 只有1.3.0-alpha04才有
//                imageView.post(() -> imageView.setImageBitmap(bitmap));
            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {
                Log.d(TAG, "onError： " + exception);
            }
        });

    }


}