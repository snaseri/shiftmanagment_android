package com.example.myapplication.myapplication.recordkeeper;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@MediumTest
public class newAgencyTestButton {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivtyTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void agencySaveButtonTakesYouToShiftlog(){

        Espresso.onView(
                ViewMatchers.withId(R.id.AddAgency)
        ).perform(
                ViewActions.click()
        );

        Espresso.onView(
                ViewMatchers.withId(R.id.phoneNumberInput)
        ).check(matches(isDisplayed()));

        Espresso.onView(
                ViewMatchers.withId(R.id.phoneNumberInput)
        ).perform(typeText("11111111111"));

        Espresso.onView(
                ViewMatchers.withId(R.id.phoneNumberInput)
        ).check(matches(withText("11111111111")));
    }
}

