package com.mafiagame.csci3308.mafiagame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

    Button new_game_button;
    Button settings_button;
    Button htp_button;
    Button about_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }

    public void addListenerOnButton() {

        final Context context = this;

        new_game_button = (Button) findViewById(R.id.newGameButton);

        new_game_button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, NewGame.class);
                startActivity(intent);

            }

        });

        settings_button = (Button) findViewById(R.id.settingsButton);

        settings_button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, Settings.class);
                startActivity(intent);

            }

        });

        htp_button = (Button) findViewById(R.id.htpButton);

        htp_button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, HowToPlay.class);
                startActivity(intent);

            }

        });

        about_button = (Button) findViewById(R.id.aboutButton);

        about_button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, About.class);
                startActivity(intent);

            }

        });

    }

}