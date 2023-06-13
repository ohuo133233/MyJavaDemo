package com.example.mediaplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        mMediaPlayer = MediaPlayer.create(mContext, R.raw.a);
        mMediaPlayer.setSurface(surfaceHolder.getSurface());
        mMediaPlayer.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

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
     */
    public int getDuration() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getDuration();
        }
        return 0;
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


}
