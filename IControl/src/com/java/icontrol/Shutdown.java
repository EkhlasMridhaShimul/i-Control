/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.icontrol;

/**
 *
 * @author ghost
 */
public class Shutdown {
    Runtime runtime;
    
    public Shutdown(){
        runtime = Runtime.getRuntime();
    }
    
    public void shutdown(){
        try{
            runtime.exec("shutdown -s -f -t 0");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void restart(){
        try{
            runtime.exec("shutdown -r -f -t 0");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
