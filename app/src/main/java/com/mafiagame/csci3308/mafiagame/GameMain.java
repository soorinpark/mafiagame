package com.mafiagame.csci3308.mafiagame;

/**
 * Created by soorinpark on 3/4/16.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class GameMain extends Activity {

    TextView final_num_players;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_main);

        //String numPlayers = getIntent().getStringExtra("num_players");
        //final_num_players = (TextView) findViewById(R.id.finalNumPlayers);
        //final_num_players.setText(numPlayers);
        //setContentView(tv);

    }



}
