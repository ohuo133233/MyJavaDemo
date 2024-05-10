package com.example.jiaozi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import xyz.doikki.videoplayer.ijk.IjkPlayerFactory;
import xyz.doikki.videoplayer.player.VideoView;
import xyz.doikki.videoplayer.player.VideoViewConfig;
import xyz.doikki.videoplayer.player.VideoViewManager;

public class MainActivity extends AppCompatActivity {
    private VideoView mVideoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVideoPlayer = findViewById(R.id.player);

        VideoViewManager.setConfig(VideoViewConfig.newBuilder()
                //使用使用IjkPlayer解码
                .setPlayerFactory(IjkPlayerFactory.create())
                .build());

        //设置视频地址
        mVideoPlayer.setUrl("rtsp://172.21.10.19/2");
        // 进入全屏
        mVideoPlayer.startFullScreen();
        // 开始播放，不调用则不自动播放
        mVideoPlayer.start();


    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoPlayer.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoPlayer.release();
    }


    @Override
    public void onBackPressed() {
        if (!mVideoPlayer.onBackPressed()) {
            super.onBackPressed();
        }
    }

}