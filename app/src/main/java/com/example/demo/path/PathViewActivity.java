package com.example.demo.path;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.demo.R;
import com.example.demo.base.BaseActivity;

public class PathViewActivity extends BaseActivity implements View.OnClickListener {
    private PathView mPathView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_view);

        mPathView = findViewById(R.id.path_view);

        Button triangle = findViewById(R.id.triangle);
        Button circular = findViewById(R.id.circular);
        Button clear = findViewById(R.id.clear);
        triangle.setOnClickListener(this);
        circular.setOnClickListener(this);
        clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.circular:
                mPathView.circular();
                break;
            case R.id.triangle:
                mPathView.triangle();
                break;
            case R.id.clear:
                mPathView.clear();
                break;
        }
    }
}