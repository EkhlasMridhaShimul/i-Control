package com.ghost.project2.Remote_desktop;

import android.os.AsyncTask;

import com.ghost.project2.Connection.ConnectFrag;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Openport extends AsyncTask<Void,Void,Void> {
    String s="AllOK";
    @Override
    protected Void doInBackground(Void... voids) {
        try{
            ConnectFrag.socket2 = new Socket(ConnectFrag.ip,4005);
            ConnectFrag.obout2 = new ObjectOutputStream(ConnectFrag.socket2.getOutputStream());
            ConnectFrag.obout2.writeObject(s);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
