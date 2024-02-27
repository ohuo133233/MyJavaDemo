package com.example.camera.camerax;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Range;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.MeteringPoint;
import androidx.camera.core.Preview;
import androidx.camera.core.SurfaceOrientedMeteringPointFactory;
import androidx.camera.core.TorchState;
import androidx.camera.extensions.ExtensionsManager;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import com.example.camera.R;
import com.example.camera.utils.VerticalSeekBar;
import com.google.common.util.concurrent.ListenableFuture;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class CameraProviderActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = CameraProviderActivity.class.getSimpleName();

    private final String FILENAME = "yyyy-MM-dd-HH-mm-ss-SSS";
    private final String PHOTO_TYPE_JPG = "image/jpeg";
    private final String PHOTO_TYPE_RAW = "image/raw";

    private Camera mCamera;
    private ProcessCameraProvider mProcessCameraProvider;
    private ImageCapture mImageCapture;
    private ImageCapture.OutputFileOptions outputFileOptions;
    private Preview mPreview;
    private CameraControl mCameraControl;
    private CameraInfo mCameraInfo;
    private PreviewView mPreviewView;

    // 是否前置摄像头
    private boolean mIsFrontCamera;

    private AppCompatSeekBar mAppCompatSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_provider);

        initView();

        // 加入PreviewView到布局
        mPreviewView = findViewById(R.id.preview_view);


        // 创建预览
        mPreview = new Preview.Builder()
                .build();

        // 设置预览的输出
        mPreview.setSurfaceProvider(mPreviewView.getSurfaceProvider());

        // 设置摄像头
        CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;

//        PoorFrameTracker.getInstance().startTrack();


        mImageCapture = new ImageCapture.Builder()
                .build();

        bingCamera(cameraSelector);

        final ListenableFuture extensionsManagerFuture =
                ExtensionsManager.getInstanceAsync(getApplicationContext(), mProcessCameraProvider);


//        extensionsManagerFuture.addListener(() -> {
//
//            try {
//                final ExtensionsManager extensionsManager = (ExtensionsManager) extensionsManagerFuture.get();
//                boolean extensionAvailable = extensionsManager.isExtensionAvailable(cameraSelector, ExtensionMode.NIGHT);
//                KLog.d(TAG,"extensionAvailable ：" + extensionAvailable);
//                if (extensionAvailable) {
//                    Toast.makeText(this, "extensionAvailable ：" + extensionAvailable, Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "extensionAvailable ：" + extensionAvailable, Toast.LENGTH_SHORT).show();
//                }
//            } catch (ExecutionException | InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }, ContextCompat.getMainExecutor(this));

        zoom();
        meteringPoint();
    }

    private void zoom() {
        // 获取最大缩放值
        float maxZoomRatio = mCameraInfo.getZoomState().getValue().getMaxZoomRatio();
        // 获取最小缩放值
        float minZoomRatio = mCameraInfo.getZoomState().getValue().getMinZoomRatio();
        Log.d(TAG, "maxZoomRatio: " + maxZoomRatio);
        Log.d(TAG, "minZoomRatio: " + minZoomRatio);

        VerticalSeekBar vertical_seek_bar = findViewById(R.id.vertical_seek_bar);
//        mCameraControl.setZoomRatio()
        vertical_seek_bar.setMax((int) maxZoomRatio);
        vertical_seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "onProgressChanged  progress:" + progress + " , fromUser: " + fromUser);
                // 设置相机的缩放
                mCameraControl.setZoomRatio(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStopTrackingTouch");
            }
        });

    }

    /**
     * 对焦
     */
    private void meteringPoint() {
        mPreviewView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                mPreviewView.post(() -> Toast.makeText(CameraProviderActivity.this, "触发点击聚焦", Toast.LENGTH_SHORT).show());

                float x = event.getX();
                float y = event.getY();
                SurfaceOrientedMeteringPointFactory surfaceOrientedMeteringPointFactory = new SurfaceOrientedMeteringPointFactory(mPreviewView.getWidth(), mPreviewView.getHeight());
                MeteringPoint point = surfaceOrientedMeteringPointFactory.createPoint(x, y);
                FocusMeteringAction action = new FocusMeteringAction.Builder(point).build();
                mCameraControl.startFocusAndMetering(action);
            }
            return false;
        });
    }


    private void bingCamera(CameraSelector cameraSelector) {
        // 获取ProcessCameraProvider  List
        ListenableFuture<ProcessCameraProvider> cameraProviderListenableFuture = ProcessCameraProvider.getInstance(this);
        try {
            mProcessCameraProvider = cameraProviderListenableFuture.get();
            mCamera = mProcessCameraProvider.bindToLifecycle(this, cameraSelector, mPreview, mImageCapture);
            mCameraControl = mCamera.getCameraControl();
            mCameraInfo = mCamera.getCameraInfo();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private void initView() {
        ImageButton settings = findViewById(R.id.settings);

        ImageButton take_photo = findViewById(R.id.take_photo);
        ImageButton flash_no = findViewById(R.id.flash_no);
        ImageButton flash_off = findViewById(R.id.flash_off);
        ImageButton flash_auto = findViewById(R.id.flash_auto);
        ImageButton switch_camera = findViewById(R.id.switch_camera);
        ImageButton capture_mode = findViewById(R.id.capture_mode);
        ImageButton torch = findViewById(R.id.torch);

        mAppCompatSeekBar = findViewById(R.id.seek_bar);
        TextView iso = findViewById(R.id.iso);
        TextView ev = findViewById(R.id.ev);

        settings.setOnClickListener(this);

        take_photo.setOnClickListener(this);
        flash_no.setOnClickListener(this);
        flash_off.setOnClickListener(this);
        flash_auto.setOnClickListener(this);
        switch_camera.setOnClickListener(this);
        capture_mode.setOnClickListener(this);
        torch.setOnClickListener(this);

        iso.setOnClickListener(this);
        ev.setOnClickListener(this);
    }


    private void takePicture() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            Log.d(TAG, "> Build.VERSION_CODES.P");
            ContentValues contentValues = new ContentValues();
            String filenameFormat = new SimpleDateFormat(FILENAME, Locale.US).format(System.currentTimeMillis());
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, filenameFormat);
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, PHOTO_TYPE_JPG);
            String appName = getResources().getString(R.string.app_name);
            contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/" + appName);
            Log.d(TAG, "contentValues: " + contentValues);
            outputFileOptions = new ImageCapture.OutputFileOptions.Builder(getContentResolver(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues).build();
        } else {
            Log.d(TAG, "< Build.VERSION_CODES.P");
            File file = new File(getCacheDir().getPath() + File.separator + System.currentTimeMillis() + ".jpg");
            outputFileOptions = new ImageCapture.OutputFileOptions.Builder(file)
                    .build();
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


    public void updateCamera() {
        CameraSelector cameraSelector;
        if (mIsFrontCamera) {
            mIsFrontCamera = false;
            cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;
        } else {
            mIsFrontCamera = true;
            cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA;
        }
        mProcessCameraProvider.unbindAll();
        bingCamera(cameraSelector);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.take_photo:
                takePicture();
                break;
            case R.id.flash_no:
                mImageCapture = new ImageCapture.Builder().setTargetRotation(Surface.ROTATION_0).setFlashMode(ImageCapture.FLASH_MODE_ON).build();
                break;
            case R.id.flash_off:
                mImageCapture = new ImageCapture.Builder().setTargetRotation(Surface.ROTATION_0).setFlashMode(ImageCapture.FLASH_MODE_OFF).build();
                break;
            case R.id.flash_auto:
                mImageCapture = new ImageCapture.Builder().setTargetRotation(Surface.ROTATION_0).setFlashMode(ImageCapture.FLASH_MODE_AUTO).build();
                break;
            case R.id.switch_camera:
                updateCamera();
                break;
            case R.id.iso:
                mAppCompatSeekBar.setVisibility(View.VISIBLE);
                break;
            case R.id.ev:
                // 判断摄像头是否支持调节EV曝光补偿
                boolean exposureCompensationSupported = mCameraInfo.getExposureState().isExposureCompensationSupported();
                if (!exposureCompensationSupported) {
                    Toast.makeText(this, "当前摄像头不支持EV曝光补偿的调节", Toast.LENGTH_SHORT).show();
                }
                mAppCompatSeekBar.setVisibility(View.VISIBLE);
                // 当前EV曝光补偿的值
                int exposureCompensationIndex = mCameraInfo.getExposureState().getExposureCompensationIndex();
                // 当前支持EV曝光补偿的范围
                Range<Integer> exposureCompensationRange = mCameraInfo.getExposureState().getExposureCompensationRange();

                Log.d(TAG, "exposureCompensationIndex： " + exposureCompensationIndex);
                Log.d(TAG, "exposureCompensationRange： " + exposureCompensationRange);


                mAppCompatSeekBar.setMax(8);
                mAppCompatSeekBar.setProgress(exposureCompensationIndex);

                mAppCompatSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        // 设置EV曝光补偿
                        mCameraControl.setExposureCompensationIndex(progress - 4);

                        int exposureCompensationIndex = mCameraInfo.getExposureState().getExposureCompensationIndex();
                        Log.d(TAG, "onProgressChanged exposureCompensationIndex： " + exposureCompensationIndex);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }


                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                break;
            case R.id.torch:
                boolean isHasFlashUnit = mCameraInfo.hasFlashUnit();
                Log.d(TAG, "手电筒是否可用： " + isHasFlashUnit);
                Integer value = mCameraInfo.getTorchState().getValue();
                Log.d(TAG, "手电筒状态：" + value);
                // 设置和当前相反的手电筒状态
                mCameraControl.enableTorch(value == TorchState.OFF);

                break;
            case R.id.capture_mode:
                // CAPTURE_MODE_MINIMIZE_LATENCY：缩短图片拍摄的延迟时间。
                // CAPTURE_MODE_MAXIMIZE_QUALITY：提高图片拍摄的图片质量。

                // CAPTURE_MODE_ZERO_SHOT_LAG：零快门延迟。
                // isZslSupported可用于查询设备是否支持该模式。但是，此模式还取决于用例配置和闪存模式设置。如果 VideoCapture 已绑定或闪光灯模式未关闭或 OEM 扩展已打开，则此模式将自动禁用。


                boolean zslSupported = mCamera.getCameraInfo().isZslSupported();
                Log.d(TAG, "是否支持0快门: " + zslSupported);
//                mImageCapture = new ImageCapture.Builder()
//                        .setTargetRotation(Surface.ROTATION_0)
//                        .setCaptureMode(ImageCapture.CAPTURE_MODE_ZERO_SHUTTER_LAG)
//                        .build();

                break;
        }
    }
}