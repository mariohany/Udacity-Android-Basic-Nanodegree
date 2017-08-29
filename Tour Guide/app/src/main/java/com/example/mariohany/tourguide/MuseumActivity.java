package com.example.mariohany.tourguide;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MuseumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_list);

        final ArrayList<Place> places = new ArrayList<Place>();
        places.add(new Place(getString(R.string.egyptian_museum_name), getString(R.string.egyptian_museum_des),R.drawable.egyptian, getString(R.string.egyptian_museum_location)));
        places.add(new Place(getString(R.string.islamic_museum_name), getString(R.string.islamic_museum_des),R.drawable.islamik, getString(R.string.islamic_museum_location)));
        places.add(new Place(getString(R.string.coptic_museum_name), getString(R.string.coptic_museum_des),R.drawable.coptic, getString(R.string.coptic_museum_location)));

        PlaceAdapter adapter = new PlaceAdapter(this,places);
        ListView lista = (ListView) findViewById(R.id.list);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Place item = places.get(position);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(item.getLocation()));
                startActivity(intent);
            }
        });
    }
}
