package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.demo.base.BaseActivity;
import com.example.demo.path.PathActivity;
import com.example.demo.surface.SurfaceViewActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        View path_view = findViewById(R.id.path_view);
        View surface_view = findViewById(R.id.surface_view);

        path_view.setOnClickListener(this);
        surface_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.surface_view:
                startActivity(new Intent(MainActivity.this, SurfaceViewActivity.class));
                break;
            case R.id.path_view:
                startActivity(new Intent(MainActivity.this, PathActivity.class));
                break;
        }
    }
}