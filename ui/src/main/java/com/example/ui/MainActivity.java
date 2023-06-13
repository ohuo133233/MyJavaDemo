package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button start_view_animation = findViewById(R.id.start_view_animation);
        Button start_path = findViewById(R.id.start_path);
        Button start_property_animation = findViewById(R.id.start_property_animation);

        start_property_animation.setOnClickListener((v -> {
            Intent intent = new Intent(this, PropertyAnimationActivity.class);
            startActivity(intent);
        }));

        start_view_animation.setOnClickListener((v -> {
            Intent intent = new Intent(this, ViewAnimationActivity.class);
            startActivity(intent);
        }));

        start_path.setOnClickListener((v -> {
//            Intent intent = new Intent(this, ViewAnimationActivity.class);
//            startActivity(intent);
        }));


        Intent intent = new Intent(this, ViewAnimationActivity.class);
        startActivity(intent);
    }
}