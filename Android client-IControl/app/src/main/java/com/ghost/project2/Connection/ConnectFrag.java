package com.ghost.project2.Connection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ghost.project2.Connection.Connect;
import com.ghost.project2.R;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ConnectFrag extends Fragment {
    public static Socket socket1=null;
    public static Socket socket2=null;

    public static ObjectOutputStream obout;
    public static ObjectInputStream obin;

    public static ObjectOutputStream obout2;
    public static ObjectInputStream obin2;

    public static String ip;
    public static int prt;

    EditText iptext;
    Button bt;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_connect,container,false);
        getActivity().setTitle("Connection");

        iptext =(EditText) v.findViewById(R.id.ip);
        bt =(Button) v.findViewById(R.id.button);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connect();
            }
        });

        return v;
    }

    void connect(){
         ip= iptext.getText().toString().trim();

        new Connect(ip, getView().getContext()){

            @Override
            public void receivedata(Object result) {
                socket1 = (Socket) result;
                if(socket1==null){
                    Toast.makeText(getView().getContext(),"Server is not listening",Toast.LENGTH_SHORT).show();
                }
                else {
                    bt.setText("Connected");
                    bt.setEnabled(false);
                }
            }
        }.execute();
    }
}
