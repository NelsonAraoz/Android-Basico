package com.example.zuki.campeonato;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.zuki.campeonato.db.TeamDAO;
import com.example.zuki.campeonato.model.Team;

import java.util.List;
import java.util.Random;

public class Championship extends AppCompatActivity {
    TeamDAO dao;
    int game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_championship);
        dao=new TeamDAO(this);
       initializeTeams();
        game=1;
        }
    public TeamGroup getTeam1()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (game)
        {
            case 1:
               return ((TeamGroup)fragmentManager.findFragmentById(R.id.fragment));
            case 2:
                return ((TeamGroup)fragmentManager.findFragmentById(R.id.fragment3));
            case 3:
                return ((TeamGroup)fragmentManager.findFragmentById(R.id.fragment8));
            case 4:
                return ((TeamGroup)fragmentManager.findFragmentById(R.id.fragment10));
            case 5:
                return ((TeamGroup)fragmentManager.findFragmentById(R.id.fragment5));
            case 6:
                return ((TeamGroup)fragmentManager.findFragmentById(R.id.fragment12));
            case 7:
                return ((TeamGroup)fragmentManager.findFragmentById(R.id.fragment7));
        }
        return null;
    }
    public TeamGroup getTeam2()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (game)
        {
            case 1:
                return ((TeamGroup)fragmentManager.findFragmentById(R.id.fragment2));
            case 2:
                return ((TeamGroup)fragmentManager.findFragmentById(R.id.fragment4));
            case 3:
                return ((TeamGroup)fragmentManager.findFragmentById(R.id.fragment9));
            case 4:
                return ((TeamGroup)fragmentManager.findFragmentById(R.id.fragment11));
            case 5:
                return ((TeamGroup)fragmentManager.findFragmentById(R.id.fragment6));
            case 6:
                return ((TeamGroup)fragmentManager.findFragmentById(R.id.fragment13));
            case 7:
                return ((TeamGroup)fragmentManager.findFragmentById(R.id.fragment14));
        }
        return null;
    }
    public int getKey()
    {
        switch (game)
        {
            case 1:
                return R.id.fragment5;
            case 2:
                return R.id.fragment6;
            case 3:
                return R.id.fragment12;
            case 4:
                return R.id.fragment13;
            case 5:
                return R.id.fragment7;
            case 6:
                return R.id.fragment14;
            case 7:
                return R.id.fragment15;

        }
        return -1;
    }
    public void playGame(View v)
    {
        if(game==8)
            finish();
        else {
            play(getTeam1(), getTeam2(), getKey());
            game++;
        }
    }
    private void initializeTeams()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Team> teams= dao.getTeams();
        fragmentManager.beginTransaction().replace(R.id.fragment, TeamGroup.newInstance(teams.get(0))).commit();
        fragmentManager.beginTransaction().replace(R.id.fragment2, TeamGroup.newInstance(teams.get(1))).commit();
        fragmentManager.beginTransaction().replace(R.id.fragment3, TeamGroup.newInstance(teams.get(2))).commit();
        fragmentManager.beginTransaction().replace(R.id.fragment4, TeamGroup.newInstance(teams.get(3))).commit();
        fragmentManager.beginTransaction().replace(R.id.fragment8, TeamGroup.newInstance(teams.get(4))).commit();
        fragmentManager.beginTransaction().replace(R.id.fragment9, TeamGroup.newInstance(teams.get(5))).commit();
        fragmentManager.beginTransaction().replace(R.id.fragment10, TeamGroup.newInstance(teams.get(6))).commit();
        fragmentManager.beginTransaction().replace(R.id.fragment11, TeamGroup.newInstance(teams.get(7))).commit();
    }
    public void play(TeamGroup t1,TeamGroup t2,int key)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        int score1=0;
        int score2=0;
        Random myRandom=new Random();
        int opportunities = myRandom.nextInt(8);
        if(opportunities%2==0) opportunities++;
        Team team1=t1.getTeam();
        Team team2=t2.getTeam();
        int total=team1.getCategory()+team2.getCategory();
        for(int i=0;i<opportunities;i++)
        {
            Random random=new Random();
            final int ran = random.nextInt(100);
            if (ran > (team1.getCategory()*100)/total)
                score2++;
            else
                score1++;
        }
        t1.setScore(score1);
        t2.setScore(score2);
        if(score1>score2)
            fragmentManager.beginTransaction().replace(key, TeamGroup.newInstance(t1.getTeam())).commit();
        else
            fragmentManager.beginTransaction().replace(key, TeamGroup.newInstance(t2.getTeam())).commit();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_championship, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
