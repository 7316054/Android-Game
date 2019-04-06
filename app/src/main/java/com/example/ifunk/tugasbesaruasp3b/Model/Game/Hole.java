package com.example.ifunk.tugasbesaruasp3b.Model.Game;

import java.util.Random;

public class Hole {
    private  DrawListener listener;
    private int x;
    private int y;

    public Hole(DrawListener listener){
        Random rand=new Random();
        this.listener=listener;
        this.x=50+rand.nextInt(listener.getWidth()-150);
        this.y=50+rand.nextInt(listener.getHeight()-150);
    }

    public void drawHole(){
        listener.drawHole(this.x,this.y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
