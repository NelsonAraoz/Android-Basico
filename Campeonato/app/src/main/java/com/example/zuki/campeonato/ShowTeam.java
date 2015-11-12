package com.example.zuki.campeonato;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zuki.campeonato.db.TeamDAO;
import com.example.zuki.campeonato.model.Team;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ShowTeam extends AppCompatActivity {

    TeamDAO teamDAO;
    Team team;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_team);
         team=(Team) getIntent().getSerializableExtra("team");
        teamDAO=new TeamDAO(getApplicationContext());
        setFields(team);

    }
    public void deleteTeam(View view)
    {
        teamDAO.deleteTeam(team.getId());
        Toast.makeText(getApplicationContext(), "Equipo Eliminado!",
                Toast.LENGTH_LONG).show();
        finish();
    }
    public void editTeam(View view)
    {
        Intent i = new Intent(ShowTeam.this, CreateTeam.class);
        i.putExtra("team", team);
        startActivity(i);
        finish();
    }
    private void setFields(Team team)
    {
        ((TextView)findViewById(R.id.show_txt_name)).setText(team.getName());
        ((RatingBar)findViewById(R.id.show_rating)).setRating(team.getCategory());
        try {
            final Uri imageUri = Uri.parse(team.getPath());
            final InputStream imageStream = getContentResolver().openInputStream(imageUri);
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            ((ImageView)findViewById(R.id.show_image)).setImageBitmap(selectedImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_team, menu);
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
