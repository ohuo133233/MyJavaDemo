package com.example.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ui.R;

public class ViewAnimationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);

        Button start_scale = findViewById(R.id.start_scale);
        Button start_translate = findViewById(R.id.start_translate);
        Button start_alpha = findViewById(R.id.start_alpha);
        Button start_rotate = findViewById(R.id.start_rotate);


        start_scale.setOnClickListener((v -> {
            Intent intent = new Intent(this, ViewScaleActivity.class);
            startActivity(intent);
        }));

        start_translate.setOnClickListener((v -> {
            Intent intent = new Intent(this, ViewTranslateActivity.class);
            startActivity(intent);
        }));

        start_alpha.setOnClickListener((v -> {
            Intent intent = new Intent(this, ViewAlphaActivity.class);
            startActivity(intent);
        }));

        start_rotate.setOnClickListener((v -> {
            Intent intent = new Intent(this, ViewRotateActivity.class);
            startActivity(intent);
        }));


    }
}

