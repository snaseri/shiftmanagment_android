package com.example.myapplication.myapplication.recordkeeper;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class SearchBarTest {

    @Rule
    public ActivityTestRule<MainActivity> mActicityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.myapplication.myapplication.recordkeeper", appContext.getPackageName());
    }

    @Test
    public void searchIconVisibility() {

    }
}


