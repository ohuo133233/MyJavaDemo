package com.example.demo.surface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.demo.R;

public class PathSurface extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private String TAG = "PathSurface";
    private SurfaceHolder mSurfaceHolder;
    //绘图的Canvas
    private Canvas mCanvas;
    //子线程标志位
    private boolean mIsDrawing;


    private Bitmap mainRoleBitmap;
    private Paint mPaint;


    public PathSurface(Context context) {
        super(context);
        initView();
    }

    public PathSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PathSurface(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public PathSurface(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mSurfaceHolder = getHolder();
        //注册回调方法
        mSurfaceHolder.addCallback(this);
        //设置一些参数方便后面绘图
        setFocusable(true);
        setKeepScreenOn(true);
        setFocusableInTouchMode(true);

        initMainBitmap();
    }

    private void initMainBitmap() {

        mainRoleBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        mPaint = new Paint();

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //创建
        mIsDrawing = true;
        //开启子线程
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //改变
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //销毁
    }

    @Override
    public void run() {
        //子线程
        while (mIsDrawing) {
            drawSomething();
        }
    }

    //绘图逻辑
    private void drawSomething() {
        try {
            //获得canvas对象
            mCanvas = mSurfaceHolder.lockCanvas();
            //绘制背景
            mCanvas.drawColor(Color.WHITE);
            //绘图
            mCanvas.drawBitmap(mainRoleBitmap, 0, 0, mPaint);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mCanvas != null) {
                //释放canvas对象并提交画布
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
