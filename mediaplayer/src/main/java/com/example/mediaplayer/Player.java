package com.example.mediaplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class Player extends SurfaceView implements SurfaceHolder.Callback {
    private MediaPlayer mMediaPlayer;
    private final String TAG = "Player";
    private Context mContext;

    public Player(Context context) {
        super(context);
        init(context);
    }

    public Player(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Player(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        this.mContext = context;
        getHolder().addCallback(this);
    }

    private IPlayer mIPlayer;

    public void setPlayer(IPlayer iPlayer) {
        mIPlayer = iPlayer;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        mMediaPlayer = MediaPlayer.create(mContext, R.raw.a);
        mMediaPlayer.setSurface(surfaceHolder.getSurface());
        mMediaPlayer.start();

        // 设置准备完成的监听器
        mMediaPlayer.setOnPreparedListener(mp -> {
            // MediaPlayer 准备完成时的回调方法

            // 创建一个定时器任务，每秒获取播放进度
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (mMediaPlayer == null) {
                        return;
                    }
                    if (mMediaPlayer.isPlaying()) {
                        // 获取当前播放位置
                        int currentPosition = mMediaPlayer.getCurrentPosition();
//                        Log.d(TAG, "run: currentPosition: " + currentPosition);
//                        Log.d(TAG, "run: duration: " + mMediaPlayer.getDuration());
                        if (mIPlayer != null) {
                            mIPlayer.OnProgressListener(mMediaPlayer, currentPosition, mMediaPlayer.getDuration());
                        }
                    }
                }
            };

            // 创建一个定时器，每秒执行一次任务
            Timer timer = new Timer();
            // 延迟0毫秒后开始执行任务，每隔0.5秒执行一次
            timer.schedule(timerTask, 0, 500);
        });

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        mMediaPlayer.release();
    }

    /**
     * 播放方法
     */
    public void start() {
        if (mMediaPlayer != null) {
            mMediaPlayer.start();
        }
    }


    /**
     * 暂停方法
     */
    public void pause() {
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
        }
    }

    /**
     * 播放和暂停的统一方法，播放的时候暂停，暂停的时候播放
     *
     * @return 返回播放状态
     */
    public boolean startAndPause() {
        if (mMediaPlayer == null) {
            return false;
        }
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            return true;
        } else {
            mMediaPlayer.start();
            return false;
        }
    }

    /**
     * 清空资源
     */
    public void release() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    /**
     * 设置音量
     *
     * @param leftVolume  设置左声道声音
     * @param rightVolume 设置右声道声音
     */

    public void setVolume(float leftVolume, float rightVolume) {
        if (mMediaPlayer != null) {
            mMediaPlayer.setVolume(leftVolume, rightVolume);
            mMediaPlayer.start();
        }
    }

    /**
     * 静音
     */
    public void setMute() {
        if (mMediaPlayer != null) {
            mMediaPlayer.setVolume(0.0f, 0.0f);
            mMediaPlayer.start();
        }
    }

    /**
     * 持续时间
     *
     * @return 返回毫秒
     */
    public int getDuration() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getDuration();
        }
        return 0;
    }

    /**
     * 返回当前视频的总时间
     * 例如:01:33:59
     *
     * @return 返回字符串类型的时分秒
     */
    public String getTime() {
        if (mMediaPlayer != null) {
            return convertMilliseconds(mMediaPlayer.getDuration());
        }
        return null;
    }

    /**
     * 当前进度
     */
    public int getCurrentPosition() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    /**
     * 当前时间
     *
     * @return 返回字符串类型的时分秒
     */
    public String getCurrentTime() {
        if (mMediaPlayer != null) {
            return convertMilliseconds(mMediaPlayer.getCurrentPosition());
        }
        return null;
    }


    public void seekTo(int msec) {
        if (mMediaPlayer != null) {
            mMediaPlayer.seekTo(msec);
        }
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
