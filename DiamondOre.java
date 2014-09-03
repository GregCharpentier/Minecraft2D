import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DiamondOre here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DiamondOre extends Block
{
    /**
     * Act - do whatever the DiamondOre wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        onFrameActions();
    }    
    public String getItemName()
    {
        return "Diamond Ore";
    }
    public DiamondOre()
    {
        id = 22;
    }
}
