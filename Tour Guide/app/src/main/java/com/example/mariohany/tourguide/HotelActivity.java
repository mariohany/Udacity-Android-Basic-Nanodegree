package com.example.mariohany.tourguide;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class HotelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_list);

        final ArrayList<Place> places = new ArrayList<Place>();
        places.add(new Place(getString(R.string.grand_hotel_name), getString(R.string.grand_hotel_des),R.drawable.grand_hotel, getString(R.string.grand_hotel_location)));
        places.add(new Place(getString(R.string.nile_ritz_carlton), getString(R.string.nile_ritz_carlton_des),R.drawable.nile_ritz_carlton, getString(R.string.nile_ritz_carlton_location)));

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
