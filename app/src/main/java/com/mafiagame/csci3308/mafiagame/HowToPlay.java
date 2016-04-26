package com.mafiagame.csci3308.mafiagame;

/**
 * Created by soorinpark on 3/4/16.
 */

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

/**
 * This is the Activity that deal with all the rules about this game.
 * @author Camille Owens, Soo Park,  Jake Mitchell, Alex Sheehan,
 */
public class HowToPlay extends Activity {

    Button button;

    /**
     * This function can be called only once. set the view to the how_to_play layout
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.how_to_play);
    }

}
