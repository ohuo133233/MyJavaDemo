package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;


import com.example.chat.base.CommonFragmentAdapter;
import com.example.chat.chat.ChatListFragment;
import com.example.chat.friends.FriendsFragment;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private ViewPager2 mViewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
    }

    private void initView() {
        ChatListFragment chatFragment = new ChatListFragment();
        FriendsFragment friendsFragment = new FriendsFragment();

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

        fragmentArrayList.add(chatFragment);
        fragmentArrayList.add(friendsFragment);
        CommonFragmentAdapter commonFragmentAdapter = new CommonFragmentAdapter(this, fragmentArrayList);

        mViewPager2 = findViewById(R.id.view_page);
        mViewPager2.setAdapter(commonFragmentAdapter);
    }
}