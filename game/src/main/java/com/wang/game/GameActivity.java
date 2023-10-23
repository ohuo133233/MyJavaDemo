package com.wang.game;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.wang.game.map.Map;
import com.wang.game.player.Player;
import com.wang.game.ui.UI;

public class GameActivity extends AppCompatActivity {

    private final String TAG = GameActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        Map map = findViewById(R.id.map);

        UI ui = findViewById(R.id.ui);
        ui.setMap(map);


        Player player = findViewById(R.id.player);

    }

}