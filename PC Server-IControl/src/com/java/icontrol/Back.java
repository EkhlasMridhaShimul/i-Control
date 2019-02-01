/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.icontrol;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ghost
 */
public class Back extends JPanel{
    
    public JLabel ipLabel = new JLabel();
    public JLabel InfoLabel = new JLabel();
    String ip = null;
    public static String status = "Not Connected",help = "Enter the ip on the client app to connect";
    
    public static ServerSocket sv = null,sv2 = null;
    public static Socket skt = null;
    public static ObjectInputStream obin = null;
    public static ObjectOutputStream obout = null;
    public static DataInputStream dis = null;
    public static DataOutputStream dos = null;
    public Back(){
        this.setVisible(true);
        this.setLayout(new FlowLayout());
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(Image2.back, 0, 0, null);
        String ipAddresses[] = new Getip().ip();
        String ipAddress = ipAddresses[0];
        if (ipAddresses[1] != null) {
            ipAddress = ipAddress + " | " + ipAddresses[1];
        }
        ip = "IP: " +ipAddress;
        g.setFont(new Font("Azonix", Font.BOLD, 30));
        g.setColor(Color.blue);
        g.drawString(ip, Main.height/2-30,Main.width/2-100);
        g.setColor(Color.white);
        g.setFont(new Font("Audiowide", Font.BOLD, 15));
        g.drawString(help,150, Main.width-260);
        g.setFont(new Font("Nevis", Font.BOLD, 18));
        g.setColor(Color.red);
        if(status=="    Connected"){
            g.setColor(Color.GREEN);
        }
        g.drawString(status, Main.height/2+40,(Main.width/2)-60);
    }
    public static void makeConnection() {
        Main.thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                sv = new ServerSocket(4004);
                System.out.println("Waiting to connect");
                skt = sv.accept();
                status = "    Connected";
                obin = new ObjectInputStream(skt.getInputStream());
                obout = new ObjectOutputStream(skt.getOutputStream());
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        
        Main.thread1.start();
        try{
            Main.thread1.join();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    } 
}
