package com.example.ifunk.tugasbesaruasp3b.Presenter;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.ifunk.tugasbesaruasp3b.Model.Game.Ball;
import com.example.ifunk.tugasbesaruasp3b.Model.Game.DrawListener;
import com.example.ifunk.tugasbesaruasp3b.Model.Game.GameRules;
import com.example.ifunk.tugasbesaruasp3b.Model.Game.GameSetting;
import com.example.ifunk.tugasbesaruasp3b.Model.Game.Hole;
import com.example.ifunk.tugasbesaruasp3b.Model.Game.RulesListener;
import com.example.ifunk.tugasbesaruasp3b.Model.Game.SensorReader;
import com.example.ifunk.tugasbesaruasp3b.Model.Game.Timer;
import com.example.ifunk.tugasbesaruasp3b.View.Game_View.GameFragment;

public class GameFragmentPresenter implements DrawListener,RulesListener {

    protected GameFragment gameFragment;
    //model
    private boolean[] deleted;
    protected Hole hole;
    protected SensorReader[] sensorReader;
    protected Timer timer;
    protected Ball[] ball;
    protected GameSetting gameSetting;
    protected GameRules gameRules;

    public GameFragmentPresenter(GameFragment gameFragment) {
        this.gameFragment = gameFragment;
        this.gameSetting = new GameSetting();
        this.timer=new Timer(this,this.gameSetting.getGameTime());
        this.gameRules=new GameRules(this);
    }

    public void setGameSetting(GameSetting gameSetting){
        this.gameSetting = gameSetting;
    }

    public GameSetting getGameSetting() {
        return gameSetting;
    }

    public void preparingModelForView(){
        this.sensorReader = new SensorReader[this.gameSetting.getTotalObjects()];
        this.ball = new Ball[this.gameSetting.getTotalObjects()];
        this.hole=new Hole(this);
        this.hole.drawHole();

        for (int i= 0 ; i <gameSetting.getTotalObjects() ;i++){
            this.ball[i]=new Ball(this);
            this.sensorReader[i]= new SensorReader(this.gameFragment.getPresenter(),this.gameFragment.getListener().getSensorManagerFromActivity(),i);
            this.ball[i].drawBall();
            this.sensorReader[i].start();
        }
    }


    public void initiateTimer(){
        this.timer=new Timer(this,this.gameSetting.getGameTime());
        this.timer.start();
    }

    public void onTimeFinished(){
        int score = Integer.parseInt(this.gameFragment.getTvScore().getText().toString().substring(7).trim());
        this.gameFragment.getStart().setText("Your Score : "+ score);
        this.gameFragment.getStart().setEnabled(false);
        this.gameFragment.getTv_timer().setText("Game Berakhir");
        this.gameFragment.getMainPresenter().setUserGameScore(score);
        this.gameFragment.getDatabasePresenter().insertScoretoDatabase(this.gameFragment.getMainPresenter().user);
    }

    //DrawListener
    @Override
    public int getWidth() {
        return this.gameFragment.getIv_canvas().getWidth();
    }

    @Override
    public int getHeight() {
        return this.gameFragment.getIv_canvas().getHeight();
    }

    @Override
    public void drawHole(int x, int y) {
        Paint paint=new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        this.gameFragment.getCanvas().drawCircle(x,y,50,paint);
        this.gameFragment.getIv_canvas().invalidate();
    }

    @Override
    public void drawBall(float x, float y, Paint paint) {
        this.gameFragment.getCanvas().drawCircle(x,y,50,paint);
        this.gameFragment.getIv_canvas().invalidate();
    }

    //RulesListener
    @Override
    public float getXBall(int i) {
        return this.ball[i].getX();
    }

    @Override
    public float getYBall(int i) {
        return this.ball[i].getY();
    }

    @Override
    public int getXHole() {
        return this.hole.getX();
    }

    @Override
    public int getYHole() {
        return this.hole.getY();
    }

    @Override
    public void rand(int i) {
        this.hole=new Hole(this);
        this.hole.drawHole();
        this.gameFragment.getIv_canvas().invalidate();
    }

    @Override
    public void addScore(int score) {
        this.gameFragment.getTvScore().setText("Score : "+score);
    }

    @Override
    public void timeUp() {
        if(this.gameFragment.getTv_timer().getText().toString().equalsIgnoreCase("Game Berakhir")){
            for (int i = 0 ; i<sensorReader.length;i++){
                Log.d("timeUp: ", "sensor "+i+": shutdown");
                this.sensorReader[i].stop();
            }
            this.sensorReader=null;
            this.ball=null;
            this.timer.stop();
            this.timer=null;
        }
    }

    public float getBallMass(int i){
        return  this.ball[i].getMass();
    }
    //Timer
    public void setTv_timerText(String text) {
        this.gameFragment.getTv_timer().setText(text);
    }

    public void setScore(float curTIme){
        this.gameSetting.setScorePoint(curTIme);
    }

    public void setStartText(String text) {
        this.gameFragment.getStart().setText(text);
    }

    //Sensor reader
    public void updateBall(float xPos,float yPos,int i){
        this.gameFragment.resetCanvas();
        this.ball[i].updateBall((int) xPos, (int) yPos);
        for (int k = 0; k < ball.length; k++) {
            if (k != i) {
                this.ball[k].drawBall();
            }
        }
        this.hole.drawHole();
        this.gameRules.cek(i,this.gameSetting.getScorePoint());
    }
}
