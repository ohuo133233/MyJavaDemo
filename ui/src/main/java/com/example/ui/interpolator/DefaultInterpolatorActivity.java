package com.example.ui.interpolator;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ui.R;

public class DefaultInterpolatorActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mDemo;
    private ObjectAnimator TranslationX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_interpolator);

        Button no_interpolator = findViewById(R.id.no_interpolator);
        Button accelerate_decelerate = findViewById(R.id.accelerate_decelerate);
        Button accelerate = findViewById(R.id.accelerate);
        Button decelerate = findViewById(R.id.decelerate);
        Button bounce = findViewById(R.id.bounce);
        Button linear = findViewById(R.id.linear);
        Button anticipate = findViewById(R.id.anticipate);
        Button overshoot = findViewById(R.id.overshoot);
        Button anticipate_overshoot = findViewById(R.id.anticipate_overshoot);
        Button cycle = findViewById(R.id.cycle);

        mDemo = findViewById(R.id.demo);

        TranslationX = ObjectAnimator.ofFloat(mDemo, "translationX", 0, 800);
        TranslationX.setDuration(3000);

        no_interpolator.setOnClickListener(this);
        accelerate_decelerate.setOnClickListener(this);
        accelerate.setOnClickListener(this);
        decelerate.setOnClickListener(this);
        bounce.setOnClickListener(this);
        linear.setOnClickListener(this);
        anticipate.setOnClickListener(this);
        overshoot.setOnClickListener(this);
        anticipate_overshoot.setOnClickListener(this);
        cycle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.no_interpolator:
                noInterpolator();
                break;
            case R.id.accelerate_decelerate:
                accelerateDecelerate();
                break;
            case R.id.accelerate:
                accelerate();
                break;
            case R.id.decelerate:
                decelerate();
                break;
            case R.id.bounce:
                bounce();
                break;
            case R.id.linear:
                linear();
                break;
            case R.id.anticipate:
                anticipate();
                break;
            case R.id.overshoot:
                overshoot();
                break;
            case R.id.anticipate_overshoot:
                anticipateOvershoot();
                break;
            case R.id.cycle:
                cycle();
                break;
        }
    }

    private void cycle() {
        TranslationX.setInterpolator(new CycleInterpolator(5));
        TranslationX.start();
    }

    private void anticipateOvershoot() {
        TranslationX.setInterpolator(new AnticipateOvershootInterpolator());
        TranslationX.start();
    }

    private void overshoot() {
        TranslationX.setInterpolator(new OvershootInterpolator());
        TranslationX.start();
    }

    private void anticipate() {
        TranslationX.setInterpolator(new AnticipateInterpolator());
        TranslationX.start();
    }

    private void linear() {
        TranslationX.setInterpolator(new LinearInterpolator());
        TranslationX.start();
    }

    private void bounce() {
        TranslationX.setInterpolator(new BounceInterpolator());
        TranslationX.start();
    }

    private void decelerate() {
        TranslationX.setInterpolator(new DecelerateInterpolator());
        TranslationX.start();
    }

    private void accelerate() {
        TranslationX.setInterpolator(new AccelerateInterpolator());
        TranslationX.start();
    }

    private void noInterpolator() {
        TranslationX.start();
    }

    private void accelerateDecelerate() {
        TranslationX.setInterpolator(new AccelerateDecelerateInterpolator());
        TranslationX.start();
    }
}