package com.example.ui.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ui.R;

public class ButtonMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_menu);

        menu();
    }

    private boolean mIsMenuOpen = false;

    private void menu() {
        Button menu = findViewById(R.id.menu);
        Button a = findViewById(R.id.a);
        Button b = findViewById(R.id.b);
        Button c = findViewById(R.id.c);
        Button d = findViewById(R.id.d);
        Button e = findViewById(R.id.e);

        menu.setOnClickListener(v -> {
            if (!mIsMenuOpen) {
                openMenu(a, b, c, d, e);
            } else {
                closeMenu(a, b, c, d, e);
            }
        });

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenu(a, b, c, d, e);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenu(a, b, c, d, e);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenu(a, b, c, d, e);
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenu(a, b, c, d, e);
            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenu(a, b, c, d, e);
            }
        });

    }

    private void openMenu(Button a, Button b, Button c, Button d, Button e) {
        mIsMenuOpen = true;
        doAnimateOpen(a, 0, 5, 500);
        doAnimateOpen(b, 1, 5, 500);
        doAnimateOpen(c, 2, 5, 500);
        doAnimateOpen(d, 3, 5, 500);
        doAnimateOpen(e, 4, 5, 500);
    }

    private void doAnimateOpen(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }

        double degree = Math.toRadians(90) / (total - 1) * index;
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0, 1f));
        animatorSet.setDuration(500);
        animatorSet.start();
    }

    private void closeMenu(Button a, Button b, Button c, Button d, Button e) {
        mIsMenuOpen = false;
        doAnimateClose(a, 0, 5, 500);
        doAnimateClose(b, 1, 5, 500);
        doAnimateClose(c, 2, 5, 500);
        doAnimateClose(d, 3, 5, 500);
        doAnimateClose(e, 4, 5, 500);
    }


    private void doAnimateClose(View view, int index, int total, int radius) {
        double degree = Math.PI * index / ((total - 1) * 2);
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translationX, 0),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.1f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.1f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0));

        animatorSet.setDuration(500);
        animatorSet.start();
    }

}