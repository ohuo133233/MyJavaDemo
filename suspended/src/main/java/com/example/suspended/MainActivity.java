package com.example.suspended;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final int CODE_WINDOW = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 清单列表声明权限
        // 动态申请权限
        findViewById(R.id.get_permission).setOnClickListener(v -> {
            // 申请悬浮窗权限
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(MainActivity.this)) {
                    Toast.makeText(MainActivity.this, "请打开此应用悬浮窗权限-Shendi", Toast.LENGTH_SHORT).show();
                    startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), CODE_WINDOW);
                }
            }
        });
        findViewById(R.id.start_suspended).setOnClickListener(v -> {
            startService(new Intent(MainActivity.this,SuspendedService.class));
        });


    }

    // 权限申请成功后的回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // 不给权限就退出
            case CODE_WINDOW:
                if (resultCode != Activity.RESULT_OK) System.exit(0);
                break;
            default:
                Toast.makeText(this, "未知权限回调: " + requestCode, Toast.LENGTH_SHORT).show();
        }
    }


}