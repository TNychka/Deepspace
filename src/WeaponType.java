package src;

/**
 * Enumeration class WeaponType - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum WeaponType
{
    SHOTGUN (4,1,1),
    PISTOL (2,2,2),
    ASSAULT (4,4,2),
    BOW (2,3,3),
    RIFLE (4,2,3),
    SNIPER(4,6,6),
    SWORD(4,8,-1);
    
    private int dam;
    private int aim;
    private int range;
    WeaponType (int dam, int aim, int range){
        this.dam=dam;
        this.aim=aim;
        this.range=range;
    }
}
