package com.example.camera.camera1;

import android.hardware.Camera;
import android.view.SurfaceHolder;

import com.example.camera.ICamera;
import com.wang.logtools.KLog;

import java.io.IOException;

public class Camera1 implements ICamera {
    private static final String TAG = Camera1.class.getSimpleName();
    private Camera mCamera;

    @Override
    public void open() {
        int numberOfCameras = Camera.getNumberOfCameras();
        KLog.d(TAG, "open numberOfCameras :" + numberOfCameras);
        mCamera = Camera.open();
    }

    @Override
    public void coles() {
        mCamera.stopPreview();
        mCamera.release();
    }

    @Override
    public void setDisplayOrientation(int orientation) {

    }

    @Override
    public void setOrientation(int orientation) {

    }

    @Override
    public void setFlashMode(String value) {

    }

    @Override
    public int getCameraNumber() {
        return Camera.getNumberOfCameras();
    }

    @Override
    public Camera.Parameters getParameters() {
      return mCamera.getParameters();
    }

    @Override
    public void setParameters(Camera.Parameters params) {
        mCamera.setParameters(params);
    }

    @Override
    public void setPreviewDisplay(SurfaceHolder holder) throws IOException {
        mCamera.setPreviewDisplay(holder);
        mCamera.startPreview();
    }

    @Override
    public void stopPreview() {
        mCamera.stopPreview();
    }

    @Override
    public void setPreviewCallback(Camera.PreviewCallback previewCallback) {
   //     mCamera.setPreviewCallback(previewCallback);
    }

    @Override
    public void setCameraCallback(Camera.PreviewCallback cb) {
        mCamera.setPreviewCallbackWithBuffer(new Camera.PreviewCallback() {
            @Override
            public void onPreviewFrame(byte[] data, Camera camera) {
                KLog.d(TAG, "onPreviewFrame: " + data.length);
            }
        });
        mCamera.addCallbackBuffer(new byte[mCamera.getParameters().getPreviewSize().width * mCamera.getParameters().getPreviewSize().height]);
    }

    @Override
    public void takePicture() {
//        mCamera.takePicture(new Camera.ShutterCallback() {
//            @Override
//            public void onShutter() {
//
//            }
//        },)
    }
}
