package com.mafiagame.csci3308.mafiagame;

/**
 * Created by soorinpark on 3/4/16.
 */

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

/**
 * This is the Activity that deal with the background information about Mafia Game
 * @author Camille Owens, Soo Park,  Jake Mitchell, Alex Sheehan,  Qi Pei
 */


public class About extends Activity {

    Button button;

    /**
    * This function can be called only once. set the view to the About layout
    * @param savedInstanceState
    */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
    }

}
