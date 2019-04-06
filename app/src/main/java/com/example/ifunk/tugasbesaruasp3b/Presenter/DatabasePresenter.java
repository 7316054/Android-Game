package com.example.ifunk.tugasbesaruasp3b.Presenter;

import android.arch.persistence.room.Room;

import com.example.ifunk.tugasbesaruasp3b.Model.Database.HighScoreDataBase;
import com.example.ifunk.tugasbesaruasp3b.Model.Database.User;
import com.example.ifunk.tugasbesaruasp3b.View.MainActivity;

import java.util.List;

public class DatabasePresenter {
    protected HighScoreDataBase dataBase;

    public DatabasePresenter(MainActivity mainActivity) {
        this.dataBase = Room.databaseBuilder(mainActivity.getApplicationContext(),HighScoreDataBase.class,"HighScoreDatabase").allowMainThreadQueries().build();
    }

    public List<User> getNewestHighScoreList(){
        return this.dataBase.userDao().loadAllUserInHighscore();
    }

    public void insertScoretoDatabase(User user){
        this.dataBase.userDao().insertHighScore(user);
    }

    public void deleteAll(){
        this.dataBase.userDao().deleteAllData();
    }
}
