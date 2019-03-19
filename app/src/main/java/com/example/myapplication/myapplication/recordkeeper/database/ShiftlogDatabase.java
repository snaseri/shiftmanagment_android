package com.example.myapplication.myapplication.recordkeeper.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Shiftlog.class}, version = 1, exportSchema = false)
public abstract class ShiftlogDatabase extends RoomDatabase {

    public abstract ShiftlogDAO shiftlogDAO();

}
