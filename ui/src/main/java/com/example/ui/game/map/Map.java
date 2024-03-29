package com.example.ui.game.map;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.ui.R;

public class Map extends ConstraintLayout {
    private String TAG = Map.class.getSimpleName();
    private View root;

    private MapCriteria mapCriteria = new MapCriteria();
    private float mCurrentPositionX;
    private float mCurrentPositionY;


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
        root = LayoutInflater.from(context).inflate(R.layout.map, this);
        View button3 = root.findViewById(R.id.button3);
        View button = root.findViewById(R.id.button);

//        findViewById(R.id.)


    }


    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);

        View view = root.findViewById(R.id.view);
        registerIsPlayerPosition(view);
    }


    private void check() {
        mapCriteria.filtered((int) mCurrentPositionX, (int) mCurrentPositionY);

    }


    /**
     * 判断角色是否在指定的区域之内
     *
     * @param view 指定的区域
     */
    private void registerIsPlayerPosition(View view) {

        Rect rect = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        Log.d(TAG, "rect范围： " + rect);
        Log.d(TAG, "mCurrentPositionX: " + mCurrentPositionX);
        Log.d(TAG, "mCurrentPositionY: " + mCurrentPositionY);

        mapCriteria.addRect(rect);
    }

    /**
     * 角色移动后更新地图位置
     *
     * @param view 角色
     */
    public void movePlayer(View view) {
        mCurrentPositionX = view.getX();
        mCurrentPositionY = view.getY();
        check();

    }


}
