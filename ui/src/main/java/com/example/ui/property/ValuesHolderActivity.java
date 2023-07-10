package com.example.ui.property;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ui.R;

public class ValuesHolderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_values_holder);

        Button test = findViewById(R.id.test);

        PropertyValuesHolder rotation = PropertyValuesHolder.ofFloat("Rotation", 60f, -60f, 40f, -40, -20f, 20f, 10f, -10f, 0);
        ValueAnimator valueAnimator = ObjectAnimator.ofPropertyValuesHolder(test,rotation);
        valueAnimator.setDuration(2000);
        valueAnimator.start();
    }
}