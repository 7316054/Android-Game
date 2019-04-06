package com.example.ifunk.tugasbesaruasp3b.View.Home_View;


import android.graphics.Color;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ifunk.tugasbesaruasp3b.Presenter.MainPresenter;
import com.example.ifunk.tugasbesaruasp3b.R;
import com.example.ifunk.tugasbesaruasp3b.View.Home_View.HomeEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    protected MainPresenter mainPresenter;
    protected HomeEventListener listener;
    protected Button homeNewBtn;
    protected Button setTimeBtn;
    protected Button setObjectBtn;
    protected EditText nameEt;
    protected EditText setObjectEt;
    protected EditText setTimeEt;
    protected TextView setObjectStatusTv;
    protected TextView setTimeStatusTv;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(HomeEventListener listener) {
        HomeFragment result = new HomeFragment();
        result.listener = listener;
        result.mainPresenter = listener.getMainPresenter();
        return result;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_home, null, false);
        this.nameEt = result.findViewById(R.id.nameEt);
        this.setObjectEt = result.findViewById(R.id.setObjectEt);
        this.setTimeEt = result.findViewById(R.id.setTimeEt);
        this.setObjectStatusTv = result.findViewById(R.id.setObjectStatusTv);
        this.setTimeStatusTv = result.findViewById(R.id.setTimeStatusTv);
        this.setObjectBtn = result.findViewById(R.id.setObjectBtn);
        this.setObjectBtn.setOnClickListener(this);
        this.setTimeBtn = result.findViewById(R.id.setTimeBtn);
        this.setTimeBtn.setOnClickListener(this);
        this.homeNewBtn = result.findViewById(R.id.homeNewBtn);
        this.homeNewBtn.setOnClickListener(this);
        return result;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == this.homeNewBtn.getId()) {
            String name = this.nameEt.getText().toString();
            if (!name.equalsIgnoreCase("")) {
                this.mainPresenter.setUserGameName(name);
                this.listener.onHomeNewBtnClicked();
            } else {
                this.nameEt.setHintTextColor(Color.RED);
            }
        } else if (view.getId() == this.setObjectBtn.getId()) {
            if (!this.setObjectEt.getText().toString().equalsIgnoreCase("")) {
                int temp = Integer.parseInt(this.setObjectEt.getText().toString());
                if (temp <= 3 && temp != 0) {
                    this.mainPresenter.setGameSettingObjectNum(temp);
                    this.setObjectStatusTv.setText(temp + " object (max = 3)");
                } else {
                    this.setObjectEt.setHintTextColor(Color.RED);
                }
            } else {
                this.setObjectEt.setHintTextColor(Color.RED);
            }

        } else if (view.getId() == this.setTimeBtn.getId()) {
            if (!this.setTimeEt.getText().toString().equalsIgnoreCase("")) {
                int temp = Integer.parseInt(this.setTimeEt.getText().toString());
                if (temp > 0) {
                    this.mainPresenter.setGameSettingGameTime(temp);
                    this.setTimeStatusTv.setText(temp + " sec (time > 0)");
                } else {
                    this.setTimeEt.setHintTextColor(Color.RED);
                }
            } else {
                this.setTimeEt.setHintTextColor(Color.RED);
            }
        }
    }

}
