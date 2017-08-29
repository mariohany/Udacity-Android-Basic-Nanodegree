package com.example.mario.musicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PlaylistsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlists);
        TextView songs = (TextView) findViewById(R.id.songsTab);
        songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent songsIntent = new Intent(PlaylistsActivity.this,SongsActivity.class);
                startActivity(songsIntent);
            }
        });
        TextView albums = (TextView) findViewById(R.id.albumsTab);
        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent albumsIntent = new Intent(PlaylistsActivity.this,AlbumsActivity.class);
                startActivity(albumsIntent);
            }
        });
    }
}
