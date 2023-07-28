package com.example.camera;

import android.hardware.Camera;
import android.view.SurfaceHolder;

import java.io.IOException;

public interface ICamera {

    /**
     * 打开相机
     */
    void open();

    /**
     * 关闭相机
     */
    void coles();

    /**
     * 设置相机预览方向
     */
    void setDisplayOrientation(int orientation);


    /**
     * 设置相机预览图像数据流的方向
     */
    void setOrientation(int orientation);

    /**
     * 设置闪光模式。
     */
    void setFlashMode(String value);

    /**
     * 返回相机数量
     */
    int getCameraNumber();

    Camera.Parameters getParameters();
    void setParameters(Camera.Parameters params);


    void setPreviewDisplay(SurfaceHolder holder) throws IOException;

    void stopPreview();

    /**
     * 预览图像
     */
    void setPreviewCallback(Camera.PreviewCallback previewCallback);

    /**
     * 相机的画面
     */
    void setCameraCallback(Camera.PreviewCallback cb);

    void takePicture();

}
