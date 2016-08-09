import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
/**
 * Deep Space
 * 
 * A turn based strategy in space
 * 
 * @author Wyatt Van Berlo, Tyler Nychka, Lucas Shanks
 * @version 0.1
 */
public class DeepSpace extends JFrame
{
    public static void main (String[] args){
        new DeepSpace();
    }
    public DeepSpace(){
        super("Deep Space");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close on click
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Graphic g = new Graphic();//Create graphics object (a JPanel)
        this.getContentPane().add(g);//add Graphics object to JFrame
        this.setUndecorated(true);
        this.setVisible(true);//make the Frame visible
        g.run();//start the graphics object
    }
}
