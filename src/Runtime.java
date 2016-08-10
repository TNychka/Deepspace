package src;

import java.util.ArrayList;
/**
 * Write a description of class Runtime here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Runtime implements Runnable
{
    ArrayList<Unit> crewList = new ArrayList<Unit>(0);
    public void run(){
        crewList.add(new Unit("Jean-Luc","Picard"));
        crewList.add(new Unit("William", "Riker"));
        crewList.add(new Unit("Wesley","Crusher"));
    }
}
