package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ViewScaleActivity extends AppCompatActivity {
    private int duration = 500;
    private static final String TAG = "ViewScaleActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scale);

        TextView text = findViewById(R.id.text);
        Button start = findViewById(R.id.start);

        //根据id获取控件
        Spinner spinner = findViewById(R.id.spinner);

        String[] areas = {"数值", "百分数", "百分数P"};
        //创建适配器
        ArrayAdapter adapter = new ArrayAdapter(ViewScaleActivity.this, android.R.layout.simple_spinner_item, areas);

        //给下拉框设置适配器
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String choice = areas[i];
                Toast.makeText(ViewScaleActivity.this, "你的选择是" + choice, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        start.setOnClickListener(v -> {
//            animation = AnimationUtils.loadAnimation(ViewScaleActivity.this, R.anim.scalea_anim);
//            text.startAnimation(animation);

            text.startAnimation(getAnimation());
//            getDuration();
        });

    }

    private Animation getAnimation() {

        long duration = Long.parseLong(getEidTextValue(R.id.duration));
        float fromX = Float.parseFloat(getEidTextValue(R.id.from_x_scale_editText));
        float toX = Float.parseFloat(getEidTextValue(R.id.to_x_scale_edit_text));
        float fromY = Float.parseFloat(getEidTextValue(R.id.from_y_scale_editText));
        float toY = Float.parseFloat(getEidTextValue(R.id.to_y_scale_edit_text));

        Log.d(TAG, "duration: " + duration);
        Log.d(TAG, "fromX: " + fromX);
        Log.d(TAG, "toX: " + toX);
        Log.d(TAG, "fromY: " + fromY);
        Log.d(TAG, "toY: " + toY);

        ScaleAnimation scaleAnimation = new ScaleAnimation(fromX, toX, fromY, toY);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setInterpolator(new LinearInterpolator());
        return scaleAnimation;

    }


    private String getEidTextValue(int viewId) {
        EditText editText = findViewById(viewId);
        return editText.getText().toString().trim();
    }
}
