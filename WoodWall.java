import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WoodWall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WoodWall extends Wall
{
    /**
     * Act - do whatever the dirt wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        onFrameActions();
    }   
    public WoodWall()
    {
        isWall = true;
        id = 15;
    }
    @Override
    protected int itemOnDestroy()
    {
        return 7;
    }
    public String material()
    {
        return "wood";
    }
    public String getItemName()
    {
        return "Wood Wall";
    }
    
}
