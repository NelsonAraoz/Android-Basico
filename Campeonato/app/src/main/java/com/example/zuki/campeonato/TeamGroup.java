package com.example.zuki.campeonato;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zuki.campeonato.model.Team;

import java.io.FileNotFoundException;
import java.io.InputStream;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeamGroup.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeamGroup#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamGroup extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TEAM = "team";

    // TODO: Rename and change types of parameters




    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TeamGroup.
     */
    // TODO: Rename and change types and number of parameters
    private Team team;
    private Context context;
    private View rootView;
    private TextView text;
    public static TeamGroup newInstance(Team team) {
        TeamGroup fragment = new TeamGroup();
        Bundle args = new Bundle();
        args.putSerializable(TEAM, team);
        fragment.setArguments(args);
        return fragment;
    }

    public TeamGroup() {
        // Required empty public constructor
    }
    public Team getTeam()
    {
        return team;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            team =(Team)getArguments().getSerializable(TEAM);
        }
    }
    public void setScore(int score)
    {
        try {
            text.setText("   ");
            text.setText(score + "");
        }
        catch(Exception ex)
        {}
    }
    private void setView(View view)
    {

        try {
        final Uri imageUri = Uri.parse(team.getPath());
        final InputStream imageStream = context.getContentResolver().openInputStream(imageUri);
        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
        ((ImageView)view.findViewById(R.id.imageView17)).setImageBitmap(selectedImage);
    } catch (Exception e) {
        e.printStackTrace();
    }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_team_group, container, false);
        context= rootView.getContext();
        text=(TextView) rootView.findViewById(R.id.textView55);
        if(team!=null)
            setView(rootView);
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }



}
