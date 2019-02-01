package com.ghost.project2.MusicPlayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ghost.project2.R;

public class Music extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        setTitle("Music Player");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
