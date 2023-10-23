package com.wang.game.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.wang.game.R;

/**
 * 蓝条
 * 1.提供背景蓝色
 */
public class MpProgressBar extends ProgressBar {
    public MpProgressBar(Context context) {
        super(context);
        init(context);
    }

    public MpProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MpProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MpProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context context) {
        // 设置为蓝色的
        setProgressDrawable(context.getResources().getDrawable(R.drawable.mp_progress_bar));

    }
}
