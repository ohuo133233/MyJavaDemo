package com.example.camera.camerax;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.camera.R;

public class CameraXActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_xactivity);


        findViewById(R.id.camera_controller).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CameraXActivity.this, CameraControllerActivity.class));
            }
        });

        findViewById(R.id.camera_provider).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CameraXActivity.this, CameraProviderActivity.class));
            }
        });
        findViewById(R.id.camera_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CameraXActivity.this, CameraVideoActivity.class));
            }
        });


    }

}