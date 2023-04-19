package com.example.chat.base.common;

import android.view.View;
import android.view.ViewGroup;

public interface IMutableItemView {
    int getItemViewType(int position);

    int onCreateViewHolder(ViewGroup parent, int viewType);
}
