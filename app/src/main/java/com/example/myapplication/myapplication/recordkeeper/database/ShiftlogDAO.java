package com.example.myapplication.myapplication.recordkeeper.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ShiftlogDAO {

    @Query("SELECT * FROM Shiftlog")
    List<Shiftlog> getAllShiftlogs();

    @Insert
    void insertShiftlog(Shiftlog... book);

    @Query("DELETE FROM Shiftlog")
    void clearShiftlogs();

    @Query("DELETE FROM Shiftlog WHERE shiftlog_company=:nameToDelete")
    void deleteShiftlogs(String nameToDelete);

    @Query("SELECT * FROM Shiftlog WHERE id=:id")
    Shiftlog getShiftLogById(int id);

    @Query("SELECT * FROM Company")
    List<Company> getAllCompanies();

    @Insert
    void insertCompany(Company... book);

    @Query("DELETE FROM Company")
    void clearCompanies();

    @Query("SELECT * FROM Company WHERE id=:id")
    Company getCompanyByID(int id);

    @Query("SELECT * FROM Agency")
    List<Agency> getAllAgencies();

    @Insert
    void insertAgency(Agency... book);

    @Query("DELETE FROM Agency")
    void clearAgencies();

    @Query("SELECT * FROM Agency WHERE id=:id")
    Agency getAgencyByID(int id);

    @Query("SELECT Shiftlog.* FROM Agency, Shiftlog WHERE Agency.id=:id AND Shiftlog.shiftlog_agency = Agency.id")
    Shiftlog getShiftLogByAgency(int id);

    @Query("UPDATE Shiftlog SET shiftlog_shared = 1 WHERE id = :id")
    void setSharedFor(int id);
}

