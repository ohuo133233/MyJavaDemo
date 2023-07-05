package com.example.ui.evaluator;

import android.animation.TypeEvaluator;

public class MyEvaluator implements TypeEvaluator<Integer> {
    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        return (int) (200 + startValue + fraction * (endValue - startValue));
    }
}
