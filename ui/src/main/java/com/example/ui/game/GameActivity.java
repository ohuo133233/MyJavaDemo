package com.example.ui.game;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ui.R;
import com.wang.logtools.KLog;

public class GameActivity extends AppCompatActivity {

    private final String TAG = "GameActivity";
    private Player mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mPlayer = findViewById(R.id.player);


    }

    private int start;
    private float down_x;
    private float down_y;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                down_x = event.getX();
                down_y = event.getY();
                KLog.d(TAG, " ACTION_DOWN down_x: " + down_x);
                KLog.d(TAG, " ACTION_DOWN down_y: " + down_y);

                int width = mPlayer.getWidth();
                int height = mPlayer.getHeight();
                KLog.d(TAG, " ACTION_DOWN width: " + width);
                KLog.d(TAG, " ACTION_DOWN height: " + height);
//                mPlayer.setVisibility(View.GONE);
//                mPlayer.setX(down_x);
//                mPlayer.setY(down_y);
//                mPlayer.invalidate();

                mPlayer.setTranslationX(down_x);
                mPlayer.setTranslationY(down_y);

                break;
            case MotionEvent.ACTION_UP:
                int x = (int) event.getX();
                int y = (int) event.getY();
//                KLog.d(TAG, " ACTION_UP x: " + x);
//                KLog.d(TAG, " ACTION_UP y: " + y);
//                mPlayer.setX(0);
//                mPlayer.setY(0);
//                mPlayer.invalidate();
                mPlayer.layout(x, y, x + mPlayer.getWidth(), y + mPlayer.getHeight());
//                setOnTouchKey(offsetX,offsetY,x,y);
                break;

        }


        return true;
    }


    /**
     * 按键方案
     *
     * @param offsetX x的偏移量
     * @param offsetY y的偏移量
     * @param x       起手x
     * @param y       起手y
     */
    private void setOnTouchKey(float offsetX, float offsetY, float x, float y) {
        if (offsetX >= offsetY) {
            if (down_y < y) {
                currentDirection = UP;
            } else {
                currentDirection = BOTTOM;
            }
        } else {
            if (down_x < x) {
                currentDirection = LEFT;
            } else {
                currentDirection = RIGHT;
            }
        }


        switch (currentDirection) {
            case LEFT:
                left();
                break;
            case RIGHT:
                right();
                break;
            case UP:
                up();
                break;
            case BOTTOM:
                bottom();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + currentDirection);
        }

    }

    private static final int LEFT = 1;

    private static final int RIGHT = 2;

    private static final int UP = 3;
    private static final int BOTTOM = 4;
    private static int currentDirection;

    private void up() {
        KLog.d(TAG, "up");
        mPlayer.up();
        ValueAnimator valueAnimator = ValueAnimator.ofInt((int) mPlayer.getY(), (int) mPlayer.getY() - 200);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                Integer animatedValue = (Integer) animation.getAnimatedValue();
                int width = mPlayer.getWidth();
                int height = mPlayer.getHeight();
//                KLog.d(TAG, "mPlayer width: " + width);
//                KLog.d(TAG, "mPlayer height: " + height);
//                KLog.d(TAG, "animatedValue: " + animatedValue);
                mPlayer.layout(animatedValue, animatedValue, animatedValue + width, animatedValue + height);
            }
        });
        valueAnimator.start();
    }

    private void bottom() {
        KLog.d(TAG, "bottom");
        mPlayer.bottom();
        ValueAnimator valueAnimator = ValueAnimator.ofInt((int) mPlayer.getY(), (int) mPlayer.getY() + 200);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                Integer animatedValue = (Integer) animation.getAnimatedValue();
                int width = mPlayer.getWidth();
                int height = mPlayer.getHeight();
//                KLog.d(TAG, "mPlayer width: " + width);
//                KLog.d(TAG, "mPlayer height: " + height);
//                KLog.d(TAG, "animatedValue: " + animatedValue);
                mPlayer.layout(animatedValue, animatedValue, animatedValue + width, animatedValue + height);
            }
        });
        valueAnimator.start();
    }

    private void left() {
        KLog.d(TAG, "left");
        mPlayer.left();
        ValueAnimator valueAnimator = ValueAnimator.ofInt((int) mPlayer.getX(), (int) mPlayer.getX() - 200);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                Integer animatedValue = (Integer) animation.getAnimatedValue();
                int width = mPlayer.getWidth();
                int height = mPlayer.getHeight();
//                KLog.d(TAG, "mPlayer width: " + width);
//                KLog.d(TAG, "mPlayer height: " + height);
//                KLog.d(TAG, "animatedValue: " + animatedValue);
                mPlayer.layout(animatedValue, animatedValue, animatedValue + width, animatedValue + height);
            }
        });
        valueAnimator.start();
    }

    private void right() {
        KLog.d(TAG, "right");
        mPlayer.right();
        ValueAnimator valueAnimator = ValueAnimator.ofInt((int) mPlayer.getX(), (int) mPlayer.getX() + 200);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                Integer animatedValue = (Integer) animation.getAnimatedValue();
                int width = mPlayer.getWidth();
                int height = mPlayer.getHeight();
//                KLog.d(TAG, "mPlayer width: " + width);
//                KLog.d(TAG, "mPlayer height: " + height);
//                KLog.d(TAG, "animatedValue: " + animatedValue);
                mPlayer.layout(animatedValue, animatedValue, animatedValue + width, animatedValue + height);
            }
        });
        valueAnimator.start();
    }
}