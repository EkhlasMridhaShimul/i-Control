/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.icontrol;


import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author ghost
 */
public class Main extends JFrame{
    
    public static int height = 500;
    public static int width = 700;
    Image2 image = new Image2();
   
    public static Back bg ;
    public static Thread thread1;
    public static Thread thread2;
    
    public Main(){
        this.setSize(width,height);
        this.setVisible(true);
        this.setTitle("IControl");
        image.Load();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        in();
    }
    
    public void in(){
        bg = new Back();
        this.add(bg);
        this.setVisible(true);
        new Executions();
        bg.repaint();
    }
    
    public static void main(String[] args){
        new Main();
    }
}
