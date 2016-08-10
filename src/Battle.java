package src;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import static src.TileType.WALL;

/**
 * An instance of a battle. Created by the runtime when a battle is needed
 * 
 * @author Wyatt Van Berlo
 * @version 0.2
 */
public class Battle
{

    WinCondition w;//The win condtion
    MapInstance m;//the map on which the battle occurs
    MovementCalc moveCalc;//Used to caclulate whether a unit can go to a space
    //Info about where the map is on the screen, and what it's showing
    int currentXPos = 0;
    int currentYPos = 0;
    int currentScale = 0;
    int currentLBound = 0;//Left bound ie which column is being displayed on the far right
    int currentUBound = 0;//upper bound
    int currentRBound = 0;//right bound
    int currentDBound = 0;//lower bound

    //Is a unit selected? where is it?
    boolean UnitSelected = false;
    int SelectX = 0;
    int SelectY = 0;

    WaitFor waiting = null;

    BufferedImage characterInfo = null;
    
    
    /**
     * Constructor for objects of class Battle
     */
    public Battle(WinCondition win, MapInstance map)
    {
        w = win;
        m = map;
        moveCalc = new MovementCalc (m);
        try{//The selection image for the tile
            characterInfo = ImageIO.read(new File("resources/character_info.png"));
        }
        catch (IOException ex){
            ;
        }
    }

    /**
     * Runs a complete instance of a battle. Returns when the battle's lost and won
     *
     * @return Returns whether the mission was succesful or not
     */
    public boolean runBattle (){
        boolean isTurn = true;//used to show that it is the players turn
        while (m.crewRemaining() >0 && !w.checkLossCondition()){//While the battle hasn't been lost yet
            if (w.checkWinCondition())
                return true;
            isTurn = true;
            while (isTurn){
                /** Mouse Responses */
                if (Controls.MouseClick){
                    /** If the mouse is within the bounds of the map */
                    if (Controls.MouseX >= currentXPos && Controls.MouseY >=currentYPos && Controls.MouseX <currentXPos + currentScale*(currentRBound - currentLBound)&& Controls.MouseY <currentYPos + currentScale*(currentDBound - currentUBound)){
                        //Set temp values for the currently clicked space
                        int HighlightX = (Controls.MouseX - currentXPos)/currentScale + currentLBound;
                        int HighlightY = (Controls.MouseY - currentYPos)/currentScale + currentUBound;
                        //If there is a unit at this spot, and is there nothing selected
                        if (m.isUnitAt(HighlightX, HighlightY) && !UnitSelected){
                            SelectX = HighlightX;
                            SelectY = HighlightY;
                            UnitSelected = true;
                        }
                        //If there is a unit at this spot, and a unit is already selected
                        else if (m.isUnitAt(HighlightX, HighlightY)){
                            //If a player unit is selected, and another is clicked
                            if (m.unitAt(HighlightX,HighlightY).faction == UnitFaction.PLAYER && m.unitAt(SelectX,SelectY).faction == UnitFaction.PLAYER){
                                //Are we waiting for a friendly to be clicked?
                                if (waiting ==WaitFor.FRIEND_CLICK){
                                    /** This is for when some item or skill needs a player unit to be clicked */
                                }
                                else {//select the new unit
                                    SelectX = HighlightX;
                                    SelectY = HighlightY;
                                }
                            }
                            //If a enemy unit is selected, and another is clicked
                            if (m.unitAt(HighlightX,HighlightY).faction == UnitFaction.ENEMY && m.unitAt(SelectX,SelectY).faction == UnitFaction.ENEMY){
                                SelectX = HighlightX;
                                SelectY = HighlightY;
                            }
                            //If a friendly is selected and an enemy is clicked
                            if (m.unitAt(HighlightX,HighlightY).faction == UnitFaction.ENEMY && m.unitAt(SelectX, SelectY).faction == UnitFaction.PLAYER){
                                /** Attacking stuff */
                            }
                        }
                        //If there is noting at the destination
                        else if (UnitSelected){
                            if (m.mapTiles[HighlightX][HighlightY] == TileType.EMPTY && moveCalc.DistanceTo(m.unitAt(SelectX, SelectY),HighlightX, HighlightY)>=0){
                                UnitSelected = false;
                                m.unitAt(SelectX, SelectY).moveCurrent -= moveCalc.DistanceTo(m.unitAt(SelectX, SelectY),HighlightX, HighlightY);
                                m.unitAt(SelectX, SelectY).x = HighlightX;
                                SelectX = HighlightX;
                                m.unitAt(SelectX, SelectY).y = HighlightY;
                                SelectY = HighlightY;
                                UnitSelected = true;
                            }
                        }
                    }
                }
            }
            for (Unit u:m.enemyUnitList){
                u.AIAction(m);
            }
        }
        return false;
    }

    /**
     * Draws the map to the graphics object
     *
     * @param g A parameter
     * @param xPos A parameter
     * @param yPos A parameter
     * @param scale A parameter
     * @param leftBound A parameter
     * @param upperBound A parameter
     * @param rightBound A parameter
     * @param lowerBound A parameter
     */
    public void drawMap(Graphics g, int xPos, int yPos, int scale, int leftBound, int upperBound, int rightBound, int lowerBound){
        for (int x = leftBound; x<rightBound;x++){
            for (int y = upperBound; y<lowerBound; y++){
                switch (m.maptiles[x][y]){
                    case WALL:
                    break;
                    case EMPTY:
                    g.setColor(Color.black);
                    g.drawRect(x*scale + xPos, y*scale + yPos, x*scale + xPos + scale, y*scale + yPos +scale);
                    break;
                    case SHORT_WALL:
                    g.setColor(Color.gray);
                    g.fillRect(x*scale + xPos, y*scale + yPos, x*scale + xPos + scale, y*scale + yPos +scale);
                    break;
                }
            }
        }
        currentXPos = xPos;
        currentYPos = yPos;
        currentScale = scale;
        currentLBound = leftBound;
        currentUBound = upperBound;
        currentRBound = rightBound;
        currentDBound = lowerBound;
    }

    
    /**
     * Draws info about the selected unit
     *
     * @param g Graphics object to draw to
     * @param xPos x position
     * @param yPos y position
     * @param scale scale
     */
    public void drawSelectedUnit (Graphics g, int xPos, int yPos, int scale){
        if (UnitSelected){
            g.drawImage(characterInfo,xPos,yPos,null);
            g.setColor(Color.black);
            g.setFont(new Font("Serif",Font.PLAIN,18));
            g.drawString(m.unitAt(SelectX,SelectY).name, xPos+36, yPos+26);

            //Stats
            g.drawString (""+m.unitAt(SelectX,SelectY).HpCurrent,xPos+95, yPos+91);
            g.drawString ("/",xPos+128, yPos+91);
            g.drawString (""+m.unitAt(SelectX,SelectY).HpMax,xPos+168, yPos+91);
            
            g.drawString (""+m.unitAt(SelectX,SelectY).power,xPos+95, yPos+145);
            g.drawString (""+m.unitAt(SelectX,SelectY).aim,xPos+95, yPos+196);
            g.drawString (""+m.unitAt(SelectX,SelectY).luck,xPos+95, yPos+252);
            
            g.drawString (""+m.unitAt(SelectX,SelectY).moveCurrent,xPos+95, yPos+314);
            g.drawString ("/",xPos+128, yPos+314);
            g.drawString (""+m.unitAt(SelectX,SelectY).move,xPos+168, yPos+314);
            
            g.drawString (""+m.unitAt(SelectX,SelectY).equippedItem.name,xPos+110, yPos+367);
        }
    }
    
    
    private enum WaitFor{
        FRIEND_CLICK, ENEMY_CLICK
    }
}
