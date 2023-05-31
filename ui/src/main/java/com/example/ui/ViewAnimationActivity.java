package com.example.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ViewAnimationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);

        Button start_scale = findViewById(R.id.start_scale);


        start_scale.setOnClickListener((v -> {
            Intent intent = new Intent(this, ViewScaleActivity.class);
            startActivity(intent);
        }));


    }
}

