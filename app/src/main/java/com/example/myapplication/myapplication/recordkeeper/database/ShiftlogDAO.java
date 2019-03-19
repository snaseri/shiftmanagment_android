package com.example.myapplication.myapplication.recordkeeper.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ShiftlogDAO {

    @Query("SELECT * FROM Shiftlog")
    List<Shiftlog> getAllBooks();

    @Insert
    void insertBooks(Shiftlog... book);

    @Query("DELETE FROM Shiftlog")
    void clearBooks();



}

