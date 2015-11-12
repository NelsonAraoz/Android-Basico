package com.example.zuki.campeonato.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.zuki.campeonato.model.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zuki on 10/15/2015.
 */
public class TeamDAO {
    private SQLiteDatabase db;
    private TeamSqliteHelper dbHelper;

    public TeamDAO(Context context) {
        dbHelper = new TeamSqliteHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public void addTeam(Team team) {
        db.insert("team", null, team.toContentValues());
    }

    public void deleteTeam(int teamId) {
        db.delete("team", "_id=" + teamId, null);
    }

    public List<Team> getTeams() {
        List<Team> teams = new ArrayList<Team>();

        String[] columns = new String[]{"_id", "name","category","path"};
        //SELECT _id, todo FROM todo
        //com.example.zuki.campeonato.db.rawQuery("SELECT _id, todo FROM todo;", null);
        Cursor cursor = db.query("team", columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            int indexID = cursor.getColumnIndex("_id");
            int indexName = cursor.getColumnIndex("name");
            int indexCategory = cursor.getColumnIndex("category");
            int indexPath = cursor.getColumnIndex("path");
            Team team = new Team(cursor.getInt(indexID),cursor.getShort(indexCategory),cursor.getString(indexName),cursor.getString(indexPath));
            teams.add(team);
            cursor.moveToNext();
        }
        return teams;
    }

    public void updateTodo(int teamId, Team team) {
        db.update("team", team.toContentValues(), "_id=" + teamId, null);
    }

    public Team getTeam(int teamId) {
        Cursor c = db.rawQuery("SELECT _id, name, category, path FROM team WHERE _id=" + teamId, null);
        c.moveToFirst();
        return new Team(c.getInt(0),c.getShort(2),c.getString(1),c.getString(3));
    }
}
