package com.example.ui.property;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ui.R;

public class AnimatorSetActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_set);

        Button sequentially = findViewById(R.id.sequentially);
        Button together = findViewById(R.id.together);
        Button one_to_two = findViewById(R.id.one_to_two);
        Button delay = findViewById(R.id.delay);

        mDemo = findViewById(R.id.demo);

        sequentially.setOnClickListener(this);
        together.setOnClickListener(this);
        one_to_two.setOnClickListener(this);
        delay.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sequentially:
                AnimatorSet animatorSet1 = new AnimatorSet();
                animatorSet1.playTogether(rotation(mDemo), translationX(mDemo));
                animatorSet1.start();
                break;
            case R.id.together:
                AnimatorSet animatorSet2 = new AnimatorSet();
                animatorSet2.playSequentially(rotation(mDemo), translationX(mDemo));
                animatorSet2.start();
                break;
            case R.id.one_to_two:
                AnimatorSet animatorSet = new AnimatorSet();
                AnimatorSet.Builder play = animatorSet.play(alpha(mDemo));
                play.before(translationX(mDemo));
                play.before(rotation(mDemo));
                animatorSet.start();
                break;
            case R.id.delay:
                ObjectAnimator rotation = ObjectAnimator.ofFloat(mDemo, "rotation", 0, 180, 0);
                rotation.setStartDelay(2000);
                ObjectAnimator translationX = ObjectAnimator.ofFloat(mDemo, "translationX", 0, 180, 0);
                translationX.setStartDelay(2000);
                AnimatorSet animatorSet3 = new AnimatorSet();
                animatorSet3.setStartDelay(2000);
                animatorSet3.playSequentially(rotation,translationX);
                animatorSet3.start();
                break;

        }
    }


    private ObjectAnimator rotation(View view) {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(view, "rotation", 0, 180, 0);
        rotation.setDuration(1000);
        return rotation;
    }

    private ObjectAnimator translationX(View view) {
        ObjectAnimator translationX = ObjectAnimator.ofFloat(view, "translationX", 0, 180, 0);
        translationX.setDuration(1000);
        return translationX;
    }

    private ObjectAnimator alpha(View view) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 1, 0, 1);
        alpha.setDuration(1000);
        return alpha;
    }
}