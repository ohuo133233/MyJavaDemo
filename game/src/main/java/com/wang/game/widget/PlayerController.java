package com.wang.game.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.wang.game.R;


public class PlayerController extends ConstraintLayout {
    public ControllerCallBack controllerCallBack;

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
        ImageButton up = layout.findViewById(R.id.up);
        ImageButton right = layout.findViewById(R.id.right);
        ImageButton bottom = layout.findViewById(R.id.bottom);
        ImageButton left = layout.findViewById(R.id.left);


        up.setOnTouchListener((view, motionEvent) -> {
            if (controllerCallBack != null) {
                controllerCallBack.up();
            }
            return false;
        });

        bottom.setOnTouchListener((view, motionkEvent) -> {
            if (controllerCallBack != null) {
                controllerCallBack.down();
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


    public void setControllerCallBack(ControllerCallBack controllerCallBack) {
        this.controllerCallBack = controllerCallBack;
    }

    public interface ControllerCallBack {
        void up();

        void right();

        void down();

        void left();
    }
}
