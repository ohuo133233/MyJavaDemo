package com.example.camera.camerax;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Size;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.example.camera.R;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutionException;

public class QRCodeActivity extends AppCompatActivity {
    private final String TAG = QRCodeActivity.class.getSimpleName();
    private BarcodeScanner mBarcodeScanner;
    private PreviewView mPreviewView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        // 初始化 CameraX 和 ML Kit
        initializeCameraX();
        initializeMLKit();

        mPreviewView = findViewById(R.id.preview_view);
    }

    private void initializeCameraX() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindCameraUseCases(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                // 处理异常
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private BarcodeScanner barcodeScanner;

    private void initializeMLKit() {
        BarcodeScannerOptions options = new BarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.FORMAT_QR_CODE, Barcode.FORMAT_AZTEC)
                .build();

        barcodeScanner = BarcodeScanning.getClient(options);
    }


    private void bindCameraUseCases(ProcessCameraProvider cameraProvider) {


        Preview preview = new Preview.Builder()
                .build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner) this, cameraSelector, preview);

        preview.setSurfaceProvider(mPreviewView.getSurfaceProvider());

        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                .setTargetResolution(new Size(1920, 1080))
                .build();


        // 在 ImageAnalysis 的回调中处理图像帧
        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this), imageProxy -> {

            Bitmap bitmap = mPreviewView.getBitmap();
            // 获取图像数据
            ByteBuffer buffer = imageProxy.getPlanes()[0].getBuffer();
            byte[] data = new byte[buffer.remaining()];
            buffer.get(data);

            // 创建 InputImage 对象
           // InputImage inputImage = InputImage.fromByteArray(data, imageProxy.getWidth(), imageProxy.getHeight(), imageProxy.getImageInfo().getRotationDegrees(), InputImage.IMAGE_FORMAT_BITMAP);
            InputImage inputImage = InputImage.fromBitmap(bitmap, 0);
            // 使用 ML Kit 来识别二维码
            barcodeScanner.process(inputImage).addOnSuccessListener(barcodes -> {
                // 处理识别到的条码（二维码）
                for (Barcode barcode : barcodes) {
                    String qrCodeText = barcode.getDisplayValue();
                    handleQRCode(qrCodeText);
                }
            }).addOnFailureListener(e -> {
                // 处理识别失败的情况
                e.printStackTrace();
            }).addOnCompleteListener(task -> {
                imageProxy.close();
            });
        });

        // 绑定 ImageAnalysis Use Case
        cameraProvider.bindToLifecycle((LifecycleOwner) this, cameraSelector, imageAnalysis);
    }

    private void handleQRCode(String qrCodeText) {
        // 在此处处理识别到的二维码内容
        runOnUiThread(() -> {
            Toast.makeText(this, "QR Code: " + qrCodeText, Toast.LENGTH_SHORT).show();
        });
    }


}