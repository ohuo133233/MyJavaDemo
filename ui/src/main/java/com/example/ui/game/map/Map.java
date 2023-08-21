package com.example.ui.game.map;

import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.ui.R;
import com.example.ui.evaluator.RouteEvaluator;
import com.wang.logtools.KLog;

public class Map extends CoordinatorLayout {
    private String TAG = Map.class.getSimpleName();

    public Map(Context context) {
        super(context);
        init(context);
    }

    public Map(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Map(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        View root = LayoutInflater.from(context).inflate(R.layout.map, this);


    }


    public void move(View view, float x, float y) {


        Route route = new Route();
        route.setX((int) x);
        route.setY((int) y);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new RouteEvaluator(), route);
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(animation -> {
            Route animatedValue = (Route) animation.getAnimatedValue();
            int width = view.getWidth();
            int height = view.getHeight();
            KLog.d(TAG, "mPlayer width: " + width);
            KLog.d(TAG, "mPlayer height: " + height);
            KLog.d(TAG, "animatedValue: " + animatedValue);
            view.layout(animatedValue.getX(), animatedValue.getY(), animatedValue.getX() + width, animatedValue.getY() + height);
        });


        valueAnimator.start();

    }


}
