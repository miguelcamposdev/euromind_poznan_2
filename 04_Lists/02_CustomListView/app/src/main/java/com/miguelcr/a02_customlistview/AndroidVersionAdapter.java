package com.miguelcr.a02_customlistview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by miguelcampos on 21/10/16.
 */
public class AndroidVersionAdapter extends ArrayAdapter<AndroidVersions> {
    private Context ctx;
    private int layout;
    private List<AndroidVersions> values;

    public AndroidVersionAdapter(Context context, int resource, List<AndroidVersions> objects) {
        super(context, resource, objects);
        ctx = context;
        layout = resource;
        values = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(ctx).inflate(layout, parent, false);

        // 1. Get the current object from the List of data (values)
        AndroidVersions current = values.get(position);

        // 2. Get all the information that we have in current object
        String name = current.getName();
        int icon = current.getIcon();

        // 3. Get all the components that we have in our layout
        ImageView imageViewIcon = (ImageView) v.findViewById(R.id.imageViewIcon);
        TextView textViewName = (TextView)v.findViewById(R.id.textViewName);

        // 4. Set the data for the current object in the view components
        imageViewIcon.setImageResource(icon);
        textViewName.setText(name);

        return v;
    }
}
