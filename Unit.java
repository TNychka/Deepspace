
/**
 * Write a description of class Unit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Unit
{
    UnitFaction faction;
    UnitSpecies species;
    String firstName;
    String lastName;
    ItemWeapon equippedItem;
    Item heldItem;
    AI unitAI;
    UnitGender gender;
    int x;
    int y;
    int HpMax = 2;
    int HpCurrent = 2;
    int move = 3;
    int moveCurrent = move;
    int power = 1;
    int aim =1 ;
    int speed=1;
    int luck=1;
    public Unit(String fIN, String lIN){
        firstName=fIN;
        lastName=lIN;
    }
    public void AIAction (MapInstance m){
        
    }
}
