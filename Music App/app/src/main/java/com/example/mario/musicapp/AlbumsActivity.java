package com.example.mario.musicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AlbumsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        TextView songs = (TextView) findViewById(R.id.songsTab);
        songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent songsIntent = new Intent(AlbumsActivity.this,SongsActivity.class);
                startActivity(songsIntent);
            }
        });
        TextView playLists = (TextView) findViewById(R.id.playListsTab);
        playLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playListsIntent = new Intent(AlbumsActivity.this,PlaylistsActivity.class);
                startActivity(playListsIntent);
            }
        });
    }
}
