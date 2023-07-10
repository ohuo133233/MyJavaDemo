package com.example.ui.property;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ui.R;

public class PropertyAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);

        Button valueAnimator = findViewById(R.id.valueAnimator);
        Button object = findViewById(R.id.object);
        Button animator_set = findViewById(R.id.animator_set);
        Button values_holder = findViewById(R.id.values_holder);

        valueAnimator.setOnClickListener(this);
        object.setOnClickListener(this);
        animator_set.setOnClickListener(this);
        values_holder.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.valueAnimator:
                startActivity(new Intent(PropertyAnimationActivity.this, ValuePropertyActivity.class));
                break;
            case R.id.object:
                startActivity(new Intent(PropertyAnimationActivity.this, ObjectPropertyActivity.class));
                break;
            case R.id.animator_set:
                startActivity(new Intent(PropertyAnimationActivity.this, AnimatorSetActivity.class));
                break;
            case R.id.values_holder:
                startActivity(new Intent(PropertyAnimationActivity.this, ValuesHolderActivity.class));
                break;
            default:
                break;
        }
    }
}