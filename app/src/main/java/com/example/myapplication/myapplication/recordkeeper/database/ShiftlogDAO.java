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

    @Query("DELETE FROM Shiftlog WHERE shiftlog_name=:nameToDelete")
    void deleteShiftlogs(String nameToDelete);

    @Query("SELECT * FROM Shiftlog WHERE id=:id")
    Shiftlog getShiftLogById(int id);
}

