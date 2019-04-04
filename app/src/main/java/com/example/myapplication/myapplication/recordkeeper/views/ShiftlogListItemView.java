package com.example.myapplication.myapplication.recordkeeper.views;

import android.arch.persistence.room.Room;

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

    public ShiftlogListItemView(Shiftlog shiftLog, List<Company> allCompanies, List<Agency> allAgencies) {

        company = String.valueOf(shiftLog.getCompany());
        for (Company c: allCompanies
        ) {
            if (shiftLog.getCompany() == c.getId()){
                company = c.getName();
            }
        }
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
