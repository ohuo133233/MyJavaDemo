package com.example.ui.evaluator;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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
        valueAnimator.setDuration(5000);
        valueAnimator.setEvaluator(new MyEvaluator());
//        valueAnimator.setEvaluator(new FloatEvaluator());
//        valueAnimator.setEvaluator(new FloatEvaluator());
        valueAnimator.start();

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(button2, "scaleX", 0, 2, 1);
    }
}