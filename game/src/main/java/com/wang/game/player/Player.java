package com.wang.game.player;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.wang.game.R;
import com.wang.logtools.KLog;

public class Player extends ConstraintLayout {
    private String TAG = Player.class.getSimpleName();

    private ImageView mImageView;

    private AnimationDrawable mBottomAnimationDrawable;
    private AnimationDrawable mUpAnimationDrawable;
    private AnimationDrawable mRightAnimationDrawable;
    private AnimationDrawable mLeftAnimationDrawable;


    public Player(Context context) {
        super(context);
        init(context);
    }

    public Player(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Player(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.player, this, true);

//        addView(view);


        mImageView = view.findViewById(R.id.imageView);
        mImageView.setVisibility(GONE);

//        mBottomAnimationDrawable = new AnimationDrawable();
//        mBottomAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.bottom_1), 200);
//        mBottomAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.bottom_2), 200);
//        mBottomAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.bottom_3), 200);
//        mBottomAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.bottom_4), 200);
//        mBottomAnimationDrawable.setOneShot(true);
//
//        mUpAnimationDrawable = new AnimationDrawable();
//        mUpAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.up_1), 200);
//        mUpAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.up_2), 200);
//        mUpAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.up_3), 200);
//        mUpAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.up_4), 200);
//        mUpAnimationDrawable.setOneShot(true);
//
//        mLeftAnimationDrawable = new AnimationDrawable();
//        mLeftAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.right_1), 200);
//        mLeftAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.right_2), 200);
//        mLeftAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.right_3), 200);
//        mLeftAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.right_4), 200);
//        mLeftAnimationDrawable.setOneShot(false);
//
//        mRightAnimationDrawable = new AnimationDrawable();
//        mRightAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.left_1), 200);
//        mRightAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.left_2), 200);
//        mRightAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.left_3), 200);
//        mRightAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.left_4), 200);
//        mRightAnimationDrawable.setOneShot(false);

    }


    public void up() {
        mImageView.setBackground(mUpAnimationDrawable);
        mUpAnimationDrawable.start();
    }


    public void bottom() {
        mImageView.setBackground(mBottomAnimationDrawable);
        mBottomAnimationDrawable.start();
    }

    public void left() {
        mImageView.setBackground(mLeftAnimationDrawable);
        mLeftAnimationDrawable.start();
    }

    public void right() {
        mImageView.setBackground(mRightAnimationDrawable);
        mRightAnimationDrawable.start();
    }

    public void setAutoOrientation(int x, int y) {
        // 获取当前位置playerView的x和y
        float x1 = getX();
        float y1 = getY();

        // 手指按下的x位置-当前角色的x位置=偏差的x
        float dx = x - x1;
        float dy = y - y1;

        KLog.d(TAG, "dx: " + dx);
        KLog.d(TAG, "dy: " + dy);

        int width = getWidth();
        int height = getHeight();
        KLog.d(TAG, "width: " + width);
        KLog.d(TAG, "height: " + height);


        if (dx >= width || dy >= height) {
            // 计算位移量并判断方向
            if (Math.abs(dx) > Math.abs(dy)) {
                // 水平方向
                if (dx > 0) {
                    // 向右滑动
                    KLog.d(TAG, "向右滑动");
                    // TODO: 在这里处理向右滑动的逻辑
                } else {
                    // 向左滑动
                    KLog.d(TAG, "向左滑动");
                    // TODO: 在这里处理向左滑动的逻辑
                }
            } else {
                // 垂直方向
                if (dy > 0) {
                    // 向下滑动
                    KLog.d(TAG, "向下滑动");
                    // TODO: 在这里处理向下滑动的逻辑
                } else {
                    // 向上滑动
                    KLog.d(TAG, "向上滑动");



                    // TODO: 在这里处理向上滑动的逻辑
                }
            }
        } else {
            // 判断四个对角线方向
            if (dx > 0 && dy > 0) {
                // 右下方向
                KLog.d(TAG, "右下方向");
                // TODO: 在这里处理右下方向的逻辑
            } else if (dx > 0 && dy < 0) {
                // 右上方向
                KLog.d(TAG, "右上方向");
                // TODO: 在这里处理右上方向的逻辑
            } else if (dx < 0 && dy > 0) {
                // 左下方向
                KLog.d(TAG, "左下方向");
                // TODO: 在这里处理左下方向的逻辑
            } else if (dx < 0 && dy < 0) {
                // 左上方向
                KLog.d(TAG, "左上方向");
                // TODO: 在这里处理左上方向的逻辑
            }
        }


    }
}
