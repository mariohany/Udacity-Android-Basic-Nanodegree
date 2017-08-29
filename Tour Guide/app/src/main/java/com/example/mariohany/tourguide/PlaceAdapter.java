package com.example.mariohany.tourguide;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mario on 6/13/2017.
 */

public class PlaceAdapter extends ArrayAdapter<Place> {

    public PlaceAdapter(Activity context, ArrayList<Place> place) {
        super(context, 0, place);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Place currentPlace = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name_text_iew);
        nameTextView.setText(currentPlace.getPlaceName());

        TextView desTextView = (TextView) listItemView.findViewById(R.id.des_text_view);
        desTextView.setText(currentPlace.getPlaceDes());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        imageView.setImageResource(currentPlace.getImageResourceId());

        return listItemView;
    }
}
