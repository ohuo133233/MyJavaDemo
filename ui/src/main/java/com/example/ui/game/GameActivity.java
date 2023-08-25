package com.example.ui.game;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ui.R;
import com.example.ui.game.ui.PlayerController;
import com.example.ui.game.map.Map;
import com.example.ui.game.player.Player;
import com.example.ui.game.ui.UI;
import com.wang.logtools.KLog;

public class GameActivity extends AppCompatActivity {

    private final String TAG = "GameActivity";
    private Map mMap;
    private UI mUI;

    private Player mPlayer;

    private float down_x;
    private float down_y;
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int UP = 3;
    private static final int BOTTOM = 4;
    private static int currentDirection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

//        mPlayer = findViewById(R.id.player);
        mMap = findViewById(R.id.map);
        mUI = findViewById(R.id.ui);

        mPlayer = mMap.findViewById(R.id.player);

        PlayerController playerController = findViewById(R.id.controller);

        playerController.setControllerCallBack(new PlayerController.ControllerCallBack() {
            @Override
            public void up() {
                Log.d(TAG, "up");
                GameActivity.this.up();
            }

            @Override
            public void right() {
                Log.d(TAG, "right");
                GameActivity.this.right();
            }

            @Override
            public void bottom() {
                Log.d(TAG, "bottom");
                GameActivity.this.bottom();
            }

            @Override
            public void left() {
                Log.d(TAG, "left");
                GameActivity.this.left();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    //    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                down_x = event.getX();
//                down_y = event.getY();
//
//                break;
//            case MotionEvent.ACTION_UP:
//                float x = event.getX();
//                float y = event.getY();
////                mMap.move(mPlayer, x, y);
////                    mMap.scrollTo(1000,1000);
////                mPlayer.setAutoOrientation(x, y);
//
//                break;
//
//        }
//        return true;
//    }


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

    private void up() {
        KLog.d(TAG, "up");
        KLog.d(TAG, "mPlayer.getY(): " + mPlayer.getY());

        int width = mPlayer.getWidth();
        int height = mPlayer.getHeight();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(20);
        valueAnimator.setDuration(10);
        valueAnimator.addUpdateListener(animation -> {
            Integer animatedValue = (Integer) animation.getAnimatedValue();
            KLog.d(TAG, "animatedValue: " + animatedValue);
            mPlayer.layout((int) mPlayer.getX(), (int) (mPlayer.getY() - animatedValue), (int) (mPlayer.getX() + width), (int) (mPlayer.getY() + height - animatedValue));
            mMap.movePlayer(mPlayer);
        });
        if (valueAnimator.isRunning()) {
            return;
        }
        valueAnimator.start();

    }

    private void bottom() {
        KLog.d(TAG, "bottom");


        int width = mPlayer.getWidth();
        int height = mPlayer.getHeight();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(20);
        valueAnimator.setDuration(10);
        valueAnimator.addUpdateListener(animation -> {
            Integer animatedValue = (Integer) animation.getAnimatedValue();
            KLog.d(TAG, "animatedValue: " + animatedValue);
            mPlayer.layout((int) mPlayer.getX(), (int) (mPlayer.getY() + animatedValue), (int) (mPlayer.getX() + width), (int) (mPlayer.getY() + height + animatedValue));
            mMap.movePlayer(mPlayer);
        });
        if (valueAnimator.isRunning()) {
            return;
        }
        valueAnimator.start();
    }

    private void left() {
        KLog.d(TAG, "left");

        int width = mPlayer.getWidth();
        int height = mPlayer.getHeight();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(20);
        valueAnimator.setDuration(10);
        valueAnimator.addUpdateListener(animation -> {
            Integer animatedValue = (Integer) animation.getAnimatedValue();
            KLog.d(TAG, "animatedValue: " + animatedValue);
            mPlayer.layout((int) mPlayer.getX() - animatedValue, (int) (mPlayer.getY()), (int) (mPlayer.getX() + width - animatedValue), (int) (mPlayer.getY() + height));
            mMap.movePlayer(mPlayer);
        });
        if (valueAnimator.isRunning()) {
            return;
        }
        valueAnimator.start();

    }

    private void right() {
        int width = mPlayer.getWidth();
        int height = mPlayer.getHeight();
        KLog.d(TAG, "right");
        ValueAnimator valueAnimator = ValueAnimator.ofInt(20);
        valueAnimator.setDuration(10);
        valueAnimator.addUpdateListener(animation -> {
            Integer animatedValue = (Integer) animation.getAnimatedValue();
            KLog.d(TAG, "animatedValue: " + animatedValue);
            mPlayer.layout((int) mPlayer.getX() + animatedValue, (int) (mPlayer.getY()), (int) (mPlayer.getX() + width + animatedValue), (int) (mPlayer.getY() + height));
            mMap.movePlayer(mPlayer);
        });
        if (valueAnimator.isRunning()) {
            return;
        }
        valueAnimator.start();
    }


}