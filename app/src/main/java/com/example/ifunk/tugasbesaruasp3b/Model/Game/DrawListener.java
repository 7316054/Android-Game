package com.example.ifunk.tugasbesaruasp3b.Model.Game;

import android.graphics.Paint;

public interface DrawListener {
    public int getWidth();
    public int getHeight();
    public void drawHole(int x,int y);
    public void drawBall(float x, float y, Paint paint);
}

