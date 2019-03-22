package com.example.myapplication.myapplication.recordkeeper.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


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

    @ColumnInfo(name = "shiftlog_vehicleuse")
    private Boolean vehicleUse;

    @ColumnInfo(name = "shiftlog_nightout")
    private Boolean nightOut;


    public Shiftlog(String name, String company, String agency, String startDate, String startTime, String endDate, String endTime,
                    Boolean vehicleUse, Boolean nightOut) {
        this.name = name;
        this.company = company;
        this.agency = agency;
        this.startDate= startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.vehicleUse = vehicleUse;
        this.nightOut = nightOut;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
