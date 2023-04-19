package com.example.chat.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat.R;
import com.example.chat.base.common.CommonRecyclerViewAdapter;
import com.example.chat.base.common.CommonRecyclerViewAdapterBackCall;
import com.example.chat.base.common.CommonRecyclerViewHolder;

import java.util.ArrayList;


public class ChatListFragment extends Fragment {
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_chat_list, container, false);
        initView(layout);
        return layout;
    }
    private final int one = 1;
    private final int two = 2;
    private void initView(View layout) {
        mRecyclerView = layout.findViewById(R.id.chat_list);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("测试数据");
        strings.add("测试数据");
        strings.add("测试数据");
        strings.add("测试数据");

        CommonRecyclerViewAdapter<String> commonRecyclerViewAdapter = new CommonRecyclerViewAdapter.Build<String>()
                .setContext(requireContext())
                .setDataList(strings)
                .setLayoutId(R.layout.chat_item)
                .build();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        mRecyclerView.setAdapter(commonRecyclerViewAdapter);

        commonRecyclerViewAdapter.setCommonRecyclerViewAdapterBackCall(new CommonRecyclerViewAdapterBackCall() {
            @Override
            public void onBindViewHolder(@NonNull CommonRecyclerViewHolder holder, int position) {
                View itemView = holder.getItemView();
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(requireContext(), ChatActivity.class));
                    }
                });
            }
        });
    }
}