package com.ghost.project2.PowerPoint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ghost.project2.Main;
import com.ghost.project2.R;

public class Powerpoint extends AppCompatActivity {

    Button buttonLeft,buttonRight,play,exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("PowerPoint");
        setContentView(R.layout.activity_powerpoint);

        play = (Button) findViewById(R.id.playbutton);
        buttonLeft = (Button) findViewById(R.id.leftpoint);
        buttonRight = (Button) findViewById(R.id.rightpoint);
        exit = (Button) findViewById(R.id.exitButton);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main.sendmessageCommand("PRESS_F5");
            }
        });
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main.sendmessageCommand("PRESS_RIGHT");
            }
        });
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main.sendmessageCommand("PRESS_LEFT");
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main.sendmessageCommand("PRESS_ESC");
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
