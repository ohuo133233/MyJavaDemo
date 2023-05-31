package com.example.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

public class MyEditText  extends View {
    public MyEditText(Context context) {
        super(context);
        initView(context);
    }

    public MyEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        View inflate = LayoutInflater.from(context).inflate(R.layout.my_edit_text, null);
    }
}
