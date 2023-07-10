package com.example.ui.interpolator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ui.R;

public class InterpolatorActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator);

        Button default_interpolator = findViewById(R.id.default_interpolator);
        Button custom_interpolator = findViewById(R.id.custom_interpolator);

        default_interpolator.setOnClickListener(this);
        custom_interpolator.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.default_interpolator:
                startActivity(new Intent(InterpolatorActivity.this, DefaultInterpolatorActivity.class));
                break;
            case R.id.custom_interpolator:
                startActivity(new Intent(InterpolatorActivity.this, CustomInterpolatorActivity.class));
                break;
        }
    }
}