package com.example.camera.camerax;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.camera.R;
import com.wang.recyclerview.CommonRecyclerViewAdapter;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        ArrayList<String> strings = new ArrayList<>();
        strings.add("水印");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        CommonRecyclerViewAdapter<String> commonRecyclerViewAdapter = new CommonRecyclerViewAdapter.Build<String>()
                .setContext(this)
                .setLayoutId(R.layout.setting_item)
                .setDataList(strings)
                .build();

        commonRecyclerViewAdapter.setCommonRecyclerViewAdapterBackCall((holder, position) -> {
            TextView textView = holder.getView(R.id.text);

            textView.setText(strings.get(position));

            holder.getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        });

        recyclerView.setAdapter(commonRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}