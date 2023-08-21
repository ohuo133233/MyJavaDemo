package com.example.ui.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ui.R;

public class DemoAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_animation);


        findViewById(R.id.menu_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DemoAnimationActivity.this, ButtonMenuActivity.class));
            }
        });
        findViewById(R.id.image_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DemoAnimationActivity.this, ImageActivity.class));
            }
        });


    }


}