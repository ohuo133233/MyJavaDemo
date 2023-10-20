package com.wang.game.ui.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.wang.game.R;

public class XpProgressBar extends ProgressBar{
    public XpProgressBar(Context context) {
        super(context);
        init(context);
    }

    public XpProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public XpProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public XpProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context context){
        // 设置为金色的
        setProgressDrawable(context.getResources().getDrawable(R.drawable.xp_progress_bar));

    }
}
