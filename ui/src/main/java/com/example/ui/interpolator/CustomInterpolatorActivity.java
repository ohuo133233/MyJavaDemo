package com.example.ui.interpolator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import com.example.ui.R;
import com.wang.logtools.KLog;

public class CustomInterpolatorActivity extends AppCompatActivity {
    private String TAG = "CustomInterpolatorActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_interpolator);

        Button demo = findViewById(R.id.demo);

        ObjectAnimator translationX = ObjectAnimator.ofFloat(demo, "translationX", 0, 800);
        translationX.setDuration(5000);
        translationX.setInterpolator(new MyInterpolator());
        translationX.start();

        translationX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                Float animatedValue = (Float) animation.getAnimatedValue();

                KLog.d(TAG, "animatedValue: " + animatedValue);
            }
        });


    }
}