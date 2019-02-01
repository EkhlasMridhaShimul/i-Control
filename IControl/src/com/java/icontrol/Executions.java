/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.icontrol;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ghost
 */
public class Executions {
    
    Controls control = new Controls();
    Shutdown shutdown = new Shutdown();
    String msg;
    String work;
    public static ObjectOutputStream obout2;
    public static ObjectInputStream obin2;
    public static Socket socket2=null;
    public static boolean status = false;
    
    public static Thread thread3;
    
    
    public Executions(){
        Back.makeConnection();
        commands();
    }
    
    public void commands(){
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screen.height;
        int width = screen.width;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        String cmd = (String) Back.obin.readObject();
                        msg=cmd;
                        //System.out.println(cmd);
                        if(cmd!=null){
                            switch(cmd){
                                case "LEFT_CLICK":
                                    control.leftClick();
                                    break;
                                case "RIGHT_CLICK":
                                    control.rightClick();
                                    break;
                                case "MOUSE_PRESS":
                                    control.mousePress();
                                    break;
                                case "MOUSE_MOVE":
                                    int x = (int) Back.obin.readObject();
                                    int y = (int) Back.obin.readObject();
                                    Point p = MouseInfo.getPointerInfo().getLocation();
                                    float x1 = p.x;
                                    float y1 = p.y;
                                    control.mouseMove((int)(x1+x),(int)(y1+y));
                                    break;
                                case "WHEEL":
                                    int a = (int) Back.obin.readObject();
                                    control.mouseWheel(a);
                                    break;
                                case "SHUTDOWN":
                                    shutdown.shutdown();
                                    break;
                                case "RESTART":
                                    shutdown.restart();
                                    break;
                                case "PRESS_F5":
                                    control.Keyboard_f5();
                                    break;
                                case "PRESS_LEFT":
                                    control.Keyboard_leftArrow();
                                    break;
                                case "PRESS_RIGHT":
                                    control.Keyboard_rightArrow();
                                    break;
                                case "PRESS_ESC":
                                    control.Keyboard_esc();
                                    break;
                                case "DISPLAY":
                                    new ScreenCap().sendScreenshot(Back.obout);
                                    break;
                            }
                        }
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    if(msg.equals("LIVE") && status==false){
                        Main.thread2 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    System.out.println("Opening port .........");
                                    Back.sv = new ServerSocket(4005);
                                    System.out.println("Waiting on port: 4005");
                                    socket2 = Back.sv.accept();
                                    System.out.println("Connected on port: 4005");
                                    obin2 = new ObjectInputStream(socket2.getInputStream());
                                    System.out.println("Stream connected");
                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
                        Main.thread2.start();
                        try{
                            Main.thread2.join();
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while(msg.equals("LIVE")){
                                    try{
                                        new ScreenCap().sendScreenshot(Back.obout);
                                        Back.obout.flush();
                                        msg= (String) Back.obin.readObject();
                                        System.out.println("live"+msg);
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            }
                    }).start();
                    while(msg.equals("LIVE")){
                        try {
                            String message = (String) obin2.readObject();
                            System.out.println(message);
                            if(message!=null){
                                switch(message){
                                    case "LEFT_CLICK":
                                        control.leftClick();
                                        break;
                                    case "MOVE_LIVE":
                                        String x = (String) obin2.readObject();
                                        String y = (String) obin2.readObject();
                                        System.out.println(x+"  "+y);
                                        float xCord = Float.parseFloat(x);
                                        float yCord = Float.parseFloat(y);
                                        xCord = xCord * width;
                                        yCord = yCord * height;
                                        control.mouseMove((int) xCord, (int) yCord);
                                        break;
                                    case "RIGHT_CLICK":
                                        control.rightClick();
                                        break;
                                    case "DOUBLE_CLICK":
                                        control.doubleClick();
                                        break;
                                    case "WHEEL":
                                        int wheel = (int) obin2.readObject();
                                        control.mouseWheel(wheel);
                                        break;
                                    case "OFF":
                                        status=true;
                                        break;
                                }
                            }

                        } catch (Exception e) {
                            System.out.println(e);
                        }  
                           
                    }
                }
            }
        }).start();
    }
}
