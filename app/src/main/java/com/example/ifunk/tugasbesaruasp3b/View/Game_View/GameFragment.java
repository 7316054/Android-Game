package com.example.ifunk.tugasbesaruasp3b.View.Game_View;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ifunk.tugasbesaruasp3b.Presenter.DatabasePresenter;
import com.example.ifunk.tugasbesaruasp3b.Presenter.GameFragmentPresenter;
import com.example.ifunk.tugasbesaruasp3b.Presenter.MainPresenter;
import com.example.ifunk.tugasbesaruasp3b.R;
import com.example.ifunk.tugasbesaruasp3b.View.Game_View.GameEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment implements View.OnClickListener{

    protected DatabasePresenter databasePresenter;
    protected GameEventListener listener;
    protected GameFragmentPresenter presenter;
    protected MainPresenter mainPresenter;

    protected ImageView iv_canvas;
    protected TextView tv_timer;
    protected Canvas canvas;
    protected Button start;
    protected TextView tvScore;


    public GameFragment() {
        // Required empty public constructor
    }

    public static GameFragment newInstance(GameEventListener listener){
        GameFragment result = new GameFragment();
        result.listener = listener;
        result.presenter = new GameFragmentPresenter(result);
        result.mainPresenter = listener.getMainPresenter();
        result.databasePresenter = listener.getDatabase();
        return result;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_game, null, false);
        this.presenter.setGameSetting(this.mainPresenter.gameSetting);
        this.iv_canvas=result.findViewById(R.id.iv_canvas);
        this.start=result.findViewById(R.id.btn_start);
        this.tv_timer=result.findViewById(R.id.tv_timer);
        this.tvScore = result.findViewById(R.id.tvScore);
        this.start.setOnClickListener(this);
        this.start.setEnabled(true);
        return result;
    }

    @Override
    public void onClick(View view) {
        if(view==start){
            if(start.getText().toString().equalsIgnoreCase("Start")){
                this.initiateCanvas();
                this.presenter.initiateTimer();
                this.start.setText("Stop");
                this.tvScore.setText("Score : 0");
            }
            else if (start.getText().toString().equalsIgnoreCase("Stop")){
                this.start.setText("Start");
                this.tv_timer.setText("Game Berakhir");
                this.presenter.timeUp();
            }

        }
    }

    //Canvas
    public void initiateCanvas(){
        Bitmap mBitmap=Bitmap.createBitmap(iv_canvas.getWidth(),iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
        this.iv_canvas.setImageBitmap(mBitmap);
        this.canvas=new Canvas(mBitmap);

        this.presenter.preparingModelForView();
    }

    public void resetCanvas(){
        int mColorBackground= Color.WHITE;
        canvas.drawColor(mColorBackground);
    }


    //getter view
    public ImageView getIv_canvas() {
        return iv_canvas;
    }

    public TextView getTv_timer() {
        return tv_timer;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Button getStart() {
        return start;
    }

    public TextView getTvScore() {
        return tvScore;
    }

    //getter
    public GameEventListener getListener() {
        return listener;
    }

    public GameFragmentPresenter getPresenter() {
        return presenter;
    }

    public DatabasePresenter getDatabasePresenter() {
        return databasePresenter;
    }

    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }
}
