package com.example.demo.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.wang.logtools.KLog;

public class PathSurface extends SurfaceView implements SurfaceHolder.Callback ,Runnable{
    private String TAG = "PathSurface";
    private Path mPath;
    private Paint mPaint;
    private boolean mIsDrawing;
    private SurfaceHolder mSurfaceHolder;
    private Canvas mCanvas;
    public PathSurface(Context context) {
        super(context);
        init();
    }

    public PathSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathSurface(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PathSurface(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        // 获取SurfaceHolder
         mSurfaceHolder = getHolder();
        // 注册回调接口 surfaceCreated、surfaceChanged、surfaceDestroyed
        mSurfaceHolder.addCallback(this);
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(20);
        mPath.moveTo(0, 0);
        mPath.lineTo(500, 500);



    }



    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void run() {
        while (mIsDrawing) {
            draw();
        }
    }

    private void draw() {
        KLog.d(TAG,"draw");
        try {
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.drawPath(mPath, mPaint);
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            if (mCanvas != null) {
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
