package com.example.camera.camera1;

import android.hardware.Camera;
import android.view.SurfaceHolder;

import java.io.IOException;

public interface ICamera1 {

    /**
     * 打开相机
     */
    void open();
    /**
     * 打开相机
     */
    void open(int cameraId);

    /**
     * 关闭相机
     */
    void coles();

    /**
     * 设置相机预览方向
     */
    void setDisplayOrientation(int orientation);

    /**
     * 设置闪光模式。
     */
    void setFlashMode(String value);

    /**
     * 返回相机数量
     */
    int getCameraNumber();

    /**
     *
     * @return 返回相机参数
     */
    Camera.Parameters getParameters();

    /**
     * 设置相机参数
     * @param params 相机参数
     */
    void setParameters(Camera.Parameters params);

    /**
     * 设置预览方向
     * @param holder SurfaceHolder
     * @throws IOException
     */
    void setPreviewDisplay(SurfaceHolder holder) throws IOException;

    /**
     * 开始预览
     */
    void startPreview();

    /**
     * 停止预览
     */
    void stopPreview();

    /**
     * 预览图像
     */
    void setPreviewCallback(Camera.PreviewCallback previewCallback);

    /**
     * 相机的画面
     */
    void setCameraCallback(Camera.PreviewCallback cb);

    /**
     * 拍照
     */
    void takePicture();

}
