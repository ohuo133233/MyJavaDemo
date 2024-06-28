package com.wang.im;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tencent.imsdk.v2.V2TIMCallback;

public class LoginActivity extends AppCompatActivity {
    private final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = findViewById(R.id.login);

        login.setOnClickListener(view ->
                IMManger.getInstance().login(DebugConfig.USER, DebugConfig.SIGN, new V2TIMCallback() {
                    @Override
                    public void onSuccess() {
                        Log.d(TAG, "login success");
                        login.post(() -> {
                            Toast.makeText(LoginActivity.this, "登录成功: "+DebugConfig.USER, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, ChatActivity.class));
                        });
                    }

                    @Override
                    public void onError(int code, String desc) {
                        // 如果返回以下错误码，表示使用 UserSig 已过期，请您使用新签发的 UserSig 进行再次登录。
                        // 1. ERR_USER_SIG_EXPIRED（6206）
                        // 2. ERR_SVR_ACCOUNT_USERSIG_EXPIRED（70001）
                        // 注意：其他的错误码，请不要在这里调用登录接口，避免 IM SDK 登录进入死循环。
                        Log.d(TAG, "failure, code:" + code + ", desc:" + desc);
                    }
                }));


        Button test_user_1 = findViewById(R.id.test_user_1);
        Button test_user_2 = findViewById(R.id.test_user_2);

        test_user_1.setOnClickListener(view -> {
            DebugConfig.USER=DebugConfig.USER_1;
            DebugConfig.SEND_USER=DebugConfig.USER_2;
            DebugConfig.SIGN=DebugConfig.USER_1_SIGN;
        });
        test_user_2.setOnClickListener(view -> {
            DebugConfig.USER=DebugConfig.USER_2;
            DebugConfig.SEND_USER=DebugConfig.USER_1;
            DebugConfig.SIGN=DebugConfig.USER_2_SIGN;
        });
    }
}