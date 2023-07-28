package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

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
    }
}