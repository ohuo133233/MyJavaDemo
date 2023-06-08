package com.example.dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wang.dialog.CommonDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.standard).setOnClickListener(v -> {
            CommonDialog commonDialog = new CommonDialog.Build(MainActivity.this)
                    .setLayout(R.layout.standard_dialog)
                    .build();

            commonDialog.uiShow();
        });

        findViewById(R.id.default_selected).setOnClickListener(v -> {
            CommonDialog commonDialog = new CommonDialog.Build(MainActivity.this)
                    .setLayout(R.layout.default_selected_dialog)
                    .build();

            Button left_button = commonDialog.findViewById(R.id.left_button);
            left_button.setTextColor(Color.WHITE);

            commonDialog.uiShow();
        });
    }
}