package com.example.mediaplayer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;


public class PlayerSeekBar extends androidx.appcompat.widget.AppCompatSeekBar {


    public PlayerSeekBar(Context context) {
        super(context);
    }

    public PlayerSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayerSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void attach(Player player) {



    }




}
