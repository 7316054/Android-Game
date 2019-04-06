package com.example.ifunk.tugasbesaruasp3b.View.Game_View;

import android.hardware.SensorManager;

import com.example.ifunk.tugasbesaruasp3b.Presenter.DatabasePresenter;
import com.example.ifunk.tugasbesaruasp3b.Presenter.MainPresenter;

public interface GameEventListener {
    public SensorManager getSensorManagerFromActivity();
    public MainPresenter getMainPresenter();
    public DatabasePresenter getDatabase();
}
