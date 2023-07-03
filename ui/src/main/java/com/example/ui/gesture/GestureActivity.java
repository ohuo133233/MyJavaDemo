package com.example.ui.gesture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.ui.R;

public class GestureActivity extends AppCompatActivity {
    private ImageView mImageView;
    private float lastX;
    private float lastY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);

        mImageView = findViewById(R.id.image);


    }


    @Override
    public boolean onTouchEvent( MotionEvent event) {
        float currentX = event.getRawX();
        float currentY = event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = currentX;
                lastY = currentY;
                break;

            case MotionEvent.ACTION_MOVE:
                float deltaX = currentX - lastX;
                float deltaY = currentY - lastY;



                // 计算新的位置
                float newX = mImageView.getX() + deltaX;
                float newY = mImageView.getY() + deltaY;

                // 更新视图的位置
                mImageView.setX(newX);
                mImageView.setY(newY);

                lastX = currentX;
                lastY = currentY;
                break;
        }


        return true;
    }

}