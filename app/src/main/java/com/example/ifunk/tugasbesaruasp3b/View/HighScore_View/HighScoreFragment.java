package com.example.ifunk.tugasbesaruasp3b.View.HighScore_View;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.ifunk.tugasbesaruasp3b.Presenter.DatabasePresenter;
import com.example.ifunk.tugasbesaruasp3b.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HighScoreFragment extends Fragment {

    protected ListView highScoreLv;
    private HighScoreLvAdapter adapter;
    protected HighScoreEventListener listener;
    protected DatabasePresenter databasePresenter;

    public HighScoreFragment() {
        // Required empty public constructor
    }

    public static HighScoreFragment newInstance(HighScoreEventListener listener){
        HighScoreFragment result = new HighScoreFragment();
        result.listener = listener;
        result.adapter = new HighScoreLvAdapter((Activity) listener);
        result.databasePresenter = listener.getDatabase();
        return result;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_high_score, null, false);
        this.highScoreLv = result.findViewById(R.id.highscoreLv);
        this.adapter.setHighscoreList(this.databasePresenter.getNewestHighScoreList());
        this.highScoreLv.setAdapter(this.adapter);
        return result;
    }

}
