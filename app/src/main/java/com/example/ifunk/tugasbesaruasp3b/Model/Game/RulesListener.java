package com.example.ifunk.tugasbesaruasp3b.Model.Game;

public interface RulesListener {
    public float getXBall(int i);
    public float getYBall(int i);
    public int getXHole();
    public int getYHole();
    public void rand(int i);
    public void addScore(int score);
    public void timeUp();
}
