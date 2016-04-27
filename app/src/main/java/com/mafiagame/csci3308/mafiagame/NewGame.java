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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.lang.Integer;

/**
 * This is the NewGame activity. It sets up the roles and the number of the players.
 * @author Soo Park, Jake Mitchell
 */
public class NewGame extends Activity {

    Button start_game_button;
    String numPlayersVal;
    String yourRoleSelected;
    Map<Integer, String> finalRoles = new HashMap<Integer, String>();

    /**
     * This function can be called only once. Setting up the view to the new_game layout.
     * This function also initializes the game.
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game);

        final Spinner numPlayers = (Spinner)findViewById(R.id.numPlayers);
        String[] items = new String[]{"6 (1 mafia)", "10 (2 mafias)"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        numPlayers.setAdapter(adapter);

        final Spinner roles = (Spinner)findViewById(R.id.yourRole);
        final String[] roleItems = new String[]{"Random", "Detective", "Doctor", "Mafia", "Villager"};
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

                List<String> newRoleList = new ArrayList<>();

                newRoleList.add("Detective");
                newRoleList.add("Doctor");
                newRoleList.add("Mafia");
                newRoleList.add("Villager1");
                newRoleList.add("Villager2");
                newRoleList.add("Villager3");

                if (numPlayersVal.equals("6 (1 mafia)")){

                    if (!yourRoleSelected.equals("Random")){

                        finalRoles.put(0, yourRoleSelected); // 0 is always you

                        for (int i = 1; i < 6; i++) {

                            long seed = System.nanoTime();
                            Collections.shuffle(newRoleList, new Random(seed));
                            newRoleList.remove(yourRoleSelected);
                            finalRoles.put(i, newRoleList.get(i-1));
                            Log.d("roleList", "role: " + i + " is " + newRoleList.get(i-1));

                        }

                    }
                    else {
                        for (int i = 0; i < 6; i++) {
                            long seed = System.nanoTime();
                            Collections.shuffle(newRoleList, new Random(seed));
                            finalRoles.put(i, newRoleList.get(i));
                            Log.d("roleList", "role: " + i + " is " + newRoleList.get(i));
                        }
                    }

                }

                Log.d("2", yourRoleSelected);

                for (Map.Entry<Integer, String> entry : finalRoles.entrySet()) {

                    Log.d("roles","playerNum = " + entry.getKey() + ", PlayerRole = " + entry.getValue());

                }

                NewGame.this.startActivity(intent);

            }
        });
    }





}
