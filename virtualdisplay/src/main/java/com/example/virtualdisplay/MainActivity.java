package com.example.virtualdisplay;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.arthenica.ffmpegkit.FFmpegKit;
import com.arthenica.ffmpegkit.FFmpegSession;
import com.arthenica.ffmpegkit.ReturnCode;

public class MainActivity extends AppCompatActivity {
    protected Surface surface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);

        findViewById(R.id.start).setOnClickListener(v -> {
            Intent screenCaptureIntent = mediaProjectionManager.createScreenCaptureIntent();
            startActivityForResult(screenCaptureIntent, 10012);
        });

        findViewById(R.id.start_live).setOnClickListener(v -> {
            startStreaming();
        });


    }


    private String inputFilePath = "/storage/emulated/0/Android/data/com.example.virtualdisplay/files/RecordFile/123456.mp4"; // 录制的视频文件路径
    private String rtmpUrl = "rtmp://172.21.202.20/live/test"; // RTMP 服务器地址


    private void startStreaming() {
        // 构建 FFmpeg 命令
//        String[] ffmpegCommand = new String[]{"-i", inputFilePath,        // 输入文件路径
//                "-c:v", "copy",             // 使用相同的视频编码
//                "-c:a", "aac",              // 使用 AAC 音频编码
//                "-f", "flv", rtmpUrl        // 输出为 FLV 格式并推流到 RTMP 服务器
//        };
        String[] ffmpegCommand = new String[]{
                "-re",
                "-stream_loop", "-1",
                "-i", inputFilePath,
                "-c", "copy",
                "-f", "flv",
                "rtmp://172.21.202.20:1935/live/test"
        };

        String ffmpegCommand1 = "-f gdigrab -framerate 30 -i desktop -c:v libx264 -preset ultrafast -tune zerolatency -f flv " + rtmpUrl;

//        FFmpegSession session = FFmpegKit.execute("-re -stream_loop -1 -i "+inputFilePath+" -c copy -f flv rtsp://172.21.121.25:8554/live/test");
        FFmpegSession session = FFmpegKit.execute(ffmpegCommand1);
        if (ReturnCode.isSuccess(session.getReturnCode())) {
            // SUCCESS
        } else if (ReturnCode.isCancel(session.getReturnCode())) {
            // CANCEL
        } else {
            // FAILURE
            Log.d("TAG", String.format("Command failed with state %s and rc %s.%s", session.getState(), session.getReturnCode(), session.getFailStackTrace()));

        }

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