package com.example.mariohany.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mario Hany on 2017-07-30.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, ArrayList<News> newses) {
        super(context, 0, newses);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        News currentNews = getItem(position);

        TextView title = (TextView) listItemView.findViewById(R.id.title);
        title.setText(currentNews.getTitle());

        TextView type = (TextView) listItemView.findViewById(R.id.type);
        type.setText(currentNews.getType());

        TextView section = (TextView) listItemView.findViewById(R.id.section);
        section.setText(currentNews.getSectionName());

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        date.setText(currentNews.getDate());

        return listItemView;
    }
}
