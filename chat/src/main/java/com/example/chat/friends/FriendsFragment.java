package com.example.chat.friends;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chat.R;
import com.example.chat.base.common.CommonRecyclerViewAdapter;
import com.example.chat.base.common.CommonRecyclerViewAdapterBackCall;
import com.example.chat.base.common.CommonRecyclerViewHolder;

import java.util.ArrayList;


public class FriendsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_friends, container, false);
        initView(layout);
        return layout;
    }

    private void initView(View layout) {

        RecyclerView recyclerView = layout.findViewById(R.id.friends_list);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("1");
        strings.add("1");
        strings.add("1");


        CommonRecyclerViewAdapter<String> recyclerViewAdapter = new CommonRecyclerViewAdapter.Build<String>()
                .setContext(requireContext())
                .setLayoutId(R.layout.friends_item)
                .setDataList(strings)
                .build();

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerViewAdapter.setCommonRecyclerViewAdapterBackCall(new CommonRecyclerViewAdapterBackCall() {
            @Override
            public void onBindViewHolder(@NonNull CommonRecyclerViewHolder holder, int position) {

            }
        });

    }
}