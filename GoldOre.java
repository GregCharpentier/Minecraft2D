import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GoldOre here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GoldOre extends Block
{
    /**
     * Act - do whatever the GoldOre wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
       onFrameActions();
    }  
    public String getItemName()
    {
        return "Gold Ore";
    }
    public GoldOre()
    {
        id = 21;
    }
}
