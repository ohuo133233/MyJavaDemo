package com.example.chat.mian;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chat.HomeActivity;
import com.example.chat.R;
import com.example.chat.base.User;
import com.example.chat.sdk.ConnectCallBack;
import com.example.chat.sdk.WebSocketSdk;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //            WebSocketSdk.getInstance().connect("49.233.14.150", "8080");
        WebSocketSdk.getInstance().connect("172.21.203.49", "8828", new ConnectCallBack() {
            @Override
            public void success() {
                Toast.makeText(MainActivity.this, "连接成功", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void fail() {
                Toast.makeText(MainActivity.this, "连接失败", Toast.LENGTH_SHORT).show();
            }
        });
        initView();
    }

    private void initView() {

        Button login = findViewById(R.id.login);
        EditText account_number = findViewById(R.id.account_number);


        login.setOnClickListener(view -> {

            account_number.post(new Runnable() {
                @Override
                public void run() {
                    String account_numberText = account_number.getText().toString();
                    Log.d("TAG", "account_numberText: " + account_numberText);
                    User.getInstance().setId(Integer.parseInt(account_numberText));
                    User.getInstance().setName(account_numberText);
                    WebSocketSdk.getInstance().login();
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }
            });


        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WebSocketSdk.getInstance().close();
    }


}