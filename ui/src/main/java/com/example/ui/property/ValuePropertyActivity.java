package com.example.ui.property;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ui.R;
import com.wang.logtools.KLog;

public class ValuePropertyActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = ValuePropertyActivity.class.getSimpleName();
    private TextView mTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_value);

        mTest = findViewById(R.id.test);
        Button scale = findViewById(R.id.scale);
        Button rotation = findViewById(R.id.rotation);
        Button alpha = findViewById(R.id.alpha);
        Button translation = findViewById(R.id.translation);

        scale.setOnClickListener(this);
        rotation.setOnClickListener(this);
        alpha.setOnClickListener(this);
        translation.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alpha:
                alpha();
                break;
            case R.id.scale:
                scale();
                break;
            case R.id.translation:
                translation();
                break;
            case R.id.rotation:
                rotation();
                break;
        }
    }

    private void rotation() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 200, 50, 80, 100);
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(animation -> {
            Integer animatedValue = (Integer) animation.getAnimatedValue();
            mTest.setRotation(animatedValue);
        });
        valueAnimator.start();
    }

    private void translation() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 200, 50, 80, 100);
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(animation -> {
            Integer animatedValue = (Integer) animation.getAnimatedValue();
            int width = mTest.getWidth();
            int height = mTest.getHeight();
            KLog.d(TAG, "mPlayer width: " + width);
            KLog.d(TAG, "mPlayer height: " + height);
            KLog.d(TAG, "animatedValue: " + animatedValue);
            mTest.layout(animatedValue, animatedValue, animatedValue + width, animatedValue + height);
        });
        valueAnimator.start();
    }

    private void scale() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt( 2, 1);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(animation -> {
            Integer animatedValue = (Integer) animation.getAnimatedValue();
            mTest.setScaleX(animatedValue);
        });
        valueAnimator.start();
    }

    private void alpha() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1, 0.5f, 0, 0.5f, 1);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(animation -> {
            Float animatedValue = (Float) animation.getAnimatedValue();
            mTest.setAlpha(animatedValue);
        });
        valueAnimator.start();
    }
}