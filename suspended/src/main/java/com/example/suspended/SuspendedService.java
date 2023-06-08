package com.example.suspended;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class SuspendedService extends Service {
    public SuspendedService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 获取WindowManager
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        // 创建布局参数
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        /** 设置参数 */
        params.type = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ?
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY :
                WindowManager.LayoutParams.TYPE_PHONE;
        params.format = PixelFormat.RGBA_8888;
        // 设置窗口的行为准则
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        //设置透明度
        params.alpha = 1.0f;
        //设置内部视图对齐方式，这边位置为左边靠上
        params.gravity = Gravity.LEFT | Gravity.TOP;
        //窗口的左上角坐标
        params.x = 0;
        params.y = 0;
        //设置窗口的宽高,这里为自动
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;


        View inflate = LayoutInflater.from(this).inflate(R.layout.suspended, null);
        View button = inflate.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SuspendedService.this, "button OnClickListener", Toast.LENGTH_SHORT).show();
                Log.e("TAG", "button onClick: ");
            }
        });
        // 添加进WindowManager
        wm.addView(inflate, params);
        return super.onStartCommand(intent, flags, startId);


    }
}