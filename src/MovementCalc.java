package src;

import java.util.ArrayList;
/**
 * Write a description of class MovementCalc here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovementCalc
{
    MapInstance m;
    public MovementCalc (MapInstance m)
    {
        this.m = m;
    }

    /**
     * Calculates the distance from a unit to a tile, and returns the number of movement points to go there
     *
     * @param u The unit
     * @param xTo The xcoordinate to go to
     * @param yTo The ycoordinate to go to
     * @return The number of movement require to use to go to a point, or -1 if that unit cannot go there with their current movement
     */
    public int DistanceTo (Unit u, int xTo, int yTo){
        ArrayList<Point> points= new ArrayList<Point>(0);//An array of points
        points.add(new Point (u.x,u.y));
        for (int i = 0; i <u.moveCurrent; i++){
            for (int ii = 0; ii <points.size(); ii++){
                if (m.mapTiles[points.get(ii).x + 1][points.get(ii).y] == TileType.EMPTY){
                    //Right one
                    if (points.get(ii).x + 1 == xTo && points.get(ii).y == yTo)
                        return i;
                    if (!IsPointInList (points, points.get(ii).x + 1,points.get(ii).y))
                        points.add (new Point(points.get(ii).x + 1,points.get(ii).y));
                        //Left one
                    if (points.get(ii).x + 1 == xTo && points.get(ii).y == yTo)
                        return i;
                    if (!IsPointInList (points, points.get(ii).x - 1,points.get(ii).y))
                        points.add (new Point(points.get(ii).x - 1,points.get(ii).y));
                        //Down one
                    if (points.get(ii).x == xTo && points.get(ii).y  + 1== yTo)
                        return i;
                    if (!IsPointInList (points, points.get(ii).x,points.get(ii).y+1))
                        points.add (new Point(points.get(ii).x,points.get(ii).y+1));
                        //Up one
                    if (points.get(ii).x  == xTo && points.get(ii).y - 1 == yTo)
                        return i;
                    if (!IsPointInList (points, points.get(ii).x ,points.get(ii).y - 1))
                        points.add (new Point(points.get(ii).x,points.get(ii).y - 1));
                }
            }
        }
        return -1;
    }

    private boolean IsPointInList (ArrayList<Point> listToCheck, int x, int y){
        for (Point p: listToCheck){
            if (p.x == x && p.y == y)
                return true;
        }
        return false;
    }
    private class Point {
        int x;
        int y;
        public Point (int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
