package com.example.ifunk.tugasbesaruasp3b.Model.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {
    //primary key autoincrement
    @PrimaryKey (autoGenerate = true)
    public int uid;

    //kolom nama
    @ColumnInfo(name = "name")
    public String name;

    //kolom highscore
    @ColumnInfo(name = "score")
    public int score;

    public User(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}