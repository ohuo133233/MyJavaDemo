package com.example.virtualdisplay;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Surface;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    protected Surface surface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        try {
//            MediaCodec mMediaCodec = MediaCodec.createEncoderByType("video/avc");
//            surface = mMediaCodec.createInputSurface();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        findViewById(R.id.start).setOnClickListener(v -> {
            MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
            Intent screenCaptureIntent = mediaProjectionManager.createScreenCaptureIntent();
            startActivityForResult(screenCaptureIntent, 10012);
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 获取申请录屏结果
        if (requestCode == 10012 && resultCode == RESULT_OK) {
            Intent intent = new Intent(this, MediaRecordService.class);
            intent.putExtra("data", data);
            intent.putExtra("resultCode", resultCode);
            intent.putExtra("width", getScreenWidth(this)); // 屏幕的宽
            intent.putExtra("height", getScreenHeight(this)); // 屏幕的高
            intent.putExtra("surface", surface); // Surface 用于显示录屏的数据
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent); // 启动前台服务
            }
        }
    }


    public int getScreenWidth(Context context) {
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
    public int getScreenHeight(Context context) {
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