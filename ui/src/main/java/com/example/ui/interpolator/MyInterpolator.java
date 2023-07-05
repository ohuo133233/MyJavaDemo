package com.example.ui.interpolator;

import android.animation.TimeInterpolator;

public class MyInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        return 1-input;
    }
}
