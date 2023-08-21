package com.example.ui.game.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.ui.R;

public class UI extends View {
    public UI(Context context) {
        super(context);
    }

    public UI(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public UI(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public UI(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context) {
        View root = LayoutInflater.from(context).inflate(R.layout.ui, null, false);


    }
}
