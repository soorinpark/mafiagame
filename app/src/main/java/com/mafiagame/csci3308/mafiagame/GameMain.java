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
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.*;


public class GameMain extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_main);
        //addListenerOnButton();

        Bundle b = this.getIntent().getExtras();
        String numPlayers = b.getString("num_players");
        String yourRole = b.getString("your_role");

        Log.d("1", yourRole);

        List<String> roleItems = new ArrayList<String>();
        roleItems.add("Detective");
        roleItems.add("Doctor");
        roleItems.add("Mafia");
        roleItems.add("Villager");

        AlertDialog.Builder yourRoleIs  = new AlertDialog.Builder(this);

        if (yourRole.equals("Random")){

            Random randomizer = new Random();
            String randomRole = roleItems.get(randomizer.nextInt(roleItems.size()));
            yourRoleIs.setMessage("You are a " + randomRole.toUpperCase());
            yourRoleIs.setTitle("Message from the Moderator");
            yourRoleIs.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });

            yourRoleIs.setCancelable(true);
            yourRoleIs.create().show();

        }
        else {

            yourRoleIs.setMessage("You are a " + yourRole.toUpperCase());
            yourRoleIs.setTitle("Message from the Moderator");
            yourRoleIs.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });

            yourRoleIs.setCancelable(true);
            yourRoleIs.create().show();
        }








    }
/*
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
    }
    */





}
