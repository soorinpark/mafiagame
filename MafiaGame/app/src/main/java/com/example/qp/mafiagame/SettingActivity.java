package com.example.qp.mafiagame;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        gobackButton();
    }

    private void gobackButton(){
        Button messageButton = (Button) findViewById(R.id.setGobackBut);
        messageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
           startActivity(new Intent(SettingActivity.this, MainActivity.class));
            }
        });
    }
}
