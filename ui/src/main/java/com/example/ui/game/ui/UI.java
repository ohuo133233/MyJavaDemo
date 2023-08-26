package com.example.ui.game.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.ui.R;

public class UI extends ConstraintLayout {

    private final String TAG = UI.class.getSimpleName();
    private View mRoot;

    public UI(Context context) {
        super(context);
        init(context);
    }

    public UI(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UI(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public UI(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mRoot = LayoutInflater.from(context).inflate(R.layout.ui, this);

        initView();
    }

    private void initView() {
        ProgressBar hp_progress_bar = findViewById(R.id.hp_progress_bar);
        ProgressBar mp_progress_bar = findViewById(R.id.mp_progress_bar);
        ProgressBar xp_progress_bar = findViewById(R.id.xp_progress_bar);

        hp_progress_bar.setProgress(100);
        mp_progress_bar.setProgress(50);
        xp_progress_bar.setProgress(50);

    }
}

