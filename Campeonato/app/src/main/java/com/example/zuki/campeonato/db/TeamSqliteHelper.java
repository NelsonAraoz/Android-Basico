package com.example.zuki.campeonato.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zuki on 10/15/2015.
 */
public class TeamSqliteHelper extends SQLiteOpenHelper {
    public TeamSqliteHelper(Context context) {
        super(context, "championship", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE  TABLE team (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,name VARCHAR(150) NOT NULL, category INT NOT NULL,path VARCHAR(250) NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS team");
    }
}
