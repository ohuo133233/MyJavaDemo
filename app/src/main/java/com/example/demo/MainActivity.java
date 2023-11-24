package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.demo.base.BaseActivity;
import com.example.demo.path.PathActivity;
import com.example.demo.surface.SurfaceViewActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private String TAG=MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        integers.add(7);
        integers.add(8);

        integers = integers.subList(0, 8);

        Log.d(TAG, "integers: " + integers);

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