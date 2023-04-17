package com.example.chat.mian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chat.HomeActivity;
import com.example.chat.R;
import com.example.chat.sdk.WebSocketSdk;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initView();
    }

    private void initView() {
        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebSocketSdk.getInstance().connect("49.233.14.150", "8080");
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WebSocketSdk.getInstance().close();
    }



}