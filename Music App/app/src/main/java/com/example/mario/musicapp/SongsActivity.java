package com.example.mario.musicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SongsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);
        TextView albums = (TextView) findViewById(R.id.albumsTab);
        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent albumsIntent = new Intent(SongsActivity.this,AlbumsActivity.class);
                startActivity(albumsIntent);
            }
        });
        TextView playLists = (TextView) findViewById(R.id.playListsTab);
        playLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playListsIntent = new Intent(SongsActivity.this,PlaylistsActivity.class);
                startActivity(playListsIntent);
            }
        });
    }
}
