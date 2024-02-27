package com.example.camera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.camera.camera1.CameraOneActivity;
import com.example.camera.camera2.CameraTwoActivity;
import com.example.camera.camerax.CameraXActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isCameraPermission()) {
            Toast.makeText(this, "已经有相机权限", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "请求相机权限", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 100);
        }

        if (isWritePermission()) {
            Toast.makeText(this, "已经有读写权限", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "请求有读写权限", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_MEDIA_IMAGES,
                    Manifest.permission.READ_MEDIA_AUDIO,
                    Manifest.permission.READ_MEDIA_VIDEO
            }, 100);
        }

        findViewById(R.id.camera1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CameraOneActivity.class));
            }
        });

        findViewById(R.id.camera2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CameraTwoActivity.class));
            }
        });
        findViewById(R.id.camerax).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CameraXActivity.class));
            }
        });


//        PermissionManager permissionManager = new PermissionManager(this);
//        permissionManager.requestPermission(Manifest.permission.RECORD_AUDIO, new IPermissionCallBack() {
//            @Override
//            public void success() {
//
//            }
//
//            @Override
//            public void fail() {
//
//            }
//
//            @Override
//            public void noMoreReminders() {
//
//            }
//
//            @Override
//            public void alreadyObtainedPermission() {
//
//            }
//        });

    }


    private boolean isCameraPermission() {
        return ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean isWritePermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }



}