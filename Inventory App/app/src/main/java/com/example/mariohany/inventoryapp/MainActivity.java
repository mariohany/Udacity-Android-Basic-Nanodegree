package com.example.mariohany.inventoryapp;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.COLUMN_ITEM_IMAGE;
import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.COLUMN_ITEM_NAME;
import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.COLUMN_ITEM_PRICE;
import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.COLUMN_ITEM_QUANTITY;
import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry.CONTENT_URI;
import static com.example.mariohany.inventoryapp.data.ItemContract.ItemEntry._ID;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int LOADER_ID = 0;
    ItemCursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.list);
        TextView emptyView = (TextView) findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewItem.class);
                startActivity(intent);
            }
        });

        adapter = new ItemCursorAdapter(this,null);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                Uri currentItemUri = ContentUris.withAppendedId(CONTENT_URI, id);
                intent.setData(currentItemUri);
                startActivity(intent);

            }
        });

        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                _ID,
                COLUMN_ITEM_NAME,
                COLUMN_ITEM_QUANTITY,
                COLUMN_ITEM_PRICE,
                COLUMN_ITEM_IMAGE};

        return new CursorLoader(this,
                CONTENT_URI,
                projection,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        adapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
