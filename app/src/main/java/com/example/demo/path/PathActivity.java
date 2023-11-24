package com.example.demo.path;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.demo.R;
import com.example.demo.base.BaseActivity;

public class PathActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);

        Button path_view = findViewById(R.id.path_view);
        Button touch_path_view = findViewById(R.id.touch_path_view);
        Button role_move_path = findViewById(R.id.role_move_path);

        path_view.setOnClickListener(this);
        touch_path_view.setOnClickListener(this);
        role_move_path.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.touch_path_view:
                startActivity(new Intent(PathActivity.this, TouchPathViewActivity.class));
                break;
            case R.id.role_move_path:
                startActivity(new Intent(PathActivity.this, RoleMovePathActivity.class));
                break;
        }
    }
}