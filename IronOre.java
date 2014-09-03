import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IronOre here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IronOre extends Block
{
    /**
     * Act - do whatever the IronOre wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        onFrameActions();
    }    
    public String getItemName()
    {
        return "Iron Ore";
    }
    public IronOre()
    {
        id = 20;
    }
    public int blockTier()
    {
        return 2;
    }
}
