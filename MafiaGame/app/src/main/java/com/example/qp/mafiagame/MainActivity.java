package com.example.qp.mafiagame;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
    WelcomeView welcomeView = null;
    WelcomeViewGoThread welcomeViewGoThread = null;



    boolean isSound = true;
    MediaPlayer startSound;

    Handler myHandler = new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what == 1){
                welcomeViewGoThread.setFlag(false);
                if(welcomeView != null){
                    welcomeView = null;
                }
                initAndToMenuActivity();
            }
        }
    };

    public void initAndToMenuActivity(){
        startActivity(new Intent(MainActivity.this, MainMenuActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        startSound = MediaPlayer.create(this, R.raw.sound3);
        startSound.setLooping(true);

        initAndToWelcomeView();
    }

    public void initAndToWelcomeView(){
        welcomeView = new WelcomeView(this);
        if(isSound){
            startSound.start();
        }
        this.setContentView(welcomeView);
        welcomeViewGoThread = new WelcomeViewGoThread(this);
        welcomeViewGoThread.start();
    }
}

