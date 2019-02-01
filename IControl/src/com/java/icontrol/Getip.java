/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tmpIPlate file, choose Tools | Templates
 * and open the tmpIPlate in the editor.
 */
package com.java.icontrol;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 *
 * @author ghost
 */
public class Getip {
    
    public String ipadd[] = new String[10],tmpIP;
    int j=0;
    
    public String[] ip(){
        Enumeration e = null;
        String s = "";
        try {
            e = NetworkInterface.getNetworkInterfaces();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        while (e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements()) {
                InetAddress i = (InetAddress) ee.nextElement();
                tmpIP = i.getHostAddress();
                if((tmpIP.charAt(1) == '7' || tmpIP.charAt(1) == '9') && (tmpIP.charAt(2) == '2')) {
                    ipadd[j] = tmpIP;
                    j++;
                    //System.out.println(tmpIP);
                }
                else if(tmpIP.charAt(0) == '1' && tmpIP.charAt(1) == '0') {
                    ipadd[j] = tmpIP;
                    j++;
                   // System.out.println(tmpIP);
                }
            }
        }
        if (ipadd[0] == null) {
            ipadd[0] = "127.0.0.1";
        }
        return ipadd;
    }
    
    
}
