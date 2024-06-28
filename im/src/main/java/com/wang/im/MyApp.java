package com.wang.im;

import android.app.Application;
import android.util.Log;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        boolean init = IMManger.getInstance().init(this);

        Log.d("TAG","init: "+init);
    }
}
