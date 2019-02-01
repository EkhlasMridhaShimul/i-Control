package com.ghost.project2;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.ghost.project2.Connection.ConnectFrag;
import com.ghost.project2.TouchMouse.MouseFrag;

import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main extends AppCompatActivity {

    public static Socket s2;
    public static DataOutputStream dos2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkForPermission();
        }

        Fragment fragment = new ConnectFrag();
        final FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();

        BottomNavigationView Bnavigation = (BottomNavigationView) findViewById(R.id.navigation);

        Bnavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if(item.getItemId() == R.id.nav_connect){
                    fragment = new ConnectFrag();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame,fragment);
                    fragmentTransaction.commit();
                }else if(item.getItemId() == R.id.nav_message){
                    fragment = new MouseFrag();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame,fragment);
                    fragmentTransaction.commit();
                }else if(item.getItemId() == R.id.nav_ScreenShare){
                    fragment = new Tools();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame,fragment);
                    fragmentTransaction.commit();
                }
                return false;
            }
        });
    }

    public static void sendmessageCommand(String msg){
        if(ConnectFrag.socket1!=null){
            new SendCommand(ConnectFrag.obout).execute(String.valueOf(msg),"STRING");
        }
    }



    public static void sendmessageCommand(int msg){
        if(ConnectFrag.socket1!=null){
            new SendCommand(ConnectFrag.obout).execute(String.valueOf(msg),"INT");
        }
    }

    public static void sendmessageCommand(float msg){
        if(ConnectFrag.socket1!=null){
            new SendCommand(ConnectFrag.obout).execute(String.valueOf(msg),"FLOAT");
        }
    }

    public static void sendmessageCommand(long msg){
        if(ConnectFrag.socket1!=null){
            new SendCommand(ConnectFrag.obout).execute(String.valueOf(msg),"LONG");
        }
    }

    public static void sendLiveCommand(String msg){
        if(ConnectFrag.socket1!=null){
            new SendCommand(ConnectFrag.obout2).execute(String.valueOf(msg),"STRING");
        }
    }



    public static void sendLiveCommand(int msg){
        if(ConnectFrag.socket1!=null){
            new SendCommand(ConnectFrag.obout2).execute(String.valueOf(msg),"INT");
        }
    }

    public static void sendLiveCommand(float msg){
        if(ConnectFrag.socket1!=null){
            new SendCommand(ConnectFrag.obout2).execute(String.valueOf(msg),"FLOAT");
        }
    }

    public static void sendLiveCommand(long msg){
        if(ConnectFrag.socket1!=null){
            new SendCommand(ConnectFrag.obout2).execute(String.valueOf(msg),"LONG");
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkForPermission() {
        if (this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (this.shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "Read Permission is necessary to transfer", Toast.LENGTH_LONG).show();
            } else {
                this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }
}
