package com.mafiagame.csci3308.mafiagame;

/**
 * Created by soorinpark on 3/4/16.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.content.Intent;
import android.util.Log;

public class NewGame extends Activity {

    Button start_game_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game);

        Spinner numPlayers = (Spinner)findViewById(R.id.numPlayers);
        String[] items = new String[]{"6 (1 mafia)", "10 (2 mafias)", "14 (3 mafias)"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        numPlayers.setAdapter(adapter);
        String numPlayersVal = numPlayers.getSelectedItem().toString();

        Intent intent = new Intent(this, GameMain.class);
        intent.putExtra("num_players", numPlayersVal);
        //startActivity(intent);

        Spinner roles = (Spinner)findViewById(R.id.yourRole);
        String[] roleItems = new String[]{"Random", "Detective", "Doctor", "Mafia", "Villager"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, roleItems);
        roles.setAdapter(adapter1);

        addListenerOnButton();


    }

    public void addListenerOnButton() {

        final Context context = this;

        start_game_button = (Button) findViewById(R.id.startButton);

        start_game_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, GameMain.class);
                startActivity(intent);

            }

        });
        Log.d("1", "1");
    }





}
