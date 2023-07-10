package com.example.ui.interpolator;

import android.view.animation.BaseInterpolator;

public class MyInterpolator extends BaseInterpolator {
    @Override
    public float getInterpolation(float input) {
        // 当动画播放到一半的时候，再返回
        if (input >= 0.5f) {
            return  input;
        }
        return 1 - input;
    }
}
