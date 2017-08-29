package com.example.mariohany.tourguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout hotel = (LinearLayout) findViewById(R.id.hotel);
        LinearLayout place = (LinearLayout) findViewById(R.id.places);
        LinearLayout restaurant = (LinearLayout) findViewById(R.id.restaurant);
        LinearLayout museum = (LinearLayout) findViewById(R.id.museum);

        hotel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,HotelActivity.class);
                startActivity(intent);
            }
        });

        place.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PlaceActivity.class);
                startActivity(intent);
            }
        });

        restaurant.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RestaurantActivity.class);
                startActivity(intent);
            }
        });

        museum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MuseumActivity.class);
                startActivity(intent);
            }
        });
    }
}
