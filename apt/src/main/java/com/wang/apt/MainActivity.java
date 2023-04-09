package com.wang.apt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wang.annotation.WorkInMainThread;

public class MainActivity extends AppCompatActivity {
    private Button update;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);
        update = findViewById(R.id.update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post();
            }
        });
    }

    @WorkInMainThread()
    private void post() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                text.setText("修改文字内容");
            }
        }.start();

    }
}