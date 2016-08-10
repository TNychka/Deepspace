package src;

import java.lang.reflect.Array;
import java.util.ArrayList;
/**
 * Write a description of class Map here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MapInstance extends MapBase
{
    ArrayList<Unit> unitList;
    ArrayList<Unit> enemyUnitList;
    TileType[][] maptiles;
    public MapInstance(){
        super();
    }
    /**
     * Returns the number of living enemies
     *
     * @return The number of living enemies
     */
    public int enemiesRemaining (){
        int temp = 0;
        for (Unit element: unitList){
            if (element.faction == UnitFaction.ENEMY)
                temp++;
        }
        return temp;
    }
    /**
     * Returns the number of living crewmen
     *
     * @return The number of living crewmen
     */
    public int crewRemaining (){
        int temp = 0;
        for (Unit element: unitList){
            if (element.faction == UnitFaction.PLAYER)
                temp++;
        }
        return temp;
    }
    
    /**
     * Checks to see if there is a unit at agiven location
     *
     * @param x The x value
     * @param y Take a guess
     * @return True if there is a unit at that location
     */
    public boolean isUnitAt (int x, int y){
        for (Unit a: unitList){
            if (a.x == x && a.y ==y)
                return true;
        }
        return false;
    }
    public Unit unitAt (int x, int y){
        for (Unit a: unitList){
            if (a.x == x && a.y ==y)
                return a;
        }
        return null;
    }
}
