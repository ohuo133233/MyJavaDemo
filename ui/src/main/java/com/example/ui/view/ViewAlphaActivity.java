package com.example.ui.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ui.R;

public class ViewAlphaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_alpha);

        ImageView image = findViewById(R.id.image);
        Button start = findViewById(R.id.start);
        start.setOnClickListener(v -> {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
            alphaAnimation.setDuration(5000);
            image.startAnimation(alphaAnimation);
        });
    }
}