package com.example.myapplication.myapplication.recordkeeper;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;

import com.example.myapplication.myapplication.recordkeeper.database.Shiftlog;
import com.example.myapplication.myapplication.recordkeeper.database.ShiftlogDAO;
import com.example.myapplication.myapplication.recordkeeper.database.ShiftlogDatabase;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseAddingShiftLogTest {
    private static ShiftlogDAO db;

    @Rule
    public ActivityTestRule<MainActivity> mActicityTestRule = new ActivityTestRule<>(MainActivity.class);

    @MediumTest
    @Test
    public void addLogsToDatabase() {
        db = Room.databaseBuilder(InstrumentationRegistry.getTargetContext(), ShiftlogDatabase.class,
                "ShiftlogDatabase").fallbackToDestructiveMigration().build().shiftlogDAO();
        //Creating a shiftlog to add it to the database
        Shiftlog logToBeAdded = new Shiftlog(1,1,3, "10:10",
                "02/01/2019", "01/01/2010", "10:10", "00:30", true, "dd,dd,dd",
                "00:30", false, true);
        //Clearing the database before to set the size to 0
        db.clearShiftlogs();
        //Inserting the new log.
        db.insertShiftlog(logToBeAdded);

        assertEquals(1, db.getAllShiftlogs().size());

    }
}
