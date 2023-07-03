package com.example.ui.game;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.ui.R;

public class Player extends LinearLayout {

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

    public Player(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.player, this,true);

//        addView(view);


        mImageView = view.findViewById(R.id.imageView);

        mBottomAnimationDrawable = new AnimationDrawable();
        mBottomAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.bottom_1), 200);
        mBottomAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.bottom_2), 200);
        mBottomAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.bottom_3), 200);
        mBottomAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.bottom_4), 200);
        mBottomAnimationDrawable.setOneShot(true);

        mUpAnimationDrawable = new AnimationDrawable();
        mUpAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.up_1), 200);
        mUpAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.up_2), 200);
        mUpAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.up_3), 200);
        mUpAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.up_4), 200);
        mUpAnimationDrawable.setOneShot(true);

        mLeftAnimationDrawable = new AnimationDrawable();
        mLeftAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.right_1), 200);
        mLeftAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.right_2), 200);
        mLeftAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.right_3), 200);
        mLeftAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.right_4), 200);
        mLeftAnimationDrawable.setOneShot(false);

        mRightAnimationDrawable = new AnimationDrawable();
        mRightAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.left_1), 200);
        mRightAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.left_2), 200);
        mRightAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.left_3), 200);
        mRightAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.left_4), 200);
        mRightAnimationDrawable.setOneShot(false);

    }


    public void up() {
        mImageView.setBackground(mUpAnimationDrawable);
        mUpAnimationDrawable.start();
    }


    public void bottom(){
        mImageView.setBackground(mBottomAnimationDrawable);
        mBottomAnimationDrawable.start();
    }

    public void left(){
        mImageView.setBackground(mLeftAnimationDrawable);
        mLeftAnimationDrawable.start();
    }

    public void right(){
        mImageView.setBackground(mRightAnimationDrawable);
        mRightAnimationDrawable.start();
    }
}
