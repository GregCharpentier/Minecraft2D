import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BasaltSilverOre here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BasaltSilverOre extends Block
{
    /**
     * Act - do whatever the BasaltSilverOre wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        onFrameActions();
    }    
    public String getItemName()
    {
        return "Basalt Silver Ore";
    }
    public BasaltSilverOre()
    {
        id = 43;
    }
    public int blockTier()
    {
        return 3;
    }
}
