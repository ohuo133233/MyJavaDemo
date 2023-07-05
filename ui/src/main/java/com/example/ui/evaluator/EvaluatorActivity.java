package com.example.ui.evaluator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ui.R;

public class EvaluatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluator);

        Button button2 = findViewById(R.id.button2);

        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 400);
        valueAnimator.addUpdateListener(animation -> {
            Integer animatedValue = (Integer) animation.getAnimatedValue();
            button2.layout(button2.getLeft(), animatedValue, button2.getRight(), animatedValue + button2.getHeight());
        });
        valueAnimator.setDuration(1000);
//        valueAnimator.setEvaluator(new MyEvaluator());
        valueAnimator.start();
    }
}