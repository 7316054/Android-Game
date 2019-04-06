package com.example.ifunk.tugasbesaruasp3b.View;

import android.content.Context;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import com.example.ifunk.tugasbesaruasp3b.Presenter.DatabasePresenter;
import com.example.ifunk.tugasbesaruasp3b.Presenter.MainPresenter;
import com.example.ifunk.tugasbesaruasp3b.R;
import com.example.ifunk.tugasbesaruasp3b.View.Game_View.GameEventListener;
import com.example.ifunk.tugasbesaruasp3b.View.Game_View.GameFragment;
import com.example.ifunk.tugasbesaruasp3b.View.HighScore_View.HighScoreEventListener;
import com.example.ifunk.tugasbesaruasp3b.View.HighScore_View.HighScoreFragment;
import com.example.ifunk.tugasbesaruasp3b.View.Home_View.HomeEventListener;
import com.example.ifunk.tugasbesaruasp3b.View.Home_View.HomeFragment;

public class MainActivity extends AppCompatActivity implements HomeEventListener,GameEventListener,NavigationView.OnNavigationItemSelectedListener,HighScoreEventListener {

    //Fragment + manager
    protected GameFragment gameFragment;
    protected HomeFragment homeFragment;
    protected HighScoreFragment highScoreFragment;
    protected FragmentManager fragmentManager;

    //view
    protected DrawerLayout mDrawerLayout;

    //Presenter
    protected DatabasePresenter databasePresenter;
    protected MainPresenter mainPresenter;


    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //drawer
        this.mDrawerLayout = this.findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        NavigationView navigationView = this.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //jika mau me-reset highscore uncomment baris 64
//        this.databasePresenter.deleteAll();


        //presenter
        this.databasePresenter = new DatabasePresenter(this);
        this.mainPresenter = new MainPresenter();

        this.homeFragment = HomeFragment.newInstance(this);
        this.gameFragment = GameFragment.newInstance(this);
        this.highScoreFragment = HighScoreFragment.newInstance(this);

        this.fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction = this.fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer,this.homeFragment);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                Log.d("case","1");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onHomeNewBtnClicked() {
        FragmentTransaction transaction = this.fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer,this.gameFragment);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() ==  R.id.newGameItem ){
            FragmentTransaction transaction = this.fragmentManager.beginTransaction();
            transaction.replace(R.id.fragmentContainer,this.homeFragment);
            transaction.commit();
        } else if (menuItem.getItemId() ==  R.id.exitItem){
            System.exit(0);
        } else if (menuItem.getItemId() == R.id.highscoreItem){
            FragmentTransaction transaction = this.fragmentManager.beginTransaction();
            transaction.replace(R.id.fragmentContainer,this.highScoreFragment);
            transaction.commit();
        }
        menuItem.setChecked(true);
        this.mDrawerLayout.closeDrawers();
        return false;
    }

    //----------------------------------------------------------------------------------------------
    //Highscore event Listener
    @Override
    public DatabasePresenter getDatabase() {
        return this.databasePresenter;
    }

    //Game event Listener
    @Override
    public SensorManager getSensorManagerFromActivity() {
        return (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    public MainPresenter getMainPresenter() {
        return this.mainPresenter;
    }

}
