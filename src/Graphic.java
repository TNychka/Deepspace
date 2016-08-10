package src;
import javax.swing.JPanel;
import java.awt.*;
/**
 * Write a description of class Graphic here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Graphic extends JPanel implements Runnable
{
    Runtime r = new Runtime(); 
    /**
     * Constructor for objects of class Graphic
     */
    public Graphic()
    {
    }

    protected void paintComponent (Graphics g){
        super.paintComponent (g);
    }

    public void run(){
        new Thread(r).start();
        while (true){
            repaint();
            try{
                Thread.sleep(30);
            }
            catch (InterruptedException e){

            }
        }
    }
}
