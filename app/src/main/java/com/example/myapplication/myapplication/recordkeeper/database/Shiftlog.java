package com.example.myapplication.myapplication.recordkeeper.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity
public class Shiftlog {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "shiftlog_company")
    private int company;

    @ColumnInfo(name = "shiftlog_agency")
    private int agency ;

    @ColumnInfo(name = "shiftlog_numbertocall")
    private int numberToCall;

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

    @ColumnInfo(name = "shiftlog_registration")
    private String registration;


    @ColumnInfo(name = "shiftlog_poa")
    private String poa;


    @ColumnInfo(name = "shiftlog_nightout")
    private Boolean nightOut;


    public Shiftlog(int company, int agency, String startDate, String startTime, String endDate, String endTime,
                    Boolean vehicleUse, String registration,String poa,  Boolean nightOut) {
        this.company = company;
        this.agency = agency;
        this.startDate= startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.vehicleUse = vehicleUse;
        this.registration = registration;
        this.poa =poa;
        this.nightOut = nightOut;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompany() {
        return company;
    }

    public void setCompany(int company) {
        this.company = company;
    }

    public int getAgency() {
        return agency;
    }

    public void setAgency(int agency) {
        this.agency = agency;
    }

    public int getNumberToCall() {
        return numberToCall;
    }

    public void setNumberToCall(int numberToCall) {
        this.numberToCall = numberToCall;
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

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getPoa() {
        return poa;
    }

    public void setPoa(String poa) {
        this.poa = poa;
    }

    public Boolean getNightOut() {
        return nightOut;
    }

    public void setNightOut(Boolean nightOut) {
        this.nightOut = nightOut;
    }
}
