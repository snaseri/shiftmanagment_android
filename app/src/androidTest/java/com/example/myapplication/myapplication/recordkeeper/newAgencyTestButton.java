package com.example.myapplication.myapplication.recordkeeper;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class newAgencyTestButton {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivtyTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void agencySaveButtonTakesYouToShiftlog(){

        Intents.init();
        Espresso.onView(
                ViewMatchers.withId(R.id.AddAgency)
        ).perform(
                ViewActions.click()
        );

        Intents.intended(
                IntentMatchers.hasComponent(NewAgencyFragment.class.getName())
        );

        Intents.init();
        Espresso.onView(
                ViewMatchers.withId(R.id.Agency_Save_Button)
        ).perform(
                ViewActions.click()
        );

        Intents.intended(
                IntentMatchers.hasComponent(MainActivity.class.getName())
        );
        Intents.release();

    }
}

