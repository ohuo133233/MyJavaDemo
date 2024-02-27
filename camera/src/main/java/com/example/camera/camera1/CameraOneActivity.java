package com.example.camera.camera1;

import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.camera.R;

import java.io.IOException;

public class CameraOneActivity extends AppCompatActivity implements View.OnClickListener, SurfaceHolder.Callback, Camera.PreviewCallback {
    private static final String TAG = CameraOneActivity.class.getSimpleName();
    private SurfaceView mPreviewSurfaceView;
    private SurfaceHolder mSurfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_one);

        mPreviewSurfaceView = findViewById(R.id.preview_surface_view);


        Button screen_switching = findViewById(R.id.screen_switching);
        screen_switching.setOnClickListener(this);


        mSurfaceHolder = mPreviewSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);

    }
private Camera mCamera;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.screen_switching:
                break;
        }
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        mCamera  = Camera.open(0);

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        Camera.Parameters parameters = mCamera.getParameters();
        // 设置相机参数，例如预览尺寸、对焦模式等
        parameters.setPreviewSize(1920, 1080);
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        mCamera.setParameters(parameters);
        try {
            mCamera.setPreviewDisplay(mSurfaceHolder);
        } catch (IOException e) {
            Log.e(TAG, "e: " + e.getMessage());
        }

        mCamera.startPreview();
        mCamera.setPreviewCallback(this);
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        Log.d(TAG, "在这里处理预览帧数据");
    }
}