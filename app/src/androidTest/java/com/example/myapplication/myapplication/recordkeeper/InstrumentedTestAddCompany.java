package com.example.myapplication.myapplication.recordkeeper;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.myapplication.myapplication.recordkeeper.database.Company;
import com.example.myapplication.myapplication.recordkeeper.database.ShiftlogDAO;
import com.example.myapplication.myapplication.recordkeeper.database.ShiftlogDatabase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class InstrumentedTestAddCompany {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    ShiftlogDAO db;
    @Before
    public void launchDB() {
         db = Room.databaseBuilder(mActivityTestRule.getActivity(), ShiftlogDatabase.class,
                "ShiftlogDatabase").fallbackToDestructiveMigration().build().shiftlogDAO();

    }

    @Test
    public void checkCompanyAdded(){
        List<Company> allCompanies = db.getAllCompanies();
        final int size1 = allCompanies.size();
        db.insertCompany(new Company("A Test Company", "07967898765"));
        allCompanies = db.getAllCompanies();
        final int size2 = allCompanies.size();
        assertEquals(size1 + 1, size2);

        assertEquals(allCompanies.get(size1).getName(), "A Test Company");
    }
}
