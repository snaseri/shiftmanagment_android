package com.example.myapplication.myapplication.recordkeeper;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;



@MediumTest
@RunWith(AndroidJUnit4.class)
public class ShiftLogTest {

    private String mStringtoBetyped;

    @Rule
    public ActivityTestRule<MainActivity> mActicityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void regInputVisibilityWhenVisible() {
        //Checking the vehicle box tickbox to see if it changes the visibility of
        //Other options that should show up
        onView(withId(R.id.vehicleUse)).perform(click());
        onView(withId(R.id.RegInput)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
}

