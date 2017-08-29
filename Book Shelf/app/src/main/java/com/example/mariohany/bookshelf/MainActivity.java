package com.example.mariohany.bookshelf;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Book>> {

    private static final String LOG_TAG = MainActivity.class.getName();
    public static final String BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    EditText searchBar;
    Button searchButton;
    static String searchText;
    ListView listView;
    BookAdapter adapter;
    public static TextView errorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchBar = (EditText) findViewById(R.id.search_bar);
        searchButton =(Button) findViewById(R.id.search_button);
        listView = (ListView) findViewById(R.id.list);
        adapter = new BookAdapter(this, new ArrayList<Book>());
        errorMsg = (TextView) findViewById(R.id.error_msg);
        Log.i(LOG_TAG,"Loader (OnCreate mainActivity) NOW RUN");
        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                errorMsg.clearComposingText();
                searchText = searchBar.getText().toString().trim();
                if(isNetworkAvailable(getApplicationContext())){
                    if(searchText == null || searchText.length() == 0){
                        Toast.makeText(getApplicationContext(),"Please Enter a Book Name", Toast.LENGTH_LONG).show();
                        errorMsg.setText("Please Enter a Book Name in Search bar");
                    }else{
                        listView.setAdapter(adapter);
                        errorMsg.setVisibility(View.INVISIBLE);
                        getLoaderManager().initLoader(1,null,MainActivity.this).forceLoad();
                    }
                }else{
                    errorMsg.setText("try again");
                    errorMsg.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Check your Internet Connection and try again !", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


    @Override
    public Loader<ArrayList<Book>> onCreateLoader(int i, Bundle bundle) {
        Log.i(LOG_TAG,"Loader (OnCreate) NOW RUN");
        return new BookLoader(MainActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Book>> loader, ArrayList<Book> books) {
        Log.i(LOG_TAG,"Loader (OnLoadFinished) NOW RUN");
        if(books != null && !books.isEmpty()){
            adapter = new BookAdapter(this, books);
            listView.setAdapter(adapter);
        }else{
            Toast.makeText(getApplicationContext(), "NO DATA !!", Toast.LENGTH_LONG);
            errorMsg.setText("NO DATA FOUND !!");
            errorMsg.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Book>> loader) {
        Log.i(LOG_TAG,"Loader (OnLoaderReset) NOW RUN");
        adapter = new BookAdapter(this, new ArrayList<Book>());
        listView.setAdapter(adapter);
    }
}
