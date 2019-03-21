package com.example.myapplication.myapplication.recordkeeper.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class Shiftlog {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "shiftlog_name")
    private String name;

    @ColumnInfo(name = "shiftlog_company")
    private String company;

    @ColumnInfo(name = "shiftlog_agency")
    private String agency ;

    @ColumnInfo(name = "shiftlog_startdate")
    private String startDate;

    @ColumnInfo(name = "shiftlog_starttime")
    private String startTime;

    @ColumnInfo(name = "shiftlog_enddate")
    private String endDate;

    @ColumnInfo(name = "shiftlog_endtime")
    private String endTime;

    @ColumnInfo(name = "shiftlog_endtime")
    private Boolean vehicleUse;

    @ColumnInfo(name = "shiftlog_endtime")
    private Boolean nightOut;


    public Shiftlog(String aName, String aCompany, String aAgency, String aStartDate, String aStartTime, String aEndDate, String aEndTime,
                    Boolean aVehicleUse, Boolean aNightout) {
        this.name = aName;
        this.company = aCompany;
        this.agency = aAgency;
        this.startDate= aStartDate;
        this.startTime = aStartTime;
        this.endDate = aEndDate;
        this.endTime = aEndTime;
        this.vehicleUse = aVehicleUse;
        this.nightOut = aNightout;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Boolean getVehicleUse() {
        return vehicleUse;
    }

    public void setVehicleUse(Boolean vehicleUse) {
        this.vehicleUse = vehicleUse;
    }

    public Boolean getNightOut() {
        return nightOut;
    }

    public void setNightOut(Boolean nightOut) {
        this.nightOut = nightOut;
    }
}
