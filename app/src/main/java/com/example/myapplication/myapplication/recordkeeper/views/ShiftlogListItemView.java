package com.example.myapplication.myapplication.recordkeeper.views;

import android.arch.persistence.room.Room;

import com.example.myapplication.myapplication.recordkeeper.database.Shiftlog;
import com.example.myapplication.myapplication.recordkeeper.database.ShiftlogDAO;
import com.example.myapplication.myapplication.recordkeeper.database.ShiftlogDatabase;

public class ShiftlogListItemView {

    private int id;
    private String company;
    private String start;
    private String end;

    public ShiftlogListItemView(Shiftlog shiftLog) {

        company = String.valueOf(shiftLog.getCompany());
        start = shiftLog.getStartDate();
        end =shiftLog.getEndDate();
        id = shiftLog.getId();
    }

    public String getCompany() {
        return company;
    }

    public String getStartDate(){
        return start;
    }
    public String getEndDate(){
        return end;
    }
    public int getId() {
        return id;
    }
}
