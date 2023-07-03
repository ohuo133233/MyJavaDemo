package com.example.ui.view;

import android.content.Context;

import androidx.lifecycle.Lifecycle;

import com.example.ui.R;
import com.wang.dialog.CommonDialog;

public class ViewAnimationDialog {


    public void show(Context context,Lifecycle lifecycle){

        CommonDialog commonDialog = new CommonDialog.Build(context, lifecycle)
                .setLayout(R.layout.view_animation_layout)
                .build();
        commonDialog.show();

    }
}
