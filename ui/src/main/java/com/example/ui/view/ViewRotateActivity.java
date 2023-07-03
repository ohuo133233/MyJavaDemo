package com.example.ui.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ui.R;

public class ViewRotateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rotate);


        ImageView image = findViewById(R.id.image);
        Button start = findViewById(R.id.start);
        start.setOnClickListener(v -> {
            RotateAnimation rotateAnimation = new RotateAnimation(1, 180);
            rotateAnimation.setDuration(5000);
            image.startAnimation(rotateAnimation);
        });
    }
}