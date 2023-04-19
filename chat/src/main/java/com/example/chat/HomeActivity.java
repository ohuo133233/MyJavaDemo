package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.chat.base.common.CommonFragmentAdapter;
import com.example.chat.chat.ChatListFragment;
import com.example.chat.friends.FriendsFragment;
import com.example.chat.more.MoreFragment;
import com.example.chat.my.MyFragment;

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
        MoreFragment moreFragment = new MoreFragment();
        MyFragment myFragment = new MyFragment();

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

        fragmentArrayList.add(chatFragment);
        fragmentArrayList.add(friendsFragment);
        fragmentArrayList.add(moreFragment);
        fragmentArrayList.add(myFragment);
        CommonFragmentAdapter commonFragmentAdapter = new CommonFragmentAdapter(this, fragmentArrayList);

        mViewPager2 = findViewById(R.id.view_page);
        mViewPager2.setAdapter(commonFragmentAdapter);


        Button chat = findViewById(R.id.chat);
        Button friends = findViewById(R.id.friends);
        Button more = findViewById(R.id.more);
        Button my = findViewById(R.id.my);


        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager2.setCurrentItem(0);
            }
        });
        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager2.setCurrentItem(1);
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager2.setCurrentItem(2);
            }
        });
        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager2.setCurrentItem(3);
            }
        });
    }
}