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
    private Date startDate;

    @ColumnInfo(name = "shiftlog_starttime")
    private Date startTime;

    @ColumnInfo(name = "shiftlog_enddate")
    private Date endDate;

    @ColumnInfo(name = "shiftlog_endtime")
    private Date endTime;

    @ColumnInfo(name = "shiftlog_endtime")
    private Boolean vehicleUse;

    @ColumnInfo(name = "shiftlog_endtime")
    private Boolean nightOut;


    public Shiftlog(String aName, String aCompany, String aAgency, Date aStartDate, Date aStartTime, Date aEndDate, Date aEndTime,
                    Boolean vehicleUse, Boolean aNightout) {
        this.name = aName;
        this.company = aCompany;
        this.agency = aAgency;
        this.startDate = aStartDate;
        this.startTime = aStartTime;
        this.endDate = aEndDate;
        this.endTime = aEndTime;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
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
