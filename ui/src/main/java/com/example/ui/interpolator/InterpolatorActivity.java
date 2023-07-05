package com.example.ui.interpolator;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ui.R;
import com.wang.logtools.KLog;

public class InterpolatorActivity extends AppCompatActivity {

    private String TAG = "InterpolatorActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator);

        Button button = findViewById(R.id.button);

        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 200);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(animation -> {
            Integer animatedValue = (Integer) animation.getAnimatedValue();
            KLog.d(TAG, "animatedValue: " + animatedValue);
            int width = button.getWidth();
            int height = button.getHeight();
            int left = button.getLeft();
            int right = button.getRight();

            KLog.d(TAG, "width: " + width);
            KLog.d(TAG, "height: " + height);
            KLog.d(TAG, "left: " + left);
            KLog.d(TAG, "right: " + right);

            button.layout(button.getLeft(), animatedValue, button.getRight(), animatedValue + button.getHeight());
        });
    }
}