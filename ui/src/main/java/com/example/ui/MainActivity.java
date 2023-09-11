package com.example.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ui.demo.DemoAnimationActivity;
import com.example.ui.evaluator.EvaluatorActivity;
import com.example.ui.game.GameActivity;
import com.example.ui.gesture.GestureActivity;
import com.example.ui.interpolator.InterpolatorActivity;
import com.example.ui.property.PropertyAnimationActivity;
import com.example.ui.view.ViewAnimationActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button start_view_animation = findViewById(R.id.start_view_animation);
        Button start_gesture = findViewById(R.id.start_gesture);
        Button start_property_animation = findViewById(R.id.start_property_animation);
        Button start_game = findViewById(R.id.start_game);
        Button start_interpolator = findViewById(R.id.start_interpolator);
        Button start_evaluator = findViewById(R.id.start_evaluator);
        Button demo = findViewById(R.id.demo);
        Button slide_view_group = findViewById(R.id.slide_view_group);

        start_property_animation.setOnClickListener((v -> {
            Intent intent = new Intent(this, PropertyAnimationActivity.class);
            startActivity(intent);
        }));

        start_view_animation.setOnClickListener((v -> {
            Intent intent = new Intent(this, ViewAnimationActivity.class);
            startActivity(intent);
        }));

        start_gesture.setOnClickListener((v -> {
            Intent intent = new Intent(this, GestureActivity.class);
            startActivity(intent);
        }));

        start_game.setOnClickListener((v -> {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        }));

        start_interpolator.setOnClickListener((v -> {
            Intent intent = new Intent(this, InterpolatorActivity.class);
            startActivity(intent);
        }));

        start_evaluator.setOnClickListener((v -> {
            Intent intent = new Intent(this, EvaluatorActivity.class);
            startActivity(intent);
        }));

        demo.setOnClickListener((v -> {
            Intent intent = new Intent(this, DemoAnimationActivity.class);
            startActivity(intent);
        }));
        slide_view_group.setOnClickListener((v -> {
            Intent intent = new Intent(this, SlideViewGroupActivity.class);
            startActivity(intent);
        }));


    }
}