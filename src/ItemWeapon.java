package src;

/**
 * Write a description of class ItemWeapon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ItemWeapon extends Item
{
    int damage;
    int aim;
    int range;
    WeaponType wtype;
    
    public ItemWeapon (int damage, int aim, int range, WeaponType wtype){
        super();
        this.damage = damage;
        this.aim = aim;
        this.range = range;
        this.wtype = wtype;
        type = ItemType.WEAPON;
    }
}
