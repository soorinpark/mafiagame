package com.mafiagame.csci3308.mafiagame;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.ImageView;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */

public class testMainActivity extends ActivityInstrumentationTestCase2<MainActivity> {
    MainActivity testMainActivity;

    public testMainActivity() {
        super(MainActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        testMainActivity = getActivity();
    }

    public void test_image_views() {

    }

    public void test_about_button(){
        Button action_button = (Button) testMainActivity.findViewById(R.id.aboutButton);
        assertNotNull(action_button);
    }
    public void test_new_game_button(){
        Button action_button = (Button) testMainActivity.findViewById(R.id.newGameButton);
        assertNotNull(action_button);
    }
    public void test_settings_button(){
        Button action_button = (Button) testMainActivity.findViewById(R.id.settingsButton);
        assertNotNull(action_button);
    }
    public void test_htp_button(){
        Button action_button = (Button) testMainActivity.findViewById(R.id.htpButton);
        assertNotNull(action_button);
    }

}
