package com.example.mediaplayer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class ItemSlideDecoration extends RecyclerView.ItemDecoration {
    private Drawable slideContent;
    private int slideOffset;

    public ItemSlideDecoration(Context context, int slideContentResId, int slideOffset) {
        slideContent = ContextCompat.getDrawable(context, slideContentResId);
        this.slideOffset = slideOffset;
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);

            int left = child.getRight();
            int right = left + slideOffset;
            int top = child.getTop();
            int bottom = child.getBottom();

            slideContent.setBounds(left, top, right, bottom);
            slideContent.draw(c);
        }
    }
}

