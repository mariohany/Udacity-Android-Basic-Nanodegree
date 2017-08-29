package com.example.mariohany.newsapp;

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
 * Created by Mario Hany on 2017-07-30.
 */

public class NewsLoader extends AsyncTaskLoader<ArrayList<News>> {

    private static final String LOG_TAG = NewsLoader.class.getName();
    private static final String NEWS_BASE_URL = "http://content.guardianapis.com/search?api-key=test";
    public NewsLoader(Context context) {
        super(context);
    }

    @Override
    public ArrayList<News> loadInBackground() {

        URL url = createUrl(NEWS_BASE_URL);
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "IOException", e);
        }
        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }
        ArrayList<News> newses = new ArrayList<>();
        try {
            JSONObject baseJsonObject = new JSONObject(jsonResponse);
            JSONObject responseJsonObject = baseJsonObject.getJSONObject("response");
            JSONArray newsArray = responseJsonObject.getJSONArray("results");

            for (int i = 0 ; i < newsArray.length() ; i++){
                JSONObject newsJsonObject = newsArray.getJSONObject(i);

                String title;
                if (newsJsonObject.has("webTitle")){
                    title = newsJsonObject.getString("webTitle");
                }else{
                    title = "NO TITLE";
                }

                String type;
                if(newsJsonObject.has("type")){
                    type = newsJsonObject.getString("type");
                }else{
                    type = "Unknown";
                }

                String section;
                if (newsJsonObject.has("sectionName")){
                    section = newsJsonObject.getString("sectionName");
                }else{
                    section = "Unknown";
                }

                String date;
                if (newsJsonObject.has("webPublicationDate")){
                    date = newsJsonObject.getString("webPublicationDate");
                }else{
                    date = "Unknown";
                }

                String webUrl;
                webUrl = newsJsonObject.getString("webUrl");
                newses.add(new News(title, type, section, date, webUrl));
            }
        }catch (JSONException e){
            Log.e(LOG_TAG, "Problem parsing the books JSON results", e);
        }
        return newses;
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
