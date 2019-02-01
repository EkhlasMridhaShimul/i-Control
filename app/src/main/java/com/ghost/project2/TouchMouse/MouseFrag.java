package com.ghost.project2.TouchMouse;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ghost.project2.Connection.ConnectFrag;
import com.ghost.project2.Main;
import com.ghost.project2.R;


public class MouseFrag extends Fragment {

    public static TextView touch,scroll;
    public static Button Lbutton,Rbutton;
    String m=null;
    int inX,inY,dX,dY;
    boolean moved = false;
    long mouseTouch1,mouseTouch2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mouse,container,false);


        touch = (TextView) root.findViewById(R.id.touchpad);
        Lbutton = (Button) root.findViewById(R.id.leftclick);
        Rbutton = (Button) root.findViewById(R.id.rightclick);
        scroll = (TextView) root.findViewById(R.id.scrollbar);

        Lbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main.sendmessageCommand("LEFT_CLICK");
            }
        });
        Rbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main.sendmessageCommand("RIGHT_CLICK");
            }
        });

        scroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(ConnectFrag.socket1!=null){
                    switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
                        case MotionEvent.ACTION_DOWN:
                            inX = (int) motionEvent.getX();
                            inY = (int) motionEvent.getY();
                            moved=false;
                            break;
                        case MotionEvent.ACTION_MOVE:
                            dY = (int) motionEvent.getY()- inY;
                            dY = (int) dY / 10;
                            inY = (int) motionEvent.getY();
                            if(dY != 0) {
                                Main.sendmessageCommand("WHEEL");
                                Main.sendmessageCommand(dY);
                                moved=true;
                            }
                            break;
                    }
                }
                return true;
            }
        });

        touch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(ConnectFrag.socket1!=null){
                    switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
                        case MotionEvent.ACTION_DOWN:
                            inX = (int) motionEvent.getX();
                            inY = (int) motionEvent.getY();
                            mouseTouch1 = System.currentTimeMillis();
                            moved=false;
                            break;
                        case MotionEvent.ACTION_MOVE:
                            dX= (int) motionEvent.getX() - inX;
                            dY = (int) motionEvent.getY() - inY;

                            inX = (int) motionEvent.getX();
                            inY = (int) motionEvent.getY();

                            if(dX!=0 || dY!=0){
                                Main.sendmessageCommand("MOUSE_MOVE");
                                Main.sendmessageCommand(dX);
                                Main.sendmessageCommand(dY);
                                moved = true;
                            }
                            break;
                        case MotionEvent.ACTION_CANCEL:
                        case MotionEvent.ACTION_UP:
                            mouseTouch2 = System.currentTimeMillis();
                            long interval = mouseTouch2-mouseTouch1;
                            if(interval>1000 && moved==false){
                                Main.sendmessageCommand("MOUSE_PRESS");
                            }
                            else if(!moved){
                                Main.sendmessageCommand("LEFT_CLICK");
                            }
                            break;
                    }
                }
                return true;
            }
        });

        return root;
    }
}
