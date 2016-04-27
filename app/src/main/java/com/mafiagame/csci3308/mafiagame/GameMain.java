package com.mafiagame.csci3308.mafiagame;

/**
 * Created by soorinpark on 3/4/16.
 */

import android.app.ActionBar;
import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.*;

import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ImageView;
import java.lang.reflect.Field;
import java.io.File;
import android.graphics.drawable.Drawable;
import java.io.FilenameFilter;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Gravity;
import android.widget.TextView;

/**
 * This is the main Class of MafiaGame.It deals with the game logic.
 * @author Soo Park,Jake Mitchell
 */
public class GameMain extends Activity {

    List<String> playerIcons = new ArrayList<String>();
    int cycleNum = 0;
    int mafiaLeft = 0;
    int targetId = -1;

    /**
     * This function can be called only once. set the view to the game_main layout.
     * It initialize the game and setup the role for every member.
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_main);


        /**
         * role setup
         */
        Bundle b = this.getIntent().getExtras();
        String numPlayers = b.getString("num_players");
        String yourRole = b.getString("your_role");

        Log.d("numPlayers", numPlayers);

        List<String> roleItems = new ArrayList<String>();
        roleItems.add("Detective");
        roleItems.add("Doctor");
        roleItems.add("Mafia");
        roleItems.add("Villager");

        AlertDialog.Builder yourRoleIs = new AlertDialog.Builder(this);

        if (yourRole.equals("Random")) {

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

        } else {

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

        final RelativeLayout relativeLayout= new RelativeLayout(this);

        //final Button helpButton = new Button(this);
        final ImageView helpButton = new ImageView(this);
        helpButton.setImageResource(R.drawable.help_helpicon);
        RelativeLayout.LayoutParams helpParam = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        helpParam.setMargins(930, -100, 0, 0);
        helpParam.width = 100;
        helpButton.setLayoutParams(helpParam);
        relativeLayout.addView(helpButton);

        final ImageView exitButton = new ImageView(this);


        helpButton.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                LayoutInflater layoutInflater
                        = (LayoutInflater) getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.how_to_play_in_game, null);
                final PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT);

                helpButton.setImageResource(R.drawable.help_helpicon);

                ImageView btnDismiss = (ImageView) popupView.findViewById(R.id.exitButton);
                btnDismiss.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v){
                        popupWindow.dismiss();
                    }
                });

                popupWindow.showAsDropDown(helpButton, 50, -30);

            }
        });






        addPlayers(numPlayers, relativeLayout);

        // choose cycle for logic
        //cycleNum++; //if it's odd, cycle is night. even, cycle is day.
        String cycle;
        final ImageView cycleImg = new ImageView(this);
        cycleImg.bringToFront();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);
        params.width = 400;
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        cycleImg.setLayoutParams(params);

        String mafiaKill;
        String detectiveKill;
        String doctorSave;
        String villagerKill;


        if (cycleNum % 2 == 0) {
            cycleImg.setImageResource(R.drawable.moon);
            relativeLayout.addView(cycleImg);
            //villagerKill = villagerAction(); // two or more people must choose the same person to eliminate
        }
        else {
            cycleImg.setImageResource(R.drawable.sun);
            relativeLayout.addView(cycleImg);

            //if (yourRole == "Mafia") mafiaKill = mafiaAction(); // choose a victim. Display all other mafia.
            //else if (yourRole == "Detective") detectiveKill = detectiveAction(); // choose mafia. if mafia, mafia is dead. if not, he knows whos innocent.
            //else if (yourRole == "Doctor") doctorSave = doctorAction(); // choose who to save

        }
        cycleImg.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                endTurn(relativeLayout, cycleImg);
            }
        });

        final ImageView targetImage = new ImageView(this);

    }

    private void endTurn(RelativeLayout relativeLayout, ImageView cycleImg){
        if (cycleNum % 2 == 0) {
            relativeLayout.removeView(cycleImg);
            cycleImg.setImageResource(R.drawable.moon);
            relativeLayout.addView(cycleImg);
            cycleNum++;
            //villagerKill = villagerAction(); // two or more people must choose the same person to eliminate
        }
        else {
            relativeLayout.removeView(cycleImg);
            cycleImg.setImageResource(R.drawable.sun);
            relativeLayout.addView(cycleImg);
            cycleNum++;
            //if (yourRole == "Mafia") mafiaKill = mafiaAction(); // choose a victim. Display all other mafia.
            //else if (yourRole == "Detective") detectiveKill = detectiveAction(); // choose mafia. if mafia, mafia is dead. if not, he knows whos innocent.
            //else if (yourRole == "Doctor") doctorSave = doctorAction(); // choose who to save
        }
    }

    private String villagerAction(){


        return "0";

    }

    private String doctorAction(){


        return "0";

    }

    private String mafiaAction(){


        return "0";

    }

    private String detectiveAction(){


        return "0";

    }

    public int playerStrToInt(String numPlayers){
        int players = 0;

        if (numPlayers.equals("6 (1 mafia)"))players = 6;
        else if (numPlayers.equals("10 (2 mafias)")) players = 10;
        else if (numPlayers.equals("14 (3 mafias)")) players = 14;
        return players;
    }

    public void addTargetImage(RelativeLayout relativeLayout, RelativeLayout.LayoutParams params, ImageView targetImage){
        if (targetImage != null) {
        if (targetImage.getParent() != null) {
            relativeLayout.removeView(targetImage);
        }}
        else {
            targetImage = new ImageView(this);
        }
        targetImage.setId(R.id.target);
        targetImage.setImageResource(R.drawable.square_targeting_image);
        targetImage.setLayoutParams(params);
        relativeLayout.addView(targetImage);
    }

    private void addPlayers(String numPlayers, final RelativeLayout relativeLayout){

        int players = playerStrToInt(numPlayers);

        // visual setup
        List<String> playerIcon = new ArrayList<String>();
        playerIcon.add("man_sil");
        playerIcon.add("Doctor");
        playerIcon.add("Mafia");
        playerIcon.add("Villager");

        /* Don't need this anymore but just in case.
        ArrayList<String> drawableList = new ArrayList<>();
        drawableList.add("player_detective"); //0
        drawableList.add("player_doctor"); //1
        drawableList.add("player_man_1"); //2
        drawableList.add("player_woman_1");
        drawableList.add("player_man_2");
        drawableList.add("player_woman_2");
        drawableList.add("player_man_3");
        drawableList.add("player_woman_3");
        */

        // table
        RelativeLayout.LayoutParams ovalParams = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);

        ImageView oval = new ImageView(this);
        oval.setId(R.id.oval);
        oval.setImageResource(R.drawable.oval_from_above);
        ovalParams.setMargins(0, 0, 0, 0);
        ovalParams.width = 1000;
        ovalParams.addRule(RelativeLayout.CENTER_VERTICAL);
        ovalParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        oval.setLayoutParams(ovalParams);

        // if there are 6 players
        if (players == 6) {

            final RelativeLayout.LayoutParams character3params = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);

            final ImageView character3 = new ImageView(this);
            character3.setImageResource(R.drawable.player_man_3);
            //imageView1.setId(1);
            character3.setId(R.id.character3);
            character3params.setMargins(0, 20, 0, 0);
            character3params.width = 200;
            character3params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            character3params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            character3.setLayoutParams(character3params);
            character3.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    targetId = character3.getId();
                    addTargetImage(relativeLayout, character3params, (ImageView) findViewById(R.id.target));
                }});


            RelativeLayout.LayoutParams playerParams = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            final ImageView playerImage = new ImageView(this);
            playerImage.setId(R.id.playerIcon);
            playerImage.setImageResource(R.drawable.player_woman_3);
            playerParams.setMargins(0, 0, 0, 0);
            playerParams.width = 300;
            playerParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            playerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            playerImage.setLayoutParams(playerParams);

            RelativeLayout.LayoutParams youParams = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            ImageView youImage = new ImageView(this);
            //imageView1.setId(2);
            youImage.setId(R.id.youIcon);
            youImage.setImageResource(R.drawable.player_you);
            youParams.setMargins(0, 0, 0, 0);
            youParams.width = 150;
            youParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            youParams.addRule(RelativeLayout.RIGHT_OF, R.id.playerIcon);
            youImage.setLayoutParams(youParams);




            // left side icons top to bottom
            final RelativeLayout.LayoutParams character2params = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            final ImageView character2 = new ImageView(this);
            //imageView1.setId(3);
            character2.setId(R.id.character2);
            character2.setImageResource(R.drawable.player_man_1);
            character2params.setMargins(20, 350, 0, 0);
            character2params.width = 200;
            character2params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            //params3.addRule(RelativeLayout.CENTER_HORIZONTAL);
            character2.setLayoutParams(character2params);
            character2.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    targetId = character2.getId();
                    addTargetImage(relativeLayout, character2params, (ImageView) findViewById(R.id.target));
                }
            });


            final RelativeLayout.LayoutParams character1params = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            final ImageView character1 = new ImageView(this);
            //imageView1.setId(4);
            character1.setId(R.id.character1);
            character1.setImageResource(R.drawable.player_woman_1);
            character1params.setMargins(20, 850, 0, 0);
            character1params.width = 200;
            character1params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            //params4.addRule(RelativeLayout.CENTER_HORIZONTAL);
            character1.setLayoutParams(character1params);
            character1.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    targetId = character1.getId();
                    addTargetImage(relativeLayout, character1params, (ImageView) findViewById(R.id.target));
                }
            });




            //right side icons top to bottom
            final RelativeLayout.LayoutParams character4params = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            final ImageView character4 = new ImageView(this);
            //imageView1.setId(5);
            character4.setId(R.id.character4);
            character4.setImageResource(R.drawable.player_man_2);
            character4params.setMargins(850, 350, 0, 0);
            character4params.width = 200;
            character4params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            //params5.addRule(RelativeLayout.CENTER_HORIZONTAL);
            character4.setLayoutParams(character4params);
            character4.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    targetId = character4.getId();
                    addTargetImage(relativeLayout, character4params, (ImageView) findViewById(R.id.target));
                }
            });

            final RelativeLayout.LayoutParams character5params = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            final ImageView character5 = new ImageView(this);
            //imageView1.setId(6);
            character5.setId(R.id.character5);
            character5.setImageResource(R.drawable.player_woman_2);
            character5params.setMargins(850, 850, 0, 0);
            character5params.width = 200;
            character5params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            //params6.addRule(RelativeLayout.CENTER_HORIZONTAL);
            character5.setLayoutParams(character5params);
            character5.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    targetId = character5.getId();
                    addTargetImage(relativeLayout, character5params, (ImageView) findViewById(R.id.target));
                }
            });

            relativeLayout.addView(playerImage);
            relativeLayout.addView(oval);
            relativeLayout.addView(character3);
            relativeLayout.addView(youImage);
            relativeLayout.addView(character2);
            relativeLayout.addView(character1);
            relativeLayout.addView(character4);
            relativeLayout.addView(character5);

            mafiaLeft = 1;
        }

        // if there are 10 players
        if (players == 10){

            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);

            ImageView imageView1 = new ImageView(this);
            imageView1.setImageResource(R.drawable.player_man_3);
            params1.setMargins(0, 20, 0, 0);
            params1.width = 180;
            params1.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            params1.addRule(RelativeLayout.CENTER_HORIZONTAL);
            imageView1.setLayoutParams(params1);

            RelativeLayout.LayoutParams params0 = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            ImageView playerImage = new ImageView(this);
            playerImage.setId(R.id.playerIcon);
            playerImage.setImageResource(R.drawable.player_woman_3);
            params0.setMargins(0, 0, 0, 0);
            params0.width = 300;
            params0.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params0.addRule(RelativeLayout.CENTER_HORIZONTAL);
            playerImage.setLayoutParams(params0);

            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            ImageView youImage = new ImageView(this);
            youImage.setImageResource(R.drawable.player_you);
            params2.setMargins(0, 0, 0, 0);
            params2.width = 150;
            params2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params2.addRule(RelativeLayout.RIGHT_OF, R.id.playerIcon);
            youImage.setLayoutParams(params2);





            // left side icons top to bottom
            RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            ImageView imageView3 = new ImageView(this);
            imageView3.setImageResource(R.drawable.player_man_1);
            params3.setMargins(150, 200, 0, 0);
            params3.width = 180;
            params3.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            //params3.addRule(RelativeLayout.CENTER_HORIZONTAL);
            imageView3.setLayoutParams(params3);

            RelativeLayout.LayoutParams params4 = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            ImageView imageView4 = new ImageView(this);
            imageView4.setImageResource(R.drawable.player_woman_1);
            params4.setMargins(80, 450, 0, 0);
            params4.width = 180;
            params4.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            //params4.addRule(RelativeLayout.CENTER_HORIZONTAL);
            imageView4.setLayoutParams(params4);

            RelativeLayout.LayoutParams params5 = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            ImageView imageView5 = new ImageView(this);
            imageView5.setImageResource(R.drawable.player_man_2);
            params5.setMargins(80, 700, 0, 0);
            params5.width = 180;
            params5.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            //params5.addRule(RelativeLayout.CENTER_HORIZONTAL);
            imageView5.setLayoutParams(params5);

            RelativeLayout.LayoutParams params6 = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            ImageView imageView6 = new ImageView(this);
            imageView6.setImageResource(R.drawable.player_woman_2);
            params6.setMargins(150, 950, 0, 0);
            params6.width = 180;
            params6.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            //params6.addRule(RelativeLayout.CENTER_HORIZONTAL);
            imageView6.setLayoutParams(params6);



            // right side icons top to bottom
            RelativeLayout.LayoutParams params7 = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            ImageView imageView7 = new ImageView(this);
            imageView7.setImageResource(R.drawable.player_man_1);
            params7.setMargins(780, 200, 0, 0);
            params7.width = 180;
            params7.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            //params5.addRule(RelativeLayout.CENTER_HORIZONTAL);
            imageView7.setLayoutParams(params7);

            RelativeLayout.LayoutParams params8 = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            ImageView imageView8 = new ImageView(this);
            imageView8.setImageResource(R.drawable.player_woman_3);
            params8.setMargins(850, 450, 0, 0);
            params8.width = 180;
            params8.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            //params6.addRule(RelativeLayout.CENTER_HORIZONTAL);
            imageView8.setLayoutParams(params8);

            RelativeLayout.LayoutParams params9 = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            ImageView imageView9 = new ImageView(this);
            imageView9.setImageResource(R.drawable.player_woman_3);
            params9.setMargins(850, 700, 0, 0);
            params9.width = 180;
            params9.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            //params6.addRule(RelativeLayout.CENTER_HORIZONTAL);
            imageView9.setLayoutParams(params9);

            RelativeLayout.LayoutParams params10 = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            ImageView imageView10 = new ImageView(this);
            imageView10.setImageResource(R.drawable.player_woman_3);
            params10.setMargins(780, 950, 0, 0);
            params10.width = 180;
            params10.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            //params6.addRule(RelativeLayout.CENTER_HORIZONTAL);
            imageView10.setLayoutParams(params10);

            relativeLayout.addView(playerImage);
            relativeLayout.addView(oval);
            relativeLayout.addView(imageView1);
            relativeLayout.addView(youImage);
            relativeLayout.addView(imageView3);
            relativeLayout.addView(imageView4);
            relativeLayout.addView(imageView5);
            relativeLayout.addView(imageView6);
            relativeLayout.addView(imageView7);
            relativeLayout.addView(imageView8);
            relativeLayout.addView(imageView9);
            relativeLayout.addView(imageView10);

            mafiaLeft = 2;

        }

        setContentView(relativeLayout);

    }



}
