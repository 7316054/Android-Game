package com.example.ifunk.tugasbesaruasp3b.Presenter;

import com.example.ifunk.tugasbesaruasp3b.Model.Database.User;
import com.example.ifunk.tugasbesaruasp3b.Model.Game.GameSetting;

public class MainPresenter {

    public GameSetting gameSetting;
    public User user;

    public MainPresenter() {
        gameSetting = new GameSetting();
        user = new User("a",0);
    }

    public void setUserGameName(String name){
        this.user.setName(name);
    }

    public void setUserGameScore(int score){
        this.user.setScore(score);
    }

    public void setGameSettingObjectNum(int objectNum){
        this.gameSetting.setTotalObjects(objectNum);
    }
    public void setGameSettingGameTime(int time){
        this.gameSetting.setGameTime(time);
    }

}
