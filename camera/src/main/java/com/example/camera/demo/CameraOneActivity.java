package com.example.camera.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.example.camera.R;
import com.example.camera.camera1.Camera1;
import com.wang.logtools.KLog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class CameraOneActivity extends AppCompatActivity implements View.OnClickListener {
    private Camera1 mCamera1;
    private SurfaceView mSurfaceView;
    private static final String TAG = CameraOneActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_one);

        mSurfaceView = findViewById(R.id.surface_view);
        Button screen_switching = findViewById(R.id.screen_switching);

        screen_switching.setOnClickListener(this);

        mCamera1 = new Camera1();
        mCamera1.open();

        mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                try {

                    mCamera1.setPreviewDisplay(mSurfaceView.getHolder());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.screen_switching:
                mCamera1.stopPreview();
                mCamera1.setCameraCallback((data, camera) -> {
                    KLog.d(TAG, "onPreviewFrame: " + data.length);
                    drawCameraDataToSurfaceView(data);
                });
                break;
        }
    }


    private void drawCameraDataToSurfaceView(byte[] data) {
        try {
            // 获取相机预览参数
            Camera.Parameters parameters = mCamera1.getParameters();
            int width = parameters.getPreviewSize().width;
            int height = parameters.getPreviewSize().height;

            // 创建YuvImage对象来处理NV21格式的相机数据
            YuvImage yuvImage = new YuvImage(data, parameters.getPreviewFormat(), width, height, null);

            // 将YuvImage转换为ByteArrayOutputStream
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            yuvImage.compressToJpeg(new Rect(0, 0, width, height), 100, byteArrayOutputStream);

            // 将ByteArrayOutputStream转换为byte数组
            byte[] jpegData = byteArrayOutputStream.toByteArray();

            // 将byte数组转换为Bitmap
            Bitmap bitmap = BitmapFactory.decodeByteArray(jpegData, 0, jpegData.length);

            // 获取SurfaceView的Canvas对象
            Canvas canvas = mSurfaceView.getHolder().lockCanvas();

            // 绘制Bitmap到SurfaceView上
            canvas.drawBitmap(bitmap, 0, 0, null);

            // 解锁Canvas并提交绘制
            mSurfaceView.getHolder().unlockCanvasAndPost(canvas);

            // 释放资源
            byteArrayOutputStream.close();
            bitmap.recycle();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 获取最适合的预览大小
    private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int width, int height) {
        final double ASPECT_TOLERANCE = 0.1;
        double targetRatio = (double) width / height;

        Camera.Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        for (Camera.Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
                continue;
            if (Math.abs(size.height - height) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - height);
            }
        }

        // 如果没有找到合适的比例，则选择最小差异的尺寸
        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - height) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - height);
                }
            }
        }

        return optimalSize;
    }

}