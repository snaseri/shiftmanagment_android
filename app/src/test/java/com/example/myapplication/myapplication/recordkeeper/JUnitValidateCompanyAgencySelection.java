package com.example.myapplication.myapplication.recordkeeper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.arch.persistence.room.Room;

import com.example.myapplication.myapplication.recordkeeper.database.Company;
import com.example.myapplication.myapplication.recordkeeper.database.ShiftlogDAO;
import com.example.myapplication.myapplication.recordkeeper.database.ShiftlogDatabase;

import java.util.List;
import static org.junit.Assert.*;

public class JUnitValidateCompanyAgencySelection {
    @Test
    public void ValidateSelected() {
        MainActivity activity = new MainActivity();

        Assert.assertTrue(activity.validate(1,1,true,true));
        Assert.assertTrue(activity.validate(1,-1,false,true));
        Assert.assertTrue(activity.validate(-1,1,true,false));
        //Only sending to those who are valid senders, eg anything >= 0, and sending to at least one

        Assert.assertFalse(activity.validate(-1,-1,true,true));
        Assert.assertFalse(activity.validate(1,-1,true,true));
        Assert.assertFalse(activity.validate(-1,1,true,true));
        Assert.assertFalse(activity.validate(1,1,false,false));
        Assert.assertFalse(activity.validate(-1,1,false,true));
        Assert.assertFalse(activity.validate(1,-1,true,false));
        //Sending to invalid users or not sending at all

    }
}