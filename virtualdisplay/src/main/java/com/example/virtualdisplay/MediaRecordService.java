package com.example.virtualdisplay;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.media.MediaRecorder;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.arthenica.ffmpegkit.FFmpegKit;
import com.arthenica.ffmpegkit.FFmpegSession;
import com.arthenica.ffmpegkit.ReturnCode;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class MediaRecordService extends Service {

    private MediaRecorder mediaRecorder;
    private File recordFile;
    private int width;
    private int height;
    private Surface surface;
    private VirtualDisplay virtualDisplay;
    private MediaCodec videoEncoder;
    private MediaMuxer mediaMuxer;
    private int videoTrackIndex;
    private MediaCodec.BufferInfo bufferInfo;
    private boolean isRecord = false;
    private NotificationManager notificationManager;
    private Intent mIntent;
    private MediaProjection mediaProjection;
    private String absolutePath;
    private String rtmpUrl = "rtmp://172.21.206.54:1935/live/test";
    private String TAG = MediaRecordService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 创建悬浮按钮视图
        View floatingView = LayoutInflater.from(this).inflate(R.layout.floating_button_layout, null);
        Button start_record = floatingView.findViewById(R.id.start_record);
        Button stop_record = floatingView.findViewById(R.id.stop_record);
        Button start_push_flow = floatingView.findViewById(R.id.start_push_flow);
        start_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScreenRecording();
            }
        });
        stop_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopScreenRecording();
            }
        });
        start_push_flow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStreaming();
            }
        });


        // 设置悬浮按钮参数
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.START;
        params.x = 0;
        params.y = 100;

        // 获取 WindowManager
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // 添加悬浮按钮到窗口
        windowManager.addView(floatingView, params);

        // 设置悬浮按钮的触摸监听
        floatingView.setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);
                        windowManager.updateViewLayout(floatingView, params);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        mIntent = intent;
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = new NotificationCompat.Builder(this, "123123").setSmallIcon(R.mipmap.ic_launcher).setContentTitle("录屏").setContentText(getString(R.string.app_name) + "录屏中").build();

        if (Build.VERSION.SDK_INT >= 26) {
            // 推送通道
            NotificationChannel channel = new NotificationChannel("123123", "通道说明", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        // 展示前台服务
        startForeground(123123, notification);


        return super.onStartCommand(intent, flags, startId);
    }


    public void startScreenRecording() {
        int resultCode = mIntent.getIntExtra("resultCode", -1);
        width = mIntent.getIntExtra("height", -1);
        height = mIntent.getIntExtra("width", -1);
        Intent data = mIntent.getParcelableExtra("data");
        surface = mIntent.getParcelableExtra("surface");
        MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        mediaProjection = mediaProjectionManager.getMediaProjection(resultCode, data);
        if (mediaProjection != null) {
            // 获取存储的位置
            recordFile = getExternalFilesDir("RecordFile");

            initMediaRecorder();

            initMediaCodec();

            absolutePath = new File(recordFile + "/record.mp4").getAbsolutePath();
            Log.e("TAG", "absolutePath: " + absolutePath);
            try {

                mediaMuxer = new MediaMuxer(absolutePath, MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4);

                /**
                 * 创建投影
                 * name 本次虚拟显示的名称
                 * width 录制后视频的宽
                 * height 录制后视频的高
                 * dpi 显示屏像素
                 * flags VIRTUAL_DISPLAY_FLAG_PUBLIC 通用显示屏
                 * Surface 输出位置
                 */
                virtualDisplay = mediaProjection.createVirtualDisplay("record-video", width, height, 6000000, DisplayManager.VIRTUAL_DISPLAY_FLAG_PUBLIC, mediaRecorder.getSurface(), null, null);

                isRecord = true;
                bufferInfo = new MediaCodec.BufferInfo();

                readEncoderData();

            } catch (IOException e) {
                e.printStackTrace();
            }

            mediaRecorder.start();
        }
    }


    private void readEncoderData() {
        new Thread(() -> {
            while (isRecord) {
                // 获取已经解码的缓冲区索引
                int index = videoEncoder.dequeueOutputBuffer(bufferInfo, 10000);
                if (index == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED) {
                    // 输出格式已改变
                    resetOutputFormat();
                } else if (index == MediaCodec.INFO_TRY_AGAIN_LATER) {
                    // 超时

                } else if (index >= 0) {
                    // 获取数据
                    ByteBuffer outputBuffer = videoEncoder.getOutputBuffer(index);

                    if ((bufferInfo.flags & MediaCodec.BUFFER_FLAG_CODEC_CONFIG) != 0) {
                        bufferInfo.size = 0;
                    }
                    if (bufferInfo.size == 0) {
                        outputBuffer = null;
                    } else {
                        if (outputBuffer != null) {
                            // 将 ByteBuffer 转换为 byte[]
                            // byte[] bytes = bytebuffer2ByteArray(outputBuffer);

                            mediaMuxer.writeSampleData(videoTrackIndex, outputBuffer, bufferInfo);
                        }
                    }
                    videoEncoder.releaseOutputBuffer(index, false);
                }
            }
        }).start();
    }


    private void initMediaCodec() {
        String MIME_TYPE = "video/avc"; // H.264 类型
        MediaFormat format = MediaFormat.createVideoFormat(MIME_TYPE, width, height);

        // 颜色格式
        format.setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface);
        // 比特率
        format.setInteger(MediaFormat.KEY_BIT_RATE, 6000000);
        // 帧速率
        format.setInteger(MediaFormat.KEY_FRAME_RATE, 60);
        // I帧的帧率
        format.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, 10);

        try {
            // 创建指定类型的编码器
            videoEncoder = MediaCodec.createEncoderByType(MIME_TYPE);
            // 设置编码器属性
            videoEncoder.configure(format, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);
            videoEncoder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void resetOutputFormat() {
        MediaFormat newFormat = videoEncoder.getOutputFormat();
        videoTrackIndex = mediaMuxer.addTrack(newFormat);
        mediaMuxer.start();
    }

    private void initMediaRecorder() {
        mediaRecorder = new MediaRecorder();
        // 设置音频来源
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        // 设置视频来源3
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);
        // 设置输出格式
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        // 设置输出文件
        String absolutePath = new File(recordFile + "/record.mp4").getAbsolutePath();
        mediaRecorder.setOutputFile(absolutePath);
        // 设置视频宽高
        mediaRecorder.setVideoSize(width, height);
        // 设置视频帧率
        mediaRecorder.setVideoFrameRate(60);
        // 设置视频编码比特率
        mediaRecorder.setVideoEncodingBitRate(6000000);
        // 设置音频编码
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        // 设置视频编码
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void stopScreenRecording() {
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
        }

        if (virtualDisplay != null) {
            virtualDisplay.release();
            virtualDisplay = null;
        }

        if (mediaProjection != null) {
            mediaProjection.stop();
            mediaProjection = null;
        }
    }


    private void startStreaming() {
        // 构建 FFmpeg 命令
        // ffmpeg -re -stream_loop -1 -i 视频文件.mp4 -c copy -f flv rtmp://127.0.0.1:1935/live/test
        String ffmpegCommand = "-re -stream_loop -1 -i " + absolutePath + " -c copy -f flv " + rtmpUrl;

        FFmpegSession session = FFmpegKit.execute(ffmpegCommand);
        if (ReturnCode.isSuccess(session.getReturnCode())) {
            Log.d(TAG,"SUCCESS");
            // SUCCESS
        } else if (ReturnCode.isCancel(session.getReturnCode())) {
            // CANCEL
            Log.d(TAG,"CANCEL");
        } else {
            // FAILURE
            Log.d(TAG, String.format("Command failed with state %s and rc %s.%s", session.getState(), session.getReturnCode(), session.getFailStackTrace()));
        }

    }

}