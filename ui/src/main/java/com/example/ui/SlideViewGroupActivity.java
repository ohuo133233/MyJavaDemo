package com.example.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 验证了一个问题就是布局大小可以扩充到屏幕显示不到的位置
 */
public class SlideViewGroupActivity extends AppCompatActivity {
    int x = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_view_group);


        findViewById(R.id.root).setOnClickListener(view -> {
            x += 100;
            findViewById(R.id.root).scrollTo(x, 0);
        });


    }
}