package com.example.myapplication.myapplication.recordkeeper.views;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.myapplication.myapplication.recordkeeper.database.Agency;
import com.example.myapplication.myapplication.recordkeeper.database.Company;
import com.example.myapplication.myapplication.recordkeeper.database.Shiftlog;
import com.example.myapplication.myapplication.recordkeeper.database.ShiftlogDAO;
import com.example.myapplication.myapplication.recordkeeper.database.ShiftlogDatabase;

import java.util.List;

public class ShiftlogListItemView {

    public interface UpdateUI{
        void update(int i);
    }

    private int id;
    private String company ;
    private String start;
    private String end;
    private Boolean shared;
    private Shiftlog shiftlog;
    private static ShiftlogDAO db;
    private Context c;
    private Integer pos;
    private UpdateUI update;

    public ShiftlogListItemView(final Shiftlog shiftLog, Context c, final int size) {


        start = shiftLog.getStartDate();
        end = shiftLog.getEndDate();
        id = shiftLog.getId();
        shared = shiftLog.getShared();
        this.shiftlog = shiftLog;
        this.c = c;
        this.pos = size;
    }
    public void generateName(){

        db = Room.databaseBuilder(c, ShiftlogDatabase.class,
                "ShiftlogDatabase").fallbackToDestructiveMigration().build().shiftlogDAO();


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Log.d("THIS INFO", String.valueOf(shiftlog.getCompany()));
                Log.d("THIS INFO", String.valueOf(pos));
                Log.d("THIS INFO", String.valueOf(db.getCompanyByID(shiftlog.getCompany())));
                if (db.getCompanyByID(shiftlog.getCompany()) != null) {

                    company = db.getCompanyByID(shiftlog.getCompany()).getName();
                    update.update(pos);

                }
            }
        });
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

    public void setUpdate(UpdateUI update) {
        this.update = update;
    }
}
