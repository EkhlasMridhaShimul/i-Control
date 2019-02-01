package com.ghost.project2;

import android.os.AsyncTask;

import com.ghost.project2.Connection.Connect;
import com.ghost.project2.Connection.ConnectFrag;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SendCommand extends AsyncTask<String,Void,Void> {

    ObjectOutputStream os;

    public SendCommand(ObjectOutputStream os){
        this.os = os;
    }

    @Override
    protected Void doInBackground(String... strings) {
        String command = strings[0];
        String type = strings[1];

        int i;
        float f;
        long l;

        if(type.equals("STRING")){
            try {
                os.writeObject(command);
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("INT")){
            try{
                i = Integer.parseInt(command);
                os.writeObject(i);
                os.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else if(type.equals("FLOAT")){
            try{
                f=Integer.parseInt(command);
                os.writeObject(f);
                os.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else if(type.equals("LONG")){
            try{
                l = Integer.parseInt(command);
                os.writeObject(l);
                os.flush();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
