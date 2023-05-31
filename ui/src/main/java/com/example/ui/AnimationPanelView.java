package com.example.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

public class AnimationPanelView extends View {
    public AnimationPanelView(Context context) {
        super(context);
    }

    public AnimationPanelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimationPanelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.animation_panel_view, null);
    }

}
