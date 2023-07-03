package com.example.ui.property;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.example.ui.R;
import com.wang.logtools.KLog;

public class PropertyTranslateActivity extends AppCompatActivity {

    private static final String TAG = PropertyTranslateActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_translate);

        TextView test = findViewById(R.id.test);
        Button start = findViewById(R.id.start);
//        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 20);
//        valueAnimator.setDuration(1000);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
//                Integer animatedValue = (Integer) animation.getAnimatedValue();
//                int width = test.getWidth();
//                int height = test.getHeight();
//                KLog.d(TAG, "mPlayer width: " + width);
//                KLog.d(TAG, "mPlayer height: " + height);
//                KLog.d(TAG, "animatedValue: " + animatedValue);
//                test.layout(animatedValue, animatedValue, animatedValue + width, animatedValue + height);
//            }
//        });


        // 第二种实现
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 200);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                valueAnimator.start();
                test.startAnimation(translateAnimation);
            }
        });

    }
}