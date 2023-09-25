package com.example.camera.camerax;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.example.camera.R;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

public class MultipleCamerasActivity extends AppCompatActivity {
    private PreviewView mFrontPreviewView;
    private PreviewView mBackPreviewView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_cameras);

        mFrontPreviewView     = findViewById(R.id.front_preview_View);
        mBackPreviewView  = findViewById(R.id.back_preview_View);

        startCameras();
    }


    private void startCameras( ) {


        Preview frontPreview = new Preview.Builder().build();
        Preview backPreview = new Preview.Builder().build();

        frontPreview.setSurfaceProvider(mFrontPreviewView.getSurfaceProvider());
        backPreview.setSurfaceProvider(mBackPreviewView.getSurfaceProvider());

        // 创建前置摄像头的 CameraSelector
        CameraSelector cameraSelectorFront = new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_FRONT).build();

        // 创建后置摄像头的 CameraSelector
        CameraSelector cameraSelectorBack = new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build();

        ListenableFuture<ProcessCameraProvider> FrontcameraProviderFuture = ProcessCameraProvider.getInstance(this);
//        ListenableFuture<ProcessCameraProvider> BackcameraProviderFuture = ProcessCameraProvider.getInstance(this);

        FrontcameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = FrontcameraProviderFuture.get();
//                ProcessCameraProvider cameraProvider1 = BackcameraProviderFuture.get();
                Camera cameraFront = cameraProvider.bindToLifecycle((LifecycleOwner) this, cameraSelectorFront, frontPreview);
                Camera cameraBack = cameraProvider.bindToLifecycle((LifecycleOwner) this, cameraSelectorBack, backPreview);
            } catch (ExecutionException | InterruptedException e) {
                // 处理异常
            }
        }, ContextCompat.getMainExecutor(this));





    }
}