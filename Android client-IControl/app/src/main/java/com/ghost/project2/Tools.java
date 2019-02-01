package com.ghost.project2;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ghost.project2.Main;
import com.ghost.project2.MusicPlayer.Music;
import com.ghost.project2.PowerPoint.Powerpoint;
import com.ghost.project2.R;
import com.ghost.project2.Remote_desktop.Desktop;


public class Tools extends Fragment {
    Button desktop;
    Button player;
    Button shutdown;
    Button powerpoint;
    String command;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tools,container,false);
        getActivity().setTitle("Tools");
        desktop = (Button) view.findViewById(R.id.button1);
        player = (Button) view.findViewById(R.id.button2);
        shutdown = (Button) view.findViewById(R.id.button3);
        powerpoint = (Button) view.findViewById(R.id.button4);
        desktop.getBackground().setAlpha(30);
        player.getBackground().setAlpha(30);
        shutdown.getBackground().setAlpha(30);
        powerpoint.getBackground().setAlpha(30);

        desktop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Desktop.class);
                startActivity(intent);
            }
        });
        powerpoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Powerpoint.class);
                startActivity(intent);
            }
        });
        shutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                 View v = inflater.inflate(R.layout.popup_shutdown,null);
                 Button shutdown1 = (Button) v.findViewById(R.id.shutdown);
                 Button restart1 = (Button) v.findViewById(R.id.restart);
                 builder.setView(v);
                 final AlertDialog dialog = builder.create();
                 dialog.show();

                 shutdown1.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         command = "SHUTDOWN";
                         Main.sendmessageCommand(command);
                         dialog.hide();
                     }
                 });
                restart1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        command = "RESTART";
                        Main.sendmessageCommand(command);
                        dialog.hide();
                    }
                });
            }
        });
        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Music.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
