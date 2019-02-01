package com.ghost.project2.Connection;

import android.content.Context;
import android.os.AsyncTask;

import com.ghost.project2.Main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public abstract class Connect extends AsyncTask<Void,Void,Socket> {

    String ip;
    Context context;

    public Connect(String ip, Context context){
        this.ip = ip;
        this.context=context;
    }

    @Override
    protected Socket doInBackground(Void... voids) {
        try{
            ConnectFrag.socket1 = new Socket(ip,4004);
            ConnectFrag.obout = new ObjectOutputStream(ConnectFrag.socket1.getOutputStream());
            ConnectFrag.obin = new ObjectInputStream(ConnectFrag.socket1.getInputStream());
        }catch(Exception e){
            e.printStackTrace();
        }
        return ConnectFrag.socket1;
    }

    @Override
    protected void onPostExecute(Socket s) {
        receivedata(s);
    }

    public abstract void receivedata(Object result);
}
