package com.example.demo.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.demo.R;

public class TouchPathView extends View {
    private String TAG = "TouchPathView";

    public TouchPathView(Context context) {
        super(context);
        initView();
    }

    public TouchPathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TouchPathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public TouchPathView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private Path path;
    private Paint paint;
    private boolean isClear;

    /**
     * 初始化View
     */
    private void initView() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        path = new Path();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
        if (isClear) {
            path.reset();
            canvas.drawColor(Color.WHITE);
            isClear = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: " + event.getAction());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                path.lineTo(event.getX(), event.getY());
                break;
        }
        invalidate();

        return true;
    }

    public void clear() {
        isClear = true;
        invalidate();
    }
}
