package com.example.myapplication.myapplication.recordkeeper.views;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import com.example.myapplication.myapplication.recordkeeper.database.Agency;
import com.example.myapplication.myapplication.recordkeeper.database.Company;
import com.example.myapplication.myapplication.recordkeeper.database.Shiftlog;
import com.example.myapplication.myapplication.recordkeeper.database.ShiftlogDAO;
import com.example.myapplication.myapplication.recordkeeper.database.ShiftlogDatabase;

import java.util.List;

public class ShiftlogListItemView {

    private int id;
    private String company ;
    private String start;
    private String end;
    private Boolean shared;
    private static ShiftlogDAO db;

    public ShiftlogListItemView(final Shiftlog shiftLog, Context c) {

        company = String.valueOf(shiftLog.getCompany());
        db = Room.databaseBuilder(c, ShiftlogDatabase.class,
                "ShiftlogDatabase").fallbackToDestructiveMigration().build().shiftlogDAO();


        AsyncTask.execute(new Runnable() {
                              @Override
                              public void run() {
                                  if (db.getCompanyByID(shiftLog.getCompany()) != null) {
                                      company = db.getCompanyByID(shiftLog.getCompany()).getName();
                                  }
                              }
                          });
        start = shiftLog.getStartDate();
        end = shiftLog.getEndDate();
        id = shiftLog.getId();
        shared = shiftLog.getShared();
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

    public Boolean getShared() {
        return shared;
    }
}
