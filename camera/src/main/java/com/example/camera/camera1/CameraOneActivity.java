package com.example.camera.camera1;

import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.camera.R;

public class CameraOneActivity extends AppCompatActivity implements View.OnClickListener {
    private Camera1 mCamera;
    private SurfaceView mPreviewSurfaceView;
    private SurfaceView mDataSurfaceView;
    private static final String TAG = CameraOneActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_one);

        mPreviewSurfaceView = findViewById(R.id.preview_surface_view);
        mDataSurfaceView = findViewById(R.id.data_surface_view);
        Button screen_switching = findViewById(R.id.screen_switching);

        screen_switching.setOnClickListener(this);

        mCamera = new Camera1();
//        mCamera.open();

//        mPreviewSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(@NonNull SurfaceHolder holder) {
//                try {
//                    mDataSurfaceView.setVisibility(View.GONE);
//                    mPreviewSurfaceView.setVisibility(View.VISIBLE);
//                    mCamera.setPreviewDisplay(mPreviewSurfaceView.getHolder());
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//            @Override
//            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
//
//            }
//
//            @Override
//            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
//
//            }

//        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.screen_switching:
                mDataSurfaceView.setVisibility(View.VISIBLE);
                mPreviewSurfaceView.setVisibility(View.GONE);
                break;
        }
    }



}