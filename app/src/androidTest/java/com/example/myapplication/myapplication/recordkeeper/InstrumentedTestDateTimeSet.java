package com.example.myapplication.myapplication.recordkeeper;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.filters.LargeTest;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class InstrumentedTestDateTimeSet {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void StartDateTest() {
        ViewInteraction appCompatButton = onView(allOf(withId(R.id.btnStartDatePicker)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"), isDisplayed()));
        appCompatButton2.perform(click());
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        String mon = String.valueOf(month+1);
        if (month+1 < 10) mon = "0" + mon;
        String day = String.valueOf(dayOfMonth);
        if (dayOfMonth < 10) day = "0" + day;
        String st = String.format("Start date - %s/%s/%d", day, mon, year);
        String s = "Start date - " + day + "/" + (month + 1) + "/" + year;
        onView(allOf(withId(R.id.btnStartDatePicker))).check(ViewAssertions.matches(withText(containsString(st))));
    }

    @Test
    public void EndDateTest() {
        ViewInteraction appCompatButton = onView(allOf(withId(R.id.btnEndDatePicker)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"), isDisplayed()));
        appCompatButton2.perform(click());
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        String mon = String.valueOf(month+1);
        if (month+1 < 10) mon = "0" + mon;
        String day = String.valueOf(dayOfMonth);
        if (dayOfMonth < 10) day = "0" + day;
        String st = String.format("End date - %s/%s/%d", day, mon, year);
        appCompatButton.check(ViewAssertions.matches(withText(containsString(st))));
    }

    @Test
    public void StartTimeTest() {
        ViewInteraction appCompatButton = onView(allOf(withId(R.id.btnStartTimePicker)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"), isDisplayed()));
        appCompatButton2.perform(click());
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        String hr = String.valueOf(hour);
        if (hour+1 < 10) hr = "0" + hr;
        String min = String.valueOf(minute);
        if (minute < 10) min = "0" + min;
        String st = String.format("Start time - %s:%s", hr, min);
        appCompatButton.check(ViewAssertions.matches(withText(containsString(st))));
    }

    @Test
    public void EndTimeTest() {
        ViewInteraction appCompatButton = onView(allOf(withId(R.id.btnEndTimePicker)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"), isDisplayed()));
        appCompatButton2.perform(click());
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        String hr = String.valueOf(hour);
        if (hour+1 < 10) hr = "0" + hr;
        String min = String.valueOf(minute);
        if (minute < 10) min = "0" + min;
        String st = String.format("End time - %s:%s", hr, min);
        appCompatButton.check(ViewAssertions.matches(withText(containsString(st))));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
