package com.example.webview;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);

        // 允许 JavaScript 执行
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // 在 WebView 中显示网页，此处加载百度首页
        webView.loadUrl("https://www.baidu.com/");

        // 设置 WebChromeClient，用于加载进度显示等
        webView.setWebChromeClient(new WebChromeClient());

        webView.loadUrl("https://weixin.woniuxx.com/elecHome/?t=1708587645/#/pages/index?" +
                "schoolId=" +"1" +
                "&classId=+" + "1" +
                "&className=" +"1" +
                "&deviceId=" +"1");


        /**
         *下载风灵月影
         *
         * mod整合
         *
          请输入向右移动的按键，推荐D
            您输入的D，请确定。 if==A，提示你输入的是A，通常是向左移动的，是不是设置反了
          请输入向左移动的按键，推荐A
            您输入的D，请确定。 if==D，提示你输入的是D，通常是向右移动的，是不是设置反了
          请输入向上看或者向前移动的按键，推荐W
            您输入的W，请确定。 if==S，提示你输入的是S，通常是向下看或者向后移动的，是不是设置反了
         请输入向下看或者向后移动的按键，推荐W
            您输入的S，请确定。 if==W，提示你输入的是W，通常是向上看或者向前移动的，是不是设置反了
         请输入跳跃按键，推荐空格键
            您输入的空格键，请确定。
         请输入普通攻击，推荐鼠标左键
            您输入的鼠标左键，请确定。
         请输入加速闪避，推荐shift键
            您输入的shift键，请确定。
         请输入下蹲，推荐ctrl键
            您输入的ctrl键，请确定。
         请输入打开地图，推荐M键
            您输入的M键，请确定。
         请输入武器选择，推荐Q键
            您输入的Q键，请确定。
         请输入技能，推荐E键
            您输入的E键，请确定。
         请输入背包，推荐B键
            您输入的B键，请确定。
         请输入菜单，推荐TAB键
            您输入的TAB键，请确定。

         */


    }
}