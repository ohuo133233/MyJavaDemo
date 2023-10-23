package com.wang.game.tools;

import android.animation.ValueAnimator;
import android.view.View;

public class AnimationUtils {


    public ValueAnimator up(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        // TODO  考虑使用ObjectAnimator代替
        ValueAnimator valueAnimator = ValueAnimator.ofInt(20);
        valueAnimator.setDuration(10);
        valueAnimator.addUpdateListener(animation -> {
            Integer animatedValue = (Integer) animation.getAnimatedValue();
            view.layout((int) view.getX(), (int) (view.getY() - animatedValue), (int) (view.getX() + width), (int) (view.getY() + height - animatedValue));
        });
        return valueAnimator;
    }

    public ValueAnimator down(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        // TODO  考虑使用ObjectAnimator代替
        ValueAnimator valueAnimator = ValueAnimator.ofInt(20);
        valueAnimator.setDuration(10);
        valueAnimator.addUpdateListener(animation -> {
            Integer animatedValue = (Integer) animation.getAnimatedValue();
            view.layout((int) view.getX(), (int) (view.getY() + animatedValue), (int) (view.getX() + width), (int) (view.getY() + height + animatedValue));
        });
        return valueAnimator;
    }

    public ValueAnimator right(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        // TODO  考虑使用ObjectAnimator代替
        ValueAnimator valueAnimator = ValueAnimator.ofInt(20);
        valueAnimator.setDuration(10);
        valueAnimator.addUpdateListener(animation -> {
            Integer animatedValue = (Integer) animation.getAnimatedValue();
            view.layout((int) view.getX() + animatedValue, (int) (view.getY()), (int) (view.getX() + width + animatedValue), (int) (view.getY() + height));
        });
        return valueAnimator;
    }

    public ValueAnimator left(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        // TODO  考虑使用ObjectAnimator代替
        ValueAnimator valueAnimator = ValueAnimator.ofInt(20);
        valueAnimator.setDuration(10);
        valueAnimator.addUpdateListener(animation -> {
            Integer animatedValue = (Integer) animation.getAnimatedValue();
            view.layout((int) view.getX() - animatedValue, (int) (view.getY()), (int) (view.getX() + width - animatedValue), (int) (view.getY() + height));
        });
        return valueAnimator;
    }


}
