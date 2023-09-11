package com.wang.game.map;

import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MapCriteria implements Criteria {

    private List<Rect> rectsList = new ArrayList<>();
    private String TAG = MapCriteria.class.getSimpleName();

    public void addRect(Rect rect) {
        rectsList.add(rect);
    }

    @Override
    public void filtered(int x, int y) {
        for (int i = 0; i < rectsList.size(); i++) {
            if (rectsList.get(i).contains(x, y)) {
                Log.d(TAG, "进入范围");
            } else {
                Log.d(TAG, "不在范围");
            }
        }
    }
}
