/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.icontrol;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author ghost
 */
public class ScreenCap {
     public void sendScreenshot(final ObjectOutputStream out) {
        InputStream is = null;
        try {
            BufferedImage screenshot
                    = new Robot().createScreenCapture(new Rectangle(
                            Toolkit.getDefaultToolkit()
                                            .getScreenSize()));
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(screenshot, "png", os);
            is = new ByteArrayInputStream(os.toByteArray());
            int fileSize = os.size();

            out.writeObject(fileSize);
            out.flush();
            byte[] buffer = new byte[4096];
            int read = 0;
            long totalRead = 0;
            int remaining = (int) fileSize;
            while ((read = is.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
                totalRead += read;
                remaining -= read;
                out.write(buffer, 0, read);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
