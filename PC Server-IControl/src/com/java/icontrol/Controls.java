/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.icontrol;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 *
 * @author ghost
 */
public class Controls {
    Robot robot;
    public Controls() {
        try {
            robot = new Robot();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void leftClick() {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);  
    }
    public void doubleClick() {
        leftClick();
        robot.delay(500);
        leftClick();
    }
    public void rightClick() {
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
    }
    public void mousePress(){
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    }
    public void mouseMove(int x, int y) {
        robot.mouseMove(x, y);
    }
    public void mouseWheel(int wheelAmount) {
        robot.mouseWheel(wheelAmount);
    }
    public void Keyboard_f5(){
        robot.keyPress(KeyEvent.VK_F5);
    }
    public void Keyboard_leftArrow(){
        robot.keyPress(KeyEvent.VK_LEFT);
    }
    public void Keyboard_rightArrow(){
        robot.keyPress(KeyEvent.VK_RIGHT);
    }
    public void Keyboard_esc(){
        robot.keyPress(KeyEvent.VK_ESCAPE);
    }
}
