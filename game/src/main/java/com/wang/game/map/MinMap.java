package com.wang.game.map;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MinMap extends ConstraintLayout {
    public MinMap(@NonNull Context context) {
        super(context);
        init(context);
    }

    public MinMap(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MinMap(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MinMap(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){

    }
}
