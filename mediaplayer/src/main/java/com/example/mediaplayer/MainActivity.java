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
import androidx.constraintlayout.widget.Group;
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
    private int mProgress;

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

        CommonRecyclerViewAdapter<String> commonRecyclerViewAdapter = new CommonRecyclerViewAdapter.Build<String>()
                .setContext(this)
                .setLayoutId(R.layout.item)
                .setDataList(list)
                .build();

        commonRecyclerViewAdapter.setCommonRecyclerViewAdapterBackCall((holder, position) -> {
            holder.getItemView().setOnClickListener(v -> {
                Group group = holder.getView(R.id.group);
                if (group.getVisibility() == View.VISIBLE) {
                    group.setVisibility(View.GONE);
                } else {
                    group.setVisibility(View.VISIBLE);
                }
            });
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


        mPlayer.setPlayer(new IPlayer() {
            @Override
            public void OnProgressListener(MediaPlayer mp, int progress, int duration) {
//                Log.d(TAG, "OnProgressListener: progress: " + progress);
//                Log.d(TAG, "OnProgressListener: duration: " + duration);
                if (isDrag) {
                    return;
                }
                runOnUiThread(() -> {
                    seekbar.setMax(duration);
                    seekbar.setProgress(progress);
                    end_time.setText(mPlayer.getTime());
                    start_time.setText(mPlayer.getCurrentTime());
                });

            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                Log.d(TAG, "onProgressChanged: ");
                mProgress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//                Log.d(TAG, "onStartTrackingTouch: ");
                isDrag = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                Log.d(TAG, "onStopTrackingTouch: ");
                isDrag = false;
                mPlayer.seekTo(mProgress);
            }
        });
    }


}