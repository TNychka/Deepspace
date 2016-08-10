package src;

import javax.swing.*;
import java.awt.event.*;
/**
 * Write a description of class Controls here.
 * 
 * @author Wyatt Van Berlo 
 * @version 30/04/15
 */
public class Controls extends JPanel implements Runnable
{
    public static int MouseX = 0;
    public static int MouseY = 0;
    public static boolean MouseClick = false;
    public Controls(){
            //Defines the mouse and keyboard listeners
        MouseMotionListener mm = new MyMouseMotionListener ();
        MouseListener m = new MyMouseListener();
        this.addMouseMotionListener (mm);
        this.addMouseListener(m);
        this.setFocusable(true);
    }
    public void run(){
        
    }
    public class MyMouseMotionListener implements MouseMotionListener {
        public void mouseMoved (MouseEvent e)
        {
            MouseX = e.getX();
            MouseY = e.getY();
        }

        public void mouseDragged (MouseEvent e)
        {
            MouseX = e.getX();
            MouseY = e.getY();
        }
    }
    public class MyMouseListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            MouseClick = true;
        }           

        public void mouseReleased(MouseEvent e) {
            MouseClick = false;
        }           

        public void mouseEntered(MouseEvent e) {
        }           

        public void mouseExited(MouseEvent e) {
        }           

        public void mouseClicked(MouseEvent e) {
        }
    }
}
