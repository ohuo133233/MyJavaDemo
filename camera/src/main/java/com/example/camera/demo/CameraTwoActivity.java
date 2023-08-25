package com.example.camera.demo;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.camera.R;

public class CameraTwoActivity extends AppCompatActivity {
    private String TAG = CameraTwoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_two);

        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String[] cameraIdList = cameraManager.getCameraIdList();
            for (int i = 0; i < cameraIdList.length; i++) {
                Log.d(TAG, "cameraIdList: " + cameraIdList[i]);
            }
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
    }
}