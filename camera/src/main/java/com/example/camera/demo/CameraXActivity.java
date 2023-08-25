package com.example.camera.demo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import com.example.camera.R;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

public class CameraXActivity extends AppCompatActivity {
    private PreviewView mPreviewView;
    private ListenableFuture<ProcessCameraProvider> mCameraProviderFuture;
    private Camera mCamera;
    private boolean isOpenFlashlight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_xactivity);
        // 加入PreviewView到布局
        mPreviewView = findViewById(R.id.preview_view);

        // 获取CameraProvider
        mCameraProviderFuture = ProcessCameraProvider.getInstance(this);

        mCameraProviderFuture.addListener(() -> {
            ProcessCameraProvider cameraProvider = null;
            try {
                cameraProvider = mCameraProviderFuture.get();
                bindPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }

        }, ContextCompat.getMainExecutor(this));


        findViewById(R.id.open_flashlight).setOnClickListener(view -> {
            isOpenFlashlight = !isOpenFlashlight;
            mCamera.getCameraControl().enableTorch(isOpenFlashlight);
        });

        mPreviewView.setOnTouchListener((view, motionEvent) -> {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            FocusMeteringAction focusMeteringAction = new FocusMeteringAction.Builder(mPreviewView.getMeteringPointFactory()
                    .createPoint(x, y)).build();
            mCamera.getCameraControl().startFocusAndMetering(focusMeteringAction);
            return true;
        });


    }

    private void bindPreview(ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder()
                .build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        preview.setSurfaceProvider(mPreviewView.getSurfaceProvider());

        mCamera = cameraProvider.bindToLifecycle(this, cameraSelector, preview);
    }
}