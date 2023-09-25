package com.example.camera.camerax;

import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Size;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ExperimentalGetImage;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;

import com.example.camera.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import com.google.mlkit.vision.face.FaceLandmark;
import com.wang.logtools.KLog;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class FacesActivity extends AppCompatActivity {
    private final String TAG = FacesActivity.class.getSimpleName();
    private ProcessCameraProvider mProcessCameraProvider;
    private Preview mPreview;
    private PreviewView mPreviewView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faces);

        mPreviewView = findViewById(R.id.preview_view);

        // 高精度地标检测与人脸分类
        FaceDetectorOptions highAccuracyOpts = new FaceDetectorOptions.Builder()
                        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
                        .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
                        .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
                        .build();

        // 实时轮廓检测
        FaceDetectorOptions realTimeOpts = new FaceDetectorOptions.Builder()
                        .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
                        .build();

        // 创建预览
        mPreview = new Preview.Builder().build();
        // 设置预览的输出
        mPreview.setSurfaceProvider(mPreviewView.getSurfaceProvider());

        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                        .setTargetResolution(new Size(1280, 720))
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build();


        FaceDetector detector = FaceDetection.getClient(highAccuracyOpts);
        imageAnalysis.setAnalyzer(Executors.newSingleThreadExecutor(), new ImageAnalysis.Analyzer() {
            @Override
            @OptIn(markerClass = ExperimentalGetImage.class)
            public void analyze(@NonNull ImageProxy imageProxy) {
                int rotationDegrees = imageProxy.getImageInfo().getRotationDegrees();
                Task<List<Face>> result = detector.process(imageProxy.getImage(),0).addOnSuccessListener(
                                new OnSuccessListener<List<Face>>() {
                                    @Override
                                    public void onSuccess(List<Face> faces) {
                                        KLog.d(TAG, "onSuccess");
                                        faces(faces);

                                    }
                                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Task failed with an exception
                                // ...
                                KLog.d(TAG, "onFailure"+e.toString());
                            }
                        });
                imageProxy.close();
            }
        });
        // 获取ProcessCameraProvider  List
        ListenableFuture<ProcessCameraProvider> cameraProviderListenableFuture = ProcessCameraProvider.getInstance(this);
        try {
            mProcessCameraProvider = cameraProviderListenableFuture.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        CameraSelector cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA;
        mProcessCameraProvider.bindToLifecycle(this, cameraSelector,mPreview,imageAnalysis);


//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.face);
//        InputImage image = InputImage.fromBitmap(bitmap, 0);
    }

    private void faces(List<Face> faces) {

        for (Face face : faces) {
            KLog.d(TAG,"face： "+face.toString());
            mPreviewView.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(FacesActivity.this, "识别到人脸", Toast.LENGTH_SHORT).show();
                }
            });
            Rect bounds = face.getBoundingBox();
            float rotY = face.getHeadEulerAngleY();  // Head is rotated to the right rotY degrees
            float rotZ = face.getHeadEulerAngleZ();  // Head is tilted sideways rotZ degrees

            // If landmark detection was enabled (mouth, ears, eyes, cheeks, and
            // nose available):
            FaceLandmark leftEar = face.getLandmark(FaceLandmark.LEFT_EAR);
            if (leftEar != null) {
                PointF leftEarPos = leftEar.getPosition();
            }

//            // If contour detection was enabled:
//            List<PointF> leftEyeContour =
//                    face.getContour(FaceContour.LEFT_EYE).getPoints();
//            List<PointF> upperLipBottomContour =
//                    face.getContour(FaceContour.UPPER_LIP_BOTTOM).getPoints();

            // If classification was enabled:
            if (face.getSmilingProbability() != null) {
                float smileProb = face.getSmilingProbability();
            }
            if (face.getRightEyeOpenProbability() != null) {
                float rightEyeOpenProb = face.getRightEyeOpenProbability();
            }

            // If face tracking was enabled:
            if (face.getTrackingId() != null) {
                int id = face.getTrackingId();
                KLog.d(TAG, "ID： " + id);
            }
        }
    }

}