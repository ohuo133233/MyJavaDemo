package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.example.chat.base.common.CommonFragmentAdapter;
import com.example.chat.chat.ChatListFragment;
import com.example.chat.friends.FriendsFragment;
import com.example.chat.more.MoreFragment;
import com.example.chat.my.MyFragment;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "HomeActivity";
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
        chat.setOnClickListener(this);
        friends.setOnClickListener(this);
        more.setOnClickListener(this);
        my.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chat:
                mViewPager2.setCurrentItem(0);
                break;
            case R.id.friends:
                mViewPager2.setCurrentItem(1);
                break;
            case R.id.more:
                mViewPager2.setCurrentItem(2);
                break;
            case R.id.my:
                mViewPager2.setCurrentItem(3);
                break;
            default:
                Log.e(TAG, "错误");
                break;
        }
    }
}