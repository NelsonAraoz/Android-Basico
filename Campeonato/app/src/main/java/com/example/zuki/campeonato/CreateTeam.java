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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zuki.campeonato.db.TeamDAO;
import com.example.zuki.campeonato.model.Team;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class CreateTeam extends AppCompatActivity {
    private final int SELECT_PHOTO = 1;
    private ImageView imageView;
    TeamDAO teamDAO;
    private TextView name;
    private RatingBar category;
    private String path;
    private boolean isEdit;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);
        Bundle extras=getIntent().getExtras();
        imageView = (ImageView)findViewById(R.id.imageView);
        teamDAO=new TeamDAO(getApplicationContext());
        name=(TextView)findViewById(R.id.box_name);
        category=(RatingBar) findViewById(R.id.ratingValue);
        isEdit=extras!=null;
        if(extras!=null)
        {
            ((TextView)findViewById(R.id.textView2)).setText("Editar Equipo");
            ((Button)findViewById(R.id.button2)).setText("Editar");
            Team team=(Team) getIntent().getSerializableExtra("team");
            id=team.getId();
            path=team.getPath();
            setFields(team);
        }

    }
    private void setFields(Team team)
    {
        name.setText(team.getName());
        category.setRating(team.getCategory());
        try {
            final Uri imageUri = Uri.parse(team.getPath());
            final InputStream imageStream = getContentResolver().openInputStream(imageUri);
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            imageView.setImageBitmap(selectedImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_team, menu);
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
    public void addImage(View view)
    {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }
    public void createTeam(View view)
    {
        if(path!=null) {
            Team team = new Team();
            team.setPath(path);
            team.setName(name.getText().toString());
            team.setCategory((short) category.getRating());
            if (!isEdit) {
                teamDAO.addTeam(team);
                Toast.makeText(getApplicationContext(), "Equipo Creado!",
                        Toast.LENGTH_LONG).show();
            } else {
                team.setId(id);
                teamDAO.updateTodo(team.getId(), team);
                Toast.makeText(getApplicationContext(), "Equipo Actualizado!",
                        Toast.LENGTH_LONG).show();
            }
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Seleccione una imagen",
                    Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    try {
                        final Uri imageUri = imageReturnedIntent.getData();
                        path=imageUri.toString();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        imageView.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}
