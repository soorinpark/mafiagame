package com.mafiagame.csci3308.mafiagame;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

/**
 * This is the Opening activity. It sets up the welcome page for the game.
 * @author Qi Pei
 */
public class OpeningActivity extends Activity {
    OpeningView openingView = null;
    OpeningRunThread openingRunThread = null;

    boolean isSound = true;
    MediaPlayer startSound;

    /**
     * This function can be called only once.
     * This function also initializes the welcome page. Setting up the background music and calling the goOpeningView function
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        startSound = MediaPlayer.create(this, R.raw.speak);
        startSound.setLooping(true);

        goOpeningView();
    }

    /**
     * The purpose of this function is to change the activity from opening to main menu.
     */
    public void goMenuActivity(){
        startActivity(new Intent(OpeningActivity.this, MainActivity.class));
    }

    /**
     * The purpose of this function is to start the openingRunThread and the background music.
     */
    public void goOpeningView(){
        openingView = new OpeningView(this);
        if(isSound){
            startSound.start();
        }
        this.setContentView(openingView);
        openingRunThread = new OpeningRunThread(this);
        openingRunThread.start();
    }

    /**
     * This is an inner class. It is a handler to change the thread from the welcome page to the main menu activity. It also pause the music of the welcome part.
     * If the massage equal to one then stop the opening thread and call goMenuActivity to change the activity. It also stop the background music.
     */
    Handler myHandler = new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what == 1){
                openingRunThread.setFlag(false);
                if(openingView != null){
                    openingView = null;
                }
                goMenuActivity();
                startSound.pause();
            }
        }
    };
}
