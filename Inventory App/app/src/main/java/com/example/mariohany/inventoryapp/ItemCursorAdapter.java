package com.example.mariohany.inventoryapp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.mariohany.inventoryapp.R.id.price;
import static com.example.mariohany.inventoryapp.R.id.quantity;
import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.COLUMN_ITEM_IMAGE;
import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.COLUMN_ITEM_NAME;
import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.COLUMN_ITEM_PRICE;
import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.COLUMN_ITEM_QUANTITY;
import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.CONTENT_URI;
import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry._ID;

/**
 * Created by Mario Hany on 2017-08-11.
 */

public class ItemCursorAdapter extends CursorAdapter {

    public ItemCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView quantityTextView = (TextView) view.findViewById(quantity);
        TextView priceTextView = (TextView) view.findViewById(price);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);

        int nameColumnIndex = cursor.getColumnIndex(COLUMN_ITEM_NAME);
        int quantityColumnIndex = cursor.getColumnIndex(COLUMN_ITEM_QUANTITY);
        int priceColumnIndex = cursor.getColumnIndex(COLUMN_ITEM_PRICE);
        int imageColumnIndex = cursor.getColumnIndex(COLUMN_ITEM_IMAGE);

        final String itemName = cursor.getString(nameColumnIndex);
        final Long itemQuantity = cursor.getLong(quantityColumnIndex);
        final float itemPrice = cursor.getFloat(priceColumnIndex);
        final String itemImage = cursor.getString(imageColumnIndex);

        nameTextView.setText(itemName);
        quantityTextView.setText(Long.toString(itemQuantity));
        priceTextView.setText(Float.toString(itemPrice));
        if(itemImage != null){
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageURI(Uri.parse(itemImage));
        }else{
            imageView.setVisibility(View.GONE);
        }
        Button button = (Button) view.findViewById(R.id.sell_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view != null) {
                    Object obj = view.getTag();
                    String st = obj.toString();
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_ITEM_NAME, itemName);
                    values.put(COLUMN_ITEM_QUANTITY, itemQuantity >= 1? itemQuantity-1: 0);
                    values.put(COLUMN_ITEM_PRICE, itemPrice);
                    values.put(COLUMN_ITEM_IMAGE, itemImage);

                    Uri currentPetUri = ContentUris.withAppendedId(CONTENT_URI, Integer.parseInt(st));

                    int rowsAffected = context.getContentResolver().update(currentPetUri, values, null, null);
                    if (rowsAffected == 0 || itemQuantity == 0) {
                        Toast.makeText(context, "Failed !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        Object obj = cursor.getInt(cursor.getColumnIndex(_ID));
        button.setTag(obj);

    }
}
