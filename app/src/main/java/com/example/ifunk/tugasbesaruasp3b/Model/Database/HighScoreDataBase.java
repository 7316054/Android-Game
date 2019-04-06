package com.example.ifunk.tugasbesaruasp3b.Model.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;



@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class HighScoreDataBase extends RoomDatabase {
    public abstract UserDataAccessObject userDao();
}
