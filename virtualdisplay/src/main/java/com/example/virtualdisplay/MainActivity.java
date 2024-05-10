package com.example.virtualdisplay;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    protected Surface surface;
    // 录制的视频文件路径
    private String inputFilePath = "/storage/emulated/0/Android/data/com.example.virtualdisplay/files/RecordFile/123456.mp4";
    // RTMP 服务器地址
    private String rtmpUrl = "rtmp://172.21.202.20/live/test";
    private MediaProjectionManager mMediaProjectionManager;
    private static final int REQUEST_CODE_OVERLAY_PERMISSION = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// 检查是否拥有悬浮窗口权限
        if (!Settings.canDrawOverlays(this)) {
            // 如果没有权限，请求权限
            requestOverlayPermission();
        }
        mMediaProjectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);

        findViewById(R.id.start).setOnClickListener(v -> {
            Intent screenCaptureIntent = mMediaProjectionManager.createScreenCaptureIntent();
            startActivityForResult(screenCaptureIntent, 10012);
        });



    }

    private void requestOverlayPermission() {
        // 请求悬浮窗口权限
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, REQUEST_CODE_OVERLAY_PERMISSION);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 获取申请录屏结果
        if (requestCode == 10012 && resultCode == RESULT_OK) {
            Intent intent = new Intent(this, MediaRecordService.class);
            intent.putExtra("data", data);
            intent.putExtra("resultCode", resultCode);
            intent.putExtra("height",getScreenHeight(this));
            intent.putExtra("width",getScreenWidth(this));
            intent.putExtra("surface", surface); // Surface 用于显示录屏的数据
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent); // 启动前台服务
            }
        }

        if (requestCode == REQUEST_CODE_OVERLAY_PERMISSION) {
            // 检查权限是否被授予
            if (Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "悬浮窗口权限授予", Toast.LENGTH_SHORT).show();
            } else {
                // 如果权限未被授予，可以显示一些提示信息或者采取其他适当的措施
                Toast.makeText(this, "悬浮窗口权限未授予", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public int getScreenWidth(@NonNull Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) return -1;
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.getDefaultDisplay().getRealSize(point);
        } else {
            wm.getDefaultDisplay().getSize(point);
        }
        return point.x;
    }

    /**
     * Return the height of screen, in pixel.
     *
     * @return the height of screen, in pixel
     */
    public int getScreenHeight(@NonNull Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) return -1;
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.getDefaultDisplay().getRealSize(point);
        } else {
            wm.getDefaultDisplay().getSize(point);
        }
        return point.y;
    }


}