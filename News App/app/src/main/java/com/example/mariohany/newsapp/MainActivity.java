package com.example.mariohany.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<News>>{

    private static final String LOG_TAG = MainActivity.class.getName();
    ListView listView;
    NewsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        if(isNetworkAvailable(this)){
            getLoaderManager().initLoader(1,null,this).forceLoad();
        }else{
            Toast.makeText(this, "NO Internet Connection !!", Toast.LENGTH_LONG);
        }

    }

    public boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Override
    public Loader<ArrayList<News>> onCreateLoader(int i, Bundle bundle) {
        Log.i(LOG_TAG,"Loader (OnCreate) NOW RUN");
        return new NewsLoader(MainActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<News>> loader, final ArrayList<News> newses) {
        Log.i(LOG_TAG,"Loader (OnLoadFinished) NOW RUN");
        if(newses != null && !newses.isEmpty()){
            adapter = new NewsAdapter(this, newses);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    News item = newses.get(i);
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getWebUrl()));
                    startActivity(intent);
                }
            });
        }else{
            Toast.makeText(getApplicationContext(), "NO DATA !!", Toast.LENGTH_LONG);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<News>> loader) {
        Log.i(LOG_TAG,"Loader (OnLoaderReset) NOW RUN");
        adapter = new NewsAdapter(this, new ArrayList<News>());
        listView.setAdapter(adapter);
    }
}
