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
import java.lang.Object;
import android.view.ViewGroup;
import android.widget.AdapterView;

public class NewGame extends Activity {

    Button start_game_button;
    String numPlayersVal;
    String yourRoleSelected;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game);

        final Spinner numPlayers = (Spinner)findViewById(R.id.numPlayers);
        String[] items = new String[]{"6 (1 mafia)", "10 (2 mafias)"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        numPlayers.setAdapter(adapter);

        final Spinner roles = (Spinner)findViewById(R.id.yourRole);
        String[] roleItems = new String[]{"Random", "Detective", "Doctor", "Mafia", "Villager"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, roleItems);
        roles.setAdapter(adapter1);

        roles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                numPlayersVal = numPlayers.getSelectedItem().toString();
                yourRoleSelected = roles.getSelectedItem().toString();

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button start_game_button = (Button) findViewById(R.id.startButton);
        start_game_button.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {

                numPlayersVal = numPlayers.getSelectedItem().toString();
                yourRoleSelected = roles.getSelectedItem().toString();

                Intent intent = new Intent(NewGame.this, GameMain.class);
                intent.putExtra("num_players", numPlayersVal);
                intent.putExtra("your_role", yourRoleSelected);
                Log.d("2", yourRoleSelected);
                NewGame.this.startActivity(intent);

            }
        });
    }





}
