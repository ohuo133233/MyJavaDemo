package com.wang.game;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.wang.game.map.Map;
import com.wang.game.player.Player;
import com.wang.game.ui.UI;
import com.wang.game.widget.Dialogue;
import com.wang.game.widget.Knapsack;

import java.util.ArrayList;

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


        Player player = map.findViewById(R.id.player);


    }

    public void showDialogue() {
        Dialogue dialogue = new Dialogue();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("第一句话");
        strings.add("第二句话");
        strings.add("第三句话");
        strings.add("第四句话");
        strings.add("第五句话");
        dialogue.show(this, getLifecycle(), "系统", strings);
    }

    public void showKnapsack() {
        Knapsack knapsack = new Knapsack();
        knapsack.show(this, getLifecycle());
    }

}