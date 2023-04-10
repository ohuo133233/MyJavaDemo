package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            WebSocketSdk.getInstance().connect("49.233.14.150", "8080");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        initView();
    }

    private void initView() {

        EditText edit_text = findViewById(R.id.edit_text);
        Button send_message = findViewById(R.id.send_message);


        send_message.setOnClickListener(view -> {
            String message = edit_text.getText().toString().trim();
            WebSocketSdk.getInstance().sendMessage(message);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WebSocketSdk.getInstance().close();
    }



}