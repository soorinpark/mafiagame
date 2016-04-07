package com.mafiagame.csci3308.mafiagame;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageView;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */

public class testGameMain extends ActivityInstrumentationTestCase2<GameMain>{
    GameMain testGameMain;

    public testGameMain() {super(GameMain.class);}

    protected void setUp() throws Exception{
        super.setUp();
        testGameMain = getActivity();
    }

    public void test_image_views() {
        ImageView oval = (ImageView) testGameMain.findViewById(R.id.oval);
        assertNotNull(oval);
        ImageView playerIcon = (ImageView) testGameMain.findViewById(R.id.playerIcon);
        assertNotNull(playerIcon);
    }

    @Test
    public void players_str_to_int() throws Exception {
        assertEquals(6, (testGameMain.playerStrToInt("6 (1 mafia)")));
        assertEquals(10, (testGameMain.playerStrToInt("10 (2 mafias)")));
        assertEquals(14, (testGameMain.playerStrToInt("14 (3 mafias)")));
    }

    public void game_main_icons () throws Exception{

    }
}