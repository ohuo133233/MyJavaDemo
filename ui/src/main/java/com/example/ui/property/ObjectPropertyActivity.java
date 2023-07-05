package com.example.ui.property;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ui.R;

public class ObjectPropertyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_property);


        Button button3 = findViewById(R.id.button3);

//        button3.setAlpha();
//        rotation(button3);
//        button3.setTranslationX(1);
//        translationX(button3);
//        button3.setScaleX();
//        scale(button3);
    }

    private void scale(View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0, 2, 1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleX", 0, 2, 1);
        scaleX.setDuration(1000);
        scaleY.setDuration(1000);
        scaleX.start();
        scaleY.start();
    }


    private void alpha(View view) {

        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 1, 0, 1);
        alpha.setDuration(1000);
        alpha.start();
    }

    private void rotation(View view) {

        ObjectAnimator rotation = ObjectAnimator.ofFloat(view, "rotation", 0, 180, 0);
        rotation.setDuration(1000);
        rotation.start();
    }

    private void translationX(View view) {

        ObjectAnimator translationX = ObjectAnimator.ofFloat(view, "translationX", 0, 180, 0);
        translationX.setDuration(1000);
        translationX.start();
    }
}