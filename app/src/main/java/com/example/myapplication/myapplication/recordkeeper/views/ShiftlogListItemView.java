package com.example.myapplication.myapplication.recordkeeper.views;

import com.example.myapplication.myapplication.recordkeeper.database.Shiftlog;

public class ShiftlogListItemView {

    private int id;
    private String name;

    public ShiftlogListItemView(Shiftlog shiftLog) {
        name = shiftLog.getName();
        id = shiftLog.getId();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
