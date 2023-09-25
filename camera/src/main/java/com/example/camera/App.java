package com.example.camera;

import android.app.Application;
import android.util.Log;

import androidx.camera.camera2.Camera2Config;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraXConfig;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


    }


    public CameraXConfig getCameraXConfig() {
        // 返回用于初始化 CameraX 实例的配置
        return CameraXConfig.Builder.fromConfig(Camera2Config.defaultConfig())
                // 设置CameraSelector以确定可用的摄像头
                // 此配置可以帮助 CameraX 优化 CameraX 初始化的延迟。
                // CameraX 初始化执行的任务包括枚举相机、查询相机特性和检索属性，为分辨率确定做好准备。在某些低端设备上，这些任务可能需要大量时间。
                // 使用该方法可以避免不必要的相机初始化，加快相机启动时间。
                // 例如，如果应用程序仅使用后置摄像头，则可以设置此配置，DEFAULT_BACK_CAMERA然后 CameraX 将避免初始化前置摄像头以减少延迟。
                .setAvailableCamerasLimiter(CameraSelector.DEFAULT_FRONT_CAMERA)
                //  更改日志记录级别
                .setMinimumLoggingLevel(Log.ERROR).build();
    }
}
