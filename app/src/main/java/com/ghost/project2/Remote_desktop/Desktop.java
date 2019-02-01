package com.ghost.project2.Remote_desktop;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.ghost.project2.Connection.ConnectFrag;
import com.ghost.project2.Main;
import com.ghost.project2.R;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class Desktop extends Activity {

    public static ImageView display;
    int displayX,displayY,xCord,yCord,initX,initY;
    Timer timer;
    private GestureDetector mdetect;
    public static boolean status = false;
    public static Updatepage task;
    boolean mouseMoved = false,moved=false;
    long touch1,touch2;

    public static int iniX,iniY,Y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desktop);
        display = (ImageView) findViewById(R.id.display);
        ViewTreeObserver vto = display.getViewTreeObserver();
        Main.sendmessageCommand("LIVE");

        if(status!=true){
            new Openport().execute();
        }

        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                displayX = display.getHeight();
                displayY = display.getWidth();
                ViewTreeObserver vto2 = display.getViewTreeObserver();
                vto2.removeOnGlobalLayoutListener(this);
            }
        });

        display.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(ConnectFrag.socket1!=null){
                    switch(motionEvent.getAction() & MotionEvent.ACTION_MASK){
                        case MotionEvent.ACTION_DOWN:
                            touch1 = System.currentTimeMillis();
                            xCord = displayX - (int) motionEvent.getY();
                            yCord = (int) motionEvent.getX();
                            iniY = (int) motionEvent.getY();
                            initX = xCord;
                            initY = yCord;
                            float sendX =(float) xCord/displayX;
                            float sendY = (float) yCord/displayY;
                            String v =Float.toString(sendX);
                            String v2 =Float.toString(sendY);
                            Main.sendLiveCommand("MOVE_LIVE");
                            Main.sendLiveCommand(v);
                            Main.sendLiveCommand(v2);
                            moved=false;
                            break;
                        case MotionEvent.ACTION_MOVE:
                            Y = (int) motionEvent.getY()- iniY;
                            iniY = (int) motionEvent.getY();
                            if(Y != 0) {
                                Main.sendLiveCommand("WHEEL");
                                Main.sendLiveCommand(Y);
                                moved=true;
                            }
                            break;
                        case MotionEvent.ACTION_CANCEL:
                        case MotionEvent.ACTION_UP:
                            touch2 = System.currentTimeMillis();
                            long interval = touch2-touch1;
                            if(interval>550 && interval<2500 && !moved){
                                Main.sendLiveCommand("DOUBLE_CLICK");
                            }
                            else if(interval>2500 && !moved){
                                Main.sendLiveCommand("RIGHT_CLICK");
                            }
                            else if(!moved){
                                Main.sendLiveCommand("LEFT_CLICK");
                            }
                            break;
                    }
                }
                return true;
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                update();
            }
        }).start();
    }

    public void update(){
        task =new Updatepage(){

            @Override
            public void receiveData(Object result) {
                String path = (String) result;
                Bitmap image = BitmapFactory.decodeFile(path);
                Matrix matrix = new Matrix();
                matrix.postRotate(-90);
                try{
                    Bitmap bitmap = Bitmap.createBitmap(image,0,0,image.getWidth(),image.getHeight(),matrix,true);
                    display.setImageBitmap(bitmap);
                    Main.sendmessageCommand("LIVE");
                }catch(Exception e){
                    e.printStackTrace();
                }
                update();
            }
        };
        task.execute();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Main.sendmessageCommand("OFF");
        Main.sendLiveCommand("OFF");
        status = true;
        this.finish();
        task.cancel(true);
    }
}
