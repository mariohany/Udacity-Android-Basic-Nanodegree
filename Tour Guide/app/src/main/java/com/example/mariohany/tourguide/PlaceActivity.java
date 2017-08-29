package com.example.mariohany.tourguide;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PlaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_list);

        final ArrayList<Place> places = new ArrayList<Place>();
        places.add(new Place(getString(R.string.abo_simble_temple), getString(R.string.abo_simble_temple_des),R.drawable.abu_simbel_temple, getString(R.string.abo_simble_temple_location)));
        places.add(new Place(getString(R.string.giza_pyramids), getString(R.string.giza_pyramids_des),R.drawable.ahramat, getString(R.string.giza_pyramids_location)));
        places.add(new Place(getString(R.string.blue_hole), getString(R.string.blue_hole_des),R.drawable.blue_hole, getString(R.string.blue_hole_location)));
        places.add(new Place(getString(R.string.kan_el_kalely), getString(R.string.kan_el_kalely_des),R.drawable.kan_el_kalely, getString(R.string.kan_el_kalely_location)));
        places.add(new Place(getString(R.string.aswan_nile_cruse), getString(R.string.aswan_nile_cruse_des),R.drawable.nile_aswan, getString(R.string.aswan_nile_cruse_location)));
        places.add(new Place(getString(R.string.wshwash_vally), getString(R.string.wshwash_vally_des),R.drawable.wady_al_wshwash, getString(R.string.wshwash_vally_location)));

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
