package com.example.camera.camera2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.camera.R;
import com.wang.logtools.KLog;

import java.util.Arrays;

public class SurfaceViewActivity extends AppCompatActivity {
    private String TAG = SurfaceViewActivity.class.getSimpleName();
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private CameraManager mCameraManager;
    private Surface mSurface;
    private CaptureRequest.Builder captureRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);


        initView();


        mSurfaceHolder = mSurfaceView.getHolder();
        mSurface = mSurfaceHolder.getSurface();

        getCameraList();
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                KLog.d(TAG, "surfaceCreated");
                openCamera();
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                KLog.d(TAG, "surfaceDestroyed");
            }
        });
    }


    private void initView() {
        mSurfaceView = findViewById(R.id.surface_view);


    }


    public void getCameraList() {

        // 获取相机列表
        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String[] cameraIdList = mCameraManager.getCameraIdList();
            for (String s : cameraIdList) {
                Log.d(TAG, "cameraIdList: " + s);
            }
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }

    }


    private void openCamera() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        try {
            mCameraManager.openCamera("0", new CameraDevice.StateCallback() {
                @Override
                public void onOpened(@NonNull CameraDevice camera) {
                    KLog.d(TAG, "onOpened");
                    try {
                        captureRequest = camera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
                        captureRequest.addTarget(mSurfaceHolder.getSurface());

                        camera.createCaptureSession(Arrays.asList(mSurface), new CameraCaptureSession.StateCallback() {
                            @Override
                            public void onConfigured(@NonNull CameraCaptureSession session) {

                                try {
                                    captureRequest.set(CaptureRequest.CONTROL_AF_TRIGGER, CameraMetadata.CONTROL_AF_TRIGGER_CANCEL);
                                    session.setRepeatingRequest(captureRequest.build(), null, new Handler());
                                } catch (CameraAccessException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onConfigureFailed(@NonNull CameraCaptureSession session) {
                            }
                        }, new Handler());
                    } catch (CameraAccessException e) {
                        throw new RuntimeException(e);
                    }

                }

                @Override
                public void onDisconnected(@NonNull CameraDevice camera) {
                    KLog.d(TAG, "onDisconnected");
                }

                @Override
                public void onError(@NonNull CameraDevice camera, int error) {
                    KLog.e(TAG, "onError： " + error);
                }
            }, new Handler());
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
    }
}