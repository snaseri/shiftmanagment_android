package com.example.myapplication.myapplication.recordkeeper;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MainActivityTest {

    @Test
    public void logIsChecked() {
        MainActivity mainActivity = new MainActivity();
        // This function looks to see if the log values are all the default values
        //if so it returns false because it means the log hasnt been changed by user.
        Boolean isLogChanged = mainActivity.logIsChanged("No Company", "No Agency",
                null, null, null, null, null, false,
                false, false, false);
        assertEquals(false, isLogChanged);
    }
}