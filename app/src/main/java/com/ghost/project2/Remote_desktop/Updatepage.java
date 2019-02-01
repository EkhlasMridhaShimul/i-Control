package com.ghost.project2.Remote_desktop;

import android.os.AsyncTask;

import com.ghost.project2.Connection.ConnectFrag;
import com.ghost.project2.GetStoragePath;
import com.ghost.project2.Main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class Updatepage extends AsyncTask<Void,Void,String>{

    @Override
    protected String doInBackground(Void... voids) {

        FileOutputStream fout = null;
        String path = new GetStoragePath().getStoragePath();
        path = path + "/IControl/display.png";
        File file = new File(path);
        File dirs = new File(file.getParent());
        if(!dirs.exists()){
            dirs.mkdirs();
        }
        try{
            if(ConnectFrag.socket1!=null){
                if(ConnectFrag.obin==null){
                    ConnectFrag.obin = new ObjectInputStream(ConnectFrag.socket1.getInputStream());
                }
            }
            fout = new FileOutputStream(file);
            byte buff[]= new byte[4096];
            int size =(int) ConnectFrag.obin.readObject();
            int read = 0;
            int remain = size;
            while ((read = ConnectFrag.obin.read(buff, 0, Math.min(buff.length, remain))) > 0) {
                remain -= read;
                fout.write(buff, 0, read);
                fout.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return path;
    }

    @Override
    protected void onPostExecute(String result) {
        receiveData(result);
    }

    public abstract void receiveData(Object result);
}
