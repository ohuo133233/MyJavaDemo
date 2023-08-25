package com.example.ui.game.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.ui.R;

public class PlayerController extends ConstraintLayout {
    public PlayerController(@NonNull Context context) {
        super(context);
    }

    public PlayerController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PlayerController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public PlayerController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        View layout = LayoutInflater.from(context).inflate(R.layout.controller, this);
        Button up = layout.findViewById(R.id.up);
        Button right = layout.findViewById(R.id.right);
        Button bottom = layout.findViewById(R.id.bottom);
        Button left = layout.findViewById(R.id.left);


        up.setOnTouchListener((view, motionEvent) -> {
            if (controllerCallBack != null) {
                controllerCallBack.up();
            }
            return false;
        });

        bottom.setOnTouchListener((view, motionEvent) -> {
            if (controllerCallBack != null) {
                controllerCallBack.bottom();
            }
            return false;
        });

        right.setOnTouchListener((view, motionEvent) -> {
            if (controllerCallBack != null) {
                controllerCallBack.right();
            }
            return false;
        });

        left.setOnTouchListener((view, motionEvent) -> {
            if (controllerCallBack != null) {
                controllerCallBack.left();
            }
            return false;
        });


    }

    public ControllerCallBack controllerCallBack;

    public void setControllerCallBack(ControllerCallBack controllerCallBack) {
        this.controllerCallBack = controllerCallBack;
    }

    public interface ControllerCallBack {
        void up();

        void right();

        void bottom();

        void left();
    }
}
