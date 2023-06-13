package com.example.mediaplayer;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wang.recyclerview.CommonRecyclerViewAdapter;
import com.wang.recyclerview.CommonRecyclerViewAdapterBackCall;
import com.wang.recyclerview.CommonRecyclerViewHolder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int progress = 0;
    private String TAG = "MainActivity";
    private PowerManager.WakeLock mWakeLock;
    private boolean isDrag;
    private Player mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBar statusBar = new StatusBar(MainActivity.this);
        //设置颜色为透明
        statusBar.setTransparent();

        getSupportActionBar().hide();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        mPlayer = findViewById(R.id.player);
        SeekBar seekbar = findViewById(R.id.seekbar);

        ImageButton play = findViewById(R.id.play);
        ImageButton stop = findViewById(R.id.stop);

        TextView start_time = findViewById(R.id.start_time);
        TextView end_time = findViewById(R.id.end_time);

        RecyclerView file_list = findViewById(R.id.file_list);

        ArrayList<String> list = new ArrayList<>();
        list.add("第一节课");
        list.add("第二节课");
        list.add("第三节课");
        list.add("第四节课");
        list.add("第五节课");

        CommonRecyclerViewAdapter<String> commonRecyclerViewAdapter = new CommonRecyclerViewAdapter.Build<String>().setContext(this).setLayoutId(R.layout.item).setDataList(list).build();

        commonRecyclerViewAdapter.setCommonRecyclerViewAdapterBackCall((holder, position) -> {
            TextView textView = holder.getView(R.id.title);
            textView.setText(list.get(position));
        });

        file_list.setAdapter(commonRecyclerViewAdapter);
        file_list.setLayoutManager(new LinearLayoutManager(this));

        play.setOnClickListener(v -> {
                mPlayer.startAndPause();
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.release();
            }
        });

        int duration = mPlayer.getDuration();
        Log.d(TAG, "视频长度毫秒: " + duration);
        Log.d(TAG, "视频长度秒: " + duration / 1000);
        String endTime = convertMilliseconds(duration);
        Log.d(TAG, "转换endTime: " + endTime);

        end_time.setText(endTime);


        seekbar.setMax(duration / 1000);


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                Log.d(TAG, "onProgressChanged: progress: " + progress);
                //    mediaPlayer.seekTo(progress);
                int currentPosition = mPlayer.getCurrentPosition();
                Log.d(TAG, "onProgressChanged: currentPosition: " + currentPosition);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isDrag = true;
                Log.d(TAG, "onStartTrackingTouch: ");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isDrag = false;
                Log.d(TAG, "onStopTrackingTouch: ");
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        // 获取电源管理器和WakeLock
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "VideoPlayerActivity");
        mWakeLock.acquire();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 释放WakeLock
//        mWakeLock.release();
    }

    public String convertMilliseconds(long milliseconds) {
        // 计算小时、分钟和秒
        long hours = milliseconds / (60 * 60 * 1000);
        milliseconds %= (60 * 60 * 1000);
        long minutes = milliseconds / (60 * 1000);
        milliseconds %= (60 * 1000);
        long seconds = milliseconds / 1000;

        // 构建格式化的时间字符串
        String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        return time;
    }


}