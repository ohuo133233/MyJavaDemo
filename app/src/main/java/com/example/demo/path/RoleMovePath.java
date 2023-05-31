package com.example.demo.path;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.demo.R;

public class RoleMovePath extends View {
    private static final String TAG = "RoleMovePath";
    private Bitmap roleBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.role);
    private Paint rolePaint;
    private Path rolePath;
    private Paint paint;

    public RoleMovePath(Context context) {
        super(context);
        initView();
    }

    public RoleMovePath(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RoleMovePath(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public RoleMovePath(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {

        Log.e(TAG, "roleBitmap.Height" + roleBitmap.getHeight());
        rolePaint = new Paint();
        rolePath = new Path();

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        rolePath.moveTo(0, roleBitmap.getHeight());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(roleBitmap, 0, 0, rolePaint);
        canvas.drawPath(rolePath, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: " + event.getAction());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                rolePath.lineTo(event.getX(), event.getY());
                break;
        }
        invalidate();

        return true;
    }
}
