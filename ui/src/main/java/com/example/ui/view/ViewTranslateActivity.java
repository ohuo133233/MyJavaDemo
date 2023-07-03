package com.example.ui.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ui.R;

public class ViewTranslateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_translate);

        ImageView image = findViewById(R.id.image);
        Button start = findViewById(R.id.start);
        start.setOnClickListener(v -> {
            TranslateAnimation translateAnimation= new TranslateAnimation(0,1000,0,1000);
            translateAnimation.setDuration(5000);
            image.startAnimation(translateAnimation);
        });
    }
}