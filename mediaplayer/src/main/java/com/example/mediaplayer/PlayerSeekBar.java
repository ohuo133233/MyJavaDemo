package com.example.mediaplayer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class PlayerSeekBar extends SeekBar {
    private Player mPlayer;
    private int progress = 0;
    private boolean isDrag;

    public PlayerSeekBar(Context context) {
        super(context);
    }

    public PlayerSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayerSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void attach(Player player) {
        mPlayer = player;
        start();
    }

    private void start() {
        new Thread() {
            @Override
            public void run() {
                super.run();

                while (progress < mPlayer.getDuration()) {
                    if (isDrag) {
                        return;
                    }
                    progress += 1;
                    setProgress(progress);


                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }.start();
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
