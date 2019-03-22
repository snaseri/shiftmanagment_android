package com.example.myapplication.myapplication.recordkeeper.views;

import com.example.myapplication.myapplication.recordkeeper.database.Shiftlog;

public class ShiftlogListItemView {

    private String name;

    public ShiftlogListItemView(Shiftlog shiftLog) {
        name = shiftLog.getName();
    }

    public String getName() {
        return name;
    }
}
