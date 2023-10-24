package com.wang.game.widget;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Lifecycle;

import com.wang.game.R;
import com.wang.game.base.CommonDialog;

import java.util.ArrayList;

public class Dialogue {

    private int onClickSize;

    public void show(Context context, Lifecycle lifecycle, String name, ArrayList<String> message) {
        CommonDialog commonDialog = new CommonDialog.Build(context, lifecycle)
                .setLayout(R.layout.dialogue)
                .setGravity(Gravity.BOTTOM | Gravity.CENTER)
                .build();

        TextView nameText = commonDialog.findViewById(R.id.name);
        TextView messageText = commonDialog.findViewById(R.id.message);
        ConstraintLayout dialogue = commonDialog.findViewById(R.id.dialogue);

        nameText.setText(name);
        messageText.setText(message.get(0));

        dialogue.setOnClickListener(v -> {
            if (message.size() == onClickSize) {
                commonDialog.dismiss();
                return;
            }
            messageText.setText(message.get(onClickSize++));
        });

        commonDialog.show();
    }
}
