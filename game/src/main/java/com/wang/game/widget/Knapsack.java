package com.wang.game.widget;

import android.content.Context;
import android.view.Gravity;
import android.widget.Button;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wang.game.R;
import com.wang.game.base.CommonDialog;
import com.wang.game.base.CommonRecyclerViewAdapter;

import java.util.ArrayList;

public class Knapsack{


    public void show(Context context, Lifecycle lifecycle) {
        CommonDialog commonDialog = new CommonDialog.Build(context, lifecycle)
                .setLayout(R.layout.knapsack)
                .setHeight(800)
                .setWidth(800)
                .setCanceledOnTouchOutside(true)
                .setGravity(Gravity.CENTER)
                .build();


        RecyclerView content = commonDialog.findViewById(R.id.content);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("物品1");
        strings.add("物品2");
        strings.add("物品3");
        strings.add("物品4");
        strings.add("物品5");

        CommonRecyclerViewAdapter<String> commonRecyclerViewAdapter = new CommonRecyclerViewAdapter.Build<String>()
                .setContext(context)
                .setLayoutId(R.layout.knapsack_item)
                .setDataList(strings)
                .build();

        commonRecyclerViewAdapter.setCommonRecyclerViewAdapterBackCall((holder, position) -> {
            Button button = holder.getView(R.id.button2);
            button.setText(strings.get(position));
        });

        content.setLayoutManager(new LinearLayoutManager(context));
        content.setAdapter(commonRecyclerViewAdapter);

        commonDialog.show();

    }


}
