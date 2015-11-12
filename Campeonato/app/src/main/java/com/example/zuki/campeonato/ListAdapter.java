package com.example.zuki.campeonato;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.zuki.campeonato.model.Team;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by possiblelabs on 7/17/15.
 */
public class ListAdapter extends ArrayAdapter<Team> {


    private Context context;
    private List<Team> teams;

    public ListAdapter(Context context, List<Team> objects) {
        super(context, R.layout.activity_main, objects);
        this.context = context;
        this.teams = objects;

    }

    @Override
    public Team getItem(int position) {
        return teams.get(position);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.frament_item, parent, false);

        TextView todoText = (TextView) rowView.findViewById(R.id.todoText);
        ImageView image = (ImageView) rowView.findViewById(R.id.img_team);
        RatingBar rating= (RatingBar) rowView.findViewById(R.id.ratingBar2);
        todoText.setText(teams.get(position).getName());
        rating.setRating(teams.get(position).getCategory());
        try {
            final Uri imageUri = Uri.parse(teams.get(position).getPath());
            final InputStream imageStream = context.getContentResolver().openInputStream(imageUri);
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            image.setImageBitmap(selectedImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return rowView;
    }
}
