package com.example.camera.camera2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.camera.R;

public class CameraTwoActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = CameraTwoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_two);

        Button surface_view_activity = findViewById(R.id.surface_view_activity);
        Button texture_view_activity = findViewById(R.id.texture_view_activity);

        surface_view_activity.setOnClickListener(this);
        texture_view_activity.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.surface_view_activity:
                startActivity(new Intent(CameraTwoActivity.this, SurfaceViewActivity.class));
                break;
            case R.id.texture_view_activity:
                startActivity(new Intent(CameraTwoActivity.this, TextureViewActivity.class));
                break;
        }
    }
}