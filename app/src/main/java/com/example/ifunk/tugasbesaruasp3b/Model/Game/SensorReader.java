package com.example.ifunk.tugasbesaruasp3b.Model.Game;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.ifunk.tugasbesaruasp3b.Presenter.GameFragmentPresenter;

public class SensorReader implements SensorEventListener {
    protected SensorManager manager;
    protected Sensor acc;
    protected GameFragmentPresenter ui;
    private float ballMass;
    private float xPos,xAccel,xVel;
    private float yPos,yAccel,yVel;
    private float xMax,yMax;
    private int radius;
    private int code;

    public SensorReader(GameFragmentPresenter gameFragmentPresenter,SensorManager manager,int i){
        this.manager= manager;
        this.acc=this.manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.ui=gameFragmentPresenter;
        this.code=i;
        this.xPos = gameFragmentPresenter.getXBall(i);
        this.yPos = gameFragmentPresenter.getYBall(i);
        this.ballMass = gameFragmentPresenter.getBallMass(i);
        xMax=this.ui.getWidth();
        yMax=this.ui.getHeight();
        this.radius = 50;
    }
    public void start(){
        this.manager.registerListener(this,this.acc,SensorManager.SENSOR_DELAY_GAME);
    }
    public void stop(){
        this.manager.unregisterListener(this,this.acc);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        xAccel=sensorEvent.values[0];
        yAccel=-sensorEvent.values[1];

        xVel += (xAccel * ballMass);
        yVel += (yAccel * ballMass);

        float xS = (xVel / 2) * ballMass;
        float yS = (yVel / 2) * ballMass;

        xPos -= xS;
        yPos -= yS;
        if((xPos<radius)||(xPos> xMax-radius)){
            xVel*=(-0.7);
        }
        if((yPos < radius)||(yPos > yMax-radius)){
            yVel*=(-0.7);
        }
        if (xPos> xMax-radius) {
            xPos=xMax-radius;
        } else if (xPos < radius) {
            xPos =radius;
        }

        if (yPos > yMax-radius) {
            yPos = yMax-radius;
        } else if (yPos < radius) {
            yPos =radius;
        }
        this.ui.updateBall(xPos,yPos,code);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
