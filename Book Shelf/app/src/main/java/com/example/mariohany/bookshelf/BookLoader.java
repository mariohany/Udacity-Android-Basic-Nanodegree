package com.example.mariohany.bookshelf;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;



/**
 * Created by Mario Hany on 2017-07-29.
 */

public class BookLoader extends AsyncTaskLoader<ArrayList<Book>> {
    private static final String LOG_TAG = BookLoader.class.getName();


    public BookLoader(Context context) {
        super(context);
    }

    @Override
    public ArrayList<Book> loadInBackground() {
        if (MainActivity.searchText.length() == 0) {
            return null;
        }
        MainActivity.searchText.replace(" ", "+");
        URL url = createUrl(MainActivity.BOOK_BASE_URL + MainActivity.searchText);
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "IOException", e);
        }
        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }
        ArrayList<Book> books = new ArrayList<>();
        try {
            JSONObject baseJsonResponse = new JSONObject(jsonResponse);
            JSONArray itemsArray = baseJsonResponse.getJSONArray("items");

            for (int i = 0 ; i < itemsArray.length() ; i++){
                JSONObject currentBook = itemsArray.getJSONObject(i);
                JSONObject information = currentBook.getJSONObject("volumeInfo");
                String title = information.getString("title");
                String description = "N/A";
                if(information.has("description")){
                    description = information.getString("description");
                }
                String author = "N/A";
                if(information.has("authors")){
                    author = information.getString("authors");
                }
                books.add(new Book(title, author, description));
            }
        }catch (JSONException e){
            Log.e(LOG_TAG, "Problem parsing the books JSON results", e);
        }
        return books;
    }

    private URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }

    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error With HTTP Request", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
