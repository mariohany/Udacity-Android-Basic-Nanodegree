package com.example.mariohany.bookshelf;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mario Hany on 2017-07-29.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Activity context, ArrayList<Book> book) {
        super(context, 0, book);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Book currentBook = getItem(position);

        TextView name = (TextView) listItemView.findViewById(R.id.book_name);
        name.setText(currentBook.getBookName());

        TextView author = (TextView) listItemView.findViewById(R.id.book_Writer);
        author.setText(currentBook.getBookWriter());

        TextView description = (TextView) listItemView.findViewById(R.id.book_des);
        description.setText(currentBook.getBookDescription());

        return listItemView;
    }
}
