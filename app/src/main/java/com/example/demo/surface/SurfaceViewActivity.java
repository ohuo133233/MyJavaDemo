package com.example.demo.surface;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.R;
import com.example.demo.path.PathView;

public class SurfaceViewActivity extends AppCompatActivity {
    protected PathView mSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);

        mSurfaceView = findViewById(R.id.path_surface);
//        SurfaceView surfaceView = findViewById(R.id.surface_view);



    }


}