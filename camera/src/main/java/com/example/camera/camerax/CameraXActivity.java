package com.example.camera.camerax;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.camera.R;

public class CameraXActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_xactivity);


        Button camera_controller = findViewById(R.id.camera_controller);
        Button camera_provider = findViewById(R.id.camera_provider);
        Button camera_video = findViewById(R.id.camera_video);
        Button multiple_camera = findViewById(R.id.multiple_camera);
        Button qr_code = findViewById(R.id.qr_code);
        Button faces = findViewById(R.id.faces);

        camera_controller.setOnClickListener(this);
        camera_provider.setOnClickListener(this);
        camera_video.setOnClickListener(this);
        multiple_camera.setOnClickListener(this);
        qr_code.setOnClickListener(this);
        faces.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.camera_controller:
                startActivity(new Intent(CameraXActivity.this, CameraControllerActivity.class));
                break;
            case R.id.camera_provider:
                startActivity(new Intent(CameraXActivity.this, CameraProviderActivity.class));
                break;
            case R.id.camera_video:
                startActivity(new Intent(CameraXActivity.this, CameraVideoActivity.class));
                break;
            case R.id.multiple_camera:
                startActivity(new Intent(CameraXActivity.this, MultipleCamerasActivity.class));
                break;
            case R.id.qr_code:
                startActivity(new Intent(CameraXActivity.this, QRCodeActivity.class));
                break;
            case R.id.faces:
                startActivity(new Intent(CameraXActivity.this, FacesActivity.class));
                break;
        }
    }
}