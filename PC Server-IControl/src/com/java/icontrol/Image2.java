/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.icontrol;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author ghost
 */
public class Image2 {
    public static BufferedImage back = null;
    
    public void Load(){
        try{
            back = ImageIO.read(getClass().getResource("back.png"));
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
