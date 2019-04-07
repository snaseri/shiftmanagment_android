package com.example.myapplication.myapplication.recordkeeper;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.myapplication.myapplication.recordkeeper.database.Agency;
import com.example.myapplication.myapplication.recordkeeper.database.Shiftlog;
import com.example.myapplication.myapplication.recordkeeper.database.ShiftlogDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AddingAgencyNameToDatabaseTest {

        private ShiftlogDAO Dao;
        private Agency db;

        @Before
        public void createDb() {
            Context applicationContext = InstrumentationRegistry.getTargetContext();
            db = Room.databaseBuilder(applicationContext, Agency.class,"Database Test").build();
            Dao = db.ShiftlogDAO();
        }


        @Test
        public void writeUserAndReadInList() throws Exception {
            Agency agency = new Agency("Agency","01234567891" );

            ShiftlogDAO.insertAgency(agency);
            List<Agency> byName = Agency.getallAgencies("Agency");
            assertThat(byName.get(0), equalTo(agency));
        }
    }

