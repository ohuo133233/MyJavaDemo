package com.example.demo.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class PathView extends View {

    private String TAG = "PathView";
    private Path path;
    private Paint paint;
    private boolean isClear;

    public PathView(Context context) {
        super(context);
        initView();
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

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
            canvas.drawColor(Color.WHITE);
            isClear=false;
        }
    }

    public void clear() {
        isClear = true;
        path.reset();
        invalidate();
    }

    public void triangle() {
        path.moveTo(10, 10);
        path.lineTo(10, 100);
        path.lineTo(300, 100);
        path.close();
        invalidate();
}
    // TODO 改为画圆弧
    public void circular() {
        path.moveTo(1000,1000);
        RectF rectF = new RectF(100, 10, 200, 100);
        path.arcTo(rectF, 0, 359, true);
        invalidate();
    }


}
