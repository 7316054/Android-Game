package com.example.ifunk.tugasbesaruasp3b.Model.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDataAccessObject {

    //load name and score by id
    @Query("SELECT * FROM user ORDER BY user.score DESC LIMIT 10")
    List<User> loadAllUserInHighscore();

    @Insert
    void insertHighScore(User user);

    @Query("DELETE FROM user")
    void deleteAllData();




}
