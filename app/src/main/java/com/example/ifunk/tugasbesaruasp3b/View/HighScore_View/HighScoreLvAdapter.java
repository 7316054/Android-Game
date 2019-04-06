package com.example.ifunk.tugasbesaruasp3b.View.HighScore_View;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ifunk.tugasbesaruasp3b.Model.Database.User;
import com.example.ifunk.tugasbesaruasp3b.R;


import java.util.ArrayList;
import java.util.List;

public class HighScoreLvAdapter extends BaseAdapter {

    protected List<User> highscoreList;
    protected LayoutInflater inflater;

    public HighScoreLvAdapter(Activity activity) {
        this.highscoreList = new ArrayList();
        this.inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return highscoreList.size();
    }

    @Override
    public Object getItem(int i) {
        return highscoreList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void setHighscoreList(List<User> newList){
        this.highscoreList = newList;
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View result = view;
        HighScoreLvViewHolder hvh;
        if(result == null){
            result = this.inflater.inflate(R.layout.highscore_list_view_content,null);
            hvh = new HighScoreLvViewHolder(result);
            result.setTag(hvh);
            Log.d("CREATE","VIEW HOLDER");
        } else {
            hvh = (HighScoreLvViewHolder) result.getTag();
        }
        User temp ;
        temp = this.highscoreList.get(i);
        hvh.getLvContentHighscoreRankTv().setText((i+1)+"");
        hvh.getLvContentHighscoreNameTv().setText(temp.name);
        hvh.getLvContentHighscoreScoreTv().setText(temp.score+"");
        return result;
    }

    private class HighScoreLvViewHolder{
        protected TextView lvContentHighscoreRankTv;
        protected TextView lvContentHighscoreNameTv;
        protected TextView lvContentHighscoreScoreTv;

        public HighScoreLvViewHolder(View v) {
            this.lvContentHighscoreNameTv = v.findViewById(R.id.lvContentHighscoreNameTv);
            this.lvContentHighscoreRankTv = v.findViewById(R.id.lvContentHighscoreRankTv);
            this.lvContentHighscoreScoreTv = v.findViewById(R.id.lvContentHighscoreScoreTv);
        }

        public TextView getLvContentHighscoreRankTv() {
            return lvContentHighscoreRankTv;
        }

        public TextView getLvContentHighscoreNameTv() {
            return lvContentHighscoreNameTv;
        }

        public TextView getLvContentHighscoreScoreTv() {
            return lvContentHighscoreScoreTv;
        }
    }
}
