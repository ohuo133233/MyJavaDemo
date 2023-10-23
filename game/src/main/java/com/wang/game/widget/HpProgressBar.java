package com.wang.game.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.wang.game.R;

/**
 * 血条
 * 1.提供背景红色
 */
public class HpProgressBar extends ProgressBar{
    public HpProgressBar(Context context) {
        super(context);
        init(context);
    }

    public HpProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HpProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public HpProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context context){
        // 设置为红色的
        setProgressDrawable(context.getResources().getDrawable(R.drawable.hp_progress_bar));
    }
}
