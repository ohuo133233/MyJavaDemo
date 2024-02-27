package com.example.camera.utils;

import android.util.Log;
import android.view.Choreographer;


public class PoorFrameTracker implements Choreographer.FrameCallback {
    public static final String TAG = "PoorFrameTracker";
    private static PoorFrameTracker sInstance;
    private long mLastFrameTime = -1L;
    private long mFrameCount = 0;
    // ms
    private final static int calRate = 200;


    private PoorFrameTracker() {
        sInstance = new PoorFrameTracker();
    }

    public static PoorFrameTracker getInstance() {
        return sInstance;
    }

    public void startTrack() {
        mLastFrameTime = 0L;
        mFrameCount = 0;
        Choreographer.getInstance().postFrameCallback(this);
    }

    @Override
    public void doFrame(long frameTimeNanos) {
        if (mLastFrameTime == -1L) {
            mLastFrameTime = frameTimeNanos;
        }
        double diff = (frameTimeNanos - mLastFrameTime) / 1_000_000.0f;
        if (diff > calRate) {
            double fps = mFrameCount / diff * 1000;
            mFrameCount = 0;
            mLastFrameTime = -1;
            Log.d(TAG, "fps: " + fps);
        } else {
            mFrameCount++;
        }
        Choreographer.getInstance().postFrameCallback(this);


    }
}
