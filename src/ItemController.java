package src;

import java.util.ArrayList;
/**
 * Write a description of class ItemController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ItemController
{
    ArrayList<Item> allItems = new ArrayList<Item>(0);
    public ItemController(){
        allItems.add(new ItemWeapon(2,3,3,WeaponType.PISTOL));
        allItems.get(0).name = "Pistol";
        allItems.get(0).type = ItemType.WEAPON;
    }
}
