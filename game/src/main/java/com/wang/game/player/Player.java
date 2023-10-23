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

/**
 * TODO 使用spine优化贴图和动画
 * <p>
 * <p>
 * 人物Base类
 * 提供4一个移动动画和静态方向
 */
public class Player extends ConstraintLayout {
    private String TAG = Player.class.getSimpleName();

    private ImageView mImageView;

    private AnimationDrawable mDownAnimationDrawable;
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
        // 自定View布局
        View view = LayoutInflater.from(context).inflate(R.layout.player, this, true);
        // 人物
        mImageView = view.findViewById(R.id.imageView);
        // 向上动画
        mUpAnimationDrawable = new AnimationDrawable();
        // 图片信息：字体大小：26  X位置：25
        mUpAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.characters_up_mobile_animation_1), 200);
        mUpAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.characters_up_mobile_animation_2), 200);
        mUpAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.characters_up_mobile_animation_3), 200);
        mUpAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.characters_up_mobile_animation_4), 200);
        mUpAnimationDrawable.setOneShot(true);

        mDownAnimationDrawable = new AnimationDrawable();
        mDownAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.characters_down_mobile_animation_1), 200);
        mDownAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.characters_down_mobile_animation_2), 200);
        mDownAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.characters_down_mobile_animation_3), 200);
        mDownAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.characters_down_mobile_animation_4), 200);
        mDownAnimationDrawable.setOneShot(true);

        mLeftAnimationDrawable = new AnimationDrawable();
        mLeftAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.characters_left_mobile_animation_1), 200);
        mLeftAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.characters_left_mobile_animation_2), 200);
        mLeftAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.characters_left_mobile_animation_3), 200);
        mLeftAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.characters_left_mobile_animation_4), 200);
        mLeftAnimationDrawable.setOneShot(true);

        mRightAnimationDrawable = new AnimationDrawable();
        mRightAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.characters_right_mobile_animation_1), 200);
        mRightAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.characters_right_mobile_animation_2), 200);
        mRightAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.characters_right_mobile_animation_3), 200);
        mRightAnimationDrawable.addFrame(getResources().getDrawable(R.mipmap.characters_right_mobile_animation_4), 200);
        mRightAnimationDrawable.setOneShot(true);

    }

    /**
     * 向上
     */
    public void up() {
        mUpAnimationDrawable.stop();
        mImageView.setBackgroundResource(R.mipmap.characters_up);

    }

    /**
     * 向上移动
     */
    public void run_up() {
        mImageView.setBackground(mUpAnimationDrawable);
        mUpAnimationDrawable.start();
    }

    /**
     * 向下
     */
    public void down() {
        mDownAnimationDrawable.stop();
        mImageView.setBackgroundResource(R.mipmap.characters_down);
    }

    /**
     * 向下移动
     */
    public void run_down() {
        mImageView.setBackground(mDownAnimationDrawable);
        mDownAnimationDrawable.start();
    }

    /**
     * 向左
     */
    public void left() {
        mLeftAnimationDrawable.stop();
        mImageView.setBackgroundResource(R.mipmap.characters_left);
    }

    /**
     * 向左移动
     */
    public void run_left() {
        mImageView.setBackground(mLeftAnimationDrawable);
        mLeftAnimationDrawable.start();
    }

    /**
     * 向右
     */
    public void right() {
        mRightAnimationDrawable.stop();
        mImageView.setBackgroundResource(R.mipmap.characters_right);
    }

    /**
     * 向右移动
     */
    public void run_right() {
        mImageView.setBackground(mRightAnimationDrawable);
        mRightAnimationDrawable.start();
    }

}
