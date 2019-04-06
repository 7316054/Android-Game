package com.example.ifunk.tugasbesaruasp3b.Model.Game;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class Ball {
    private  DrawListener listener;
    private float x;
    private float y;
    private float mass;
    private Paint paint;

    public Ball(DrawListener listener){
        Random rand=new Random();
        this.listener=listener;
        this.x=50+rand.nextInt(listener.getWidth()-150);
        this.y=50+rand.nextInt(listener.getHeight()-150);
        this.mass=0.05f + rand.nextFloat() * (0.2f - 0.05f);
        this.paint= new Paint();
        int code = rand.nextInt(6);
        if (code ==0){
            this.paint.setColor(Color.RED);
            this.mass+=0.05f;
        }
        else if(code==1){
            this.paint.setColor(Color.BLUE);
            this.mass+=0.1f;
        }
        else if(code==2){
            this.paint.setColor(Color.CYAN);
            this.mass+=0.15f;
        }
        else if(code==3){
            this.paint.setColor(Color.GREEN);
            this.mass+=0.2f;
        }
        else if(code==4){
            this.paint.setColor(Color.YELLOW);
            this.mass+=0.25f;
        }
        else if(code==5){
            this.paint.setColor(Color.MAGENTA);
            this.mass+=0.3f;
        }
    }

    public void drawBall(){
        listener.drawBall(this.x,this.y,this.paint);
    }
    public void updateBall(float currX,float currY){
        this.x=currX;
        this.y=currY;
        listener.drawBall(this.x,this.y,this.paint);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getMass() {
        return mass;
    }
    public void dissapear(){
        this.paint.setColor(Color.WHITE);
    }
}