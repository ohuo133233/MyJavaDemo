package com.wang.game.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.wang.game.Constant;
import com.wang.game.R;
import com.wang.game.map.Map;
import com.wang.game.player.Player;
import com.wang.game.widget.HpProgressBar;
import com.wang.game.widget.MpProgressBar;
import com.wang.game.widget.PlayerController;
import com.wang.game.widget.XpProgressBar;


public class UI extends ConstraintLayout {

    private final String TAG = UI.class.getSimpleName();
    private View mRoot;
    private Player mPlayer;
    private Map mMap;

    public UI(Context context) {
        super(context);
        init(context);
    }

    public UI(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UI(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public UI(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mRoot = LayoutInflater.from(context).inflate(R.layout.ui, this);

        initView();
    }


    private void initView() {
        HpProgressBar hp_progress_bar = findViewById(R.id.hp_progress_bar);
        MpProgressBar mp_progress_bar = findViewById(R.id.mp_progress_bar);
        XpProgressBar xp_progress_bar = findViewById(R.id.xp_progress_bar);

        hp_progress_bar.setProgress(100);
        mp_progress_bar.setProgress(50);
        xp_progress_bar.setProgress(50);

    }


    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);

        mPlayer = mMap.findViewById(R.id.player);
        PlayerController playerController = findViewById(R.id.controller);

        playerController.setControllerCallBack(new PlayerController.ControllerCallBack() {
            @Override
            public void up() {
                Log.d(TAG, "up");
                mMap.moveView(mPlayer, Constant.UP);
            }

            @Override
            public void right() {
                Log.d(TAG, "right");
                mMap.moveView(mPlayer, Constant.RIGHT);
            }

            @Override
            public void down() {
                Log.d(TAG, "down");
                mMap.moveView(mPlayer, Constant.DOWN);
            }

            @Override
            public void left() {
                Log.d(TAG, "left");
                mMap.moveView(mPlayer, Constant.LEFT);
            }
        });
    }


    private float down_x;
    private float down_y;

    private static int currentDirection;

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
                currentDirection = Constant.UP;
            } else {
                currentDirection = Constant.DOWN;
            }
        } else {
            if (down_x < x) {
                currentDirection = Constant.LEFT;
            } else {
                currentDirection = Constant.RIGHT;
            }
        }


//        switch (currentDirection) {
//            case Constant.LEFT:
//                left();
//                break;
//            case Constant.RIGHT:
//                right();
//                break;
//            case Constant.UP:
//                up();
//                break;
//            case Constant.DOWN:
//                down();
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + currentDirection);
//        }

    }

    public void setMap(Map map) {
        mMap = map;
    }
}

