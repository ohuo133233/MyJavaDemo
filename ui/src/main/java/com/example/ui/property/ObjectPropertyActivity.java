package com.example.ui.property;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ui.R;

public class ObjectPropertyActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_property);


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

    private void scale() {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mTest, "scaleX", 0, 2, 1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mTest, "scaleX", 0, 2, 1);
        scaleX.setDuration(1000);
        scaleY.setDuration(1000);
        scaleX.start();
        scaleY.start();
    }


    private void alpha() {

        ObjectAnimator alpha = ObjectAnimator.ofFloat(mTest, "alpha", 1, 0, 1);
        alpha.setDuration(1000);
        alpha.start();
    }

    private void rotation() {

        ObjectAnimator rotation = ObjectAnimator.ofFloat(mTest, "rotation", 0, 180, 0);
        rotation.setDuration(1000);
        rotation.start();
    }

    private void translation() {

        ObjectAnimator translationX = ObjectAnimator.ofFloat(mTest, "translationX", 0, 180);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(mTest, "translationY", 0, 180);
        translationX.setDuration(1000);
        translationY.setDuration(1000);
        translationX.start();
        translationY.start();
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
}