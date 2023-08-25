package com.example.ui.game.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.ui.R;

public class OperateController extends ConstraintLayout {
    public OperateController(@NonNull Context context) {
        super(context);
    }

    public OperateController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public OperateController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public OperateController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    private void initView(Context context) {
        View layout = LayoutInflater.from(context).inflate(R.layout.operate_controller, this);}
}
