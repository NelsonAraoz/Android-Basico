package com.example.zuki.campeonato.model;

import android.content.ContentValues;

import java.io.Serializable;

/**
 * Created by Zuki on 10/15/2015.
 */
public class Team implements Serializable{
    private int id;
    private short category;
    private String name;
    private String path;
    private int score;
    public Team()
    {

    }
    public Team(int id, short category, String name, String path) {
        this.id = id;
        this.category = category;
        this.path = path;
        this.name = name;
        this.score=0;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getCategory() {
        return category;
    }

    public void setCategory(short category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("category", category);
        contentValues.put("path", path);
        return contentValues;
    }

}
