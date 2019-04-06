package com.example.ifunk.tugasbesaruasp3b.Model.Game;

public class GameSetting {
    protected int gameTime;
    protected float scorePoint;
    protected int totalObjects;

    public GameSetting() {
        this.gameTime = 10;
        this.scorePoint = 1;
        this.totalObjects = 3;
    }

    public int getGameTime() {
        return gameTime;
    }

    public int getTotalObjects() {
        return totalObjects;
    }

    public float getScorePoint() {
        return scorePoint;
    }

    public void setTotalObjects(int totalObjects) {
        this.totalObjects = totalObjects;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public void setScorePoint(float curTime) {
        this.scorePoint=1*curTime;
    }

}
