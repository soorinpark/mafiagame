package com.example.qp.mafiagame;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_main_menu);
        setupSettingButton();
    }

    private void setupSettingButton() {
        Button setButton = (Button) findViewById(R.id.setBut);
        Button backgroundButton = (Button) findViewById(R.id.backBut);
        Button howButton = (Button) findViewById(R.id.howBut);
        Button newButton = (Button) findViewById(R.id.newBut);

        setButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, SettingActivity.class));
            }
        });

        backgroundButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, BackgroundActivity.class));
            }
        });

        howButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, HowActivity.class));
            }
        });

        newButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, NewGameActivity.class));
            }
        });
    }
}
