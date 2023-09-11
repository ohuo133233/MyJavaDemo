package com.example.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SlideView extends ConstraintLayout {
    public SlideView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public SlideView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SlideView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public SlideView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    int x = 100;

    private void initView(Context context) {

        ConstraintLayout root = (ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.slide_view_group, this);
        Button a = root.findViewById(R.id.a);
        root.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                x += 100;
                root.scrollTo(x, 0);
            }
        });

    }
}
