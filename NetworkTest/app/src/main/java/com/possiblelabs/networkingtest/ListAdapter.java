package com.possiblelabs.networkingtest;
        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Color;
        import android.net.Uri;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.RatingBar;
        import android.widget.TextView;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.FileNotFoundException;
        import java.io.InputStream;
        import java.util.List;

/**
 * Created by possiblelabs on 7/17/15.
 */
public class ListAdapter extends ArrayAdapter<String> {


    private Context context;
    private List<String> eq;

    public ListAdapter(Context context, List<String> objects) {
        super(context, R.layout.activity_main, objects);
        this.context = context;
        this.eq = objects;

    }

    @Override
    public String getItem(int position) {
        return eq.get(position);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);
        LinearLayout layout=(LinearLayout) rowView.findViewById(R.id.container);
        TextView location = (TextView) rowView.findViewById(R.id.location);
        TextView magnitude = (TextView) rowView.findViewById(R.id.magnitude);
        try {
            JSONObject object=new JSONObject(eq.get(position));
            location.setText(object.getString("src"));
            magnitude.setText(object.getString("magnitude"));
            double mag=object.getDouble("magnitude");
            if(mag>8)
                layout.setBackgroundColor(Color.RED);
            else if(mag<=8 && mag>5)
                layout.setBackgroundColor(Color.YELLOW);
            else
                layout.setBackgroundColor(Color.GREEN);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rowView;
    }
}

