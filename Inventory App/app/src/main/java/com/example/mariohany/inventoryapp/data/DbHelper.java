package com.example.mariohany.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.COLUMN_ITEM_IMAGE;
import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.COLUMN_ITEM_NAME;
import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.COLUMN_ITEM_PRICE;
import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.COLUMN_ITEM_QUANTITY;
import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.TABLE_NAME;
import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry._ID;

/**
 * Created by Mario Hany on 2017-08-11.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "inventory.db";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PRODUCTS_TABLE =  "CREATE TABLE " + TABLE_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ITEM_NAME + " TEXT NOT NULL, "
                + COLUMN_ITEM_QUANTITY + " LONG NOT NULL DEFAULT 0, "
                + COLUMN_ITEM_PRICE + " REAL NOT NULL DEFAULT 0.0, "
                + COLUMN_ITEM_IMAGE + " TEXT);";

        db.execSQL(SQL_CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
