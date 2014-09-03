import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MChest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MChest extends Module
{
    public Chest chest;
    public MChest(Chest chest)
    {
        this.chest=chest;
    }
    public void onRemove()
    {
        chest.save();
    }
}
