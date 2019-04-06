package com.example.ifunk.tugasbesaruasp3b.Model.Game;


import android.os.CountDownTimer;

import com.example.ifunk.tugasbesaruasp3b.Presenter.GameFragmentPresenter;


public class Timer {
    private CountDownTimer countDownTimer;
    private GameFragmentPresenter gameFragmentPresenter;
    private int time;
    public Timer(GameFragmentPresenter gameFragmentPresenter, int time){
        this.gameFragmentPresenter = gameFragmentPresenter;
        this.time = time;
    }

    public void start(){
        this.gameFragmentPresenter.setTv_timerText("Time : "+time);
        countDownTimer=new CountDownTimer(time * 1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                gameFragmentPresenter.setTv_timerText("Time : "+millisUntilFinished/1000);
                gameFragmentPresenter.setScore(millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                gameFragmentPresenter.setTv_timerText("Game Berakhir");
                gameFragmentPresenter.timeUp();
                gameFragmentPresenter.onTimeFinished();

            }
        };
        countDownTimer.start();
    }

    public void stop(){
        this.countDownTimer.cancel();
        this.gameFragmentPresenter.setTv_timerText("Game Berakhir");
    }

}