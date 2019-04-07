package com.example.myapplication.myapplication.recordkeeper;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ShiftLogTest {

    private String mStringtoBetyped;

    @Rule
    public ActivityTestRule<MainActivity> mActicityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void checkTheVehiceUseBox() {
    }

    @Test
    public void regInputVisibilityWhenVisible() {
        onView(withId(R.id.btnStartDatePicker)).perform(click());
        onView(withId(R.id.RegInput)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
//        onView(withId(R.id.btnStartDatePicker)).check(matches(isDisplayed()));

    }
}

