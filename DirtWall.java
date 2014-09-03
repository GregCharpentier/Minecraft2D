import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DirtWall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirtWall extends Wall
{
    /**
     * Act - do whatever the dirt wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        onFrameActions();
    }    
    public String getItemName()
    {
        return "Dirt Wall";
    }
    public DirtWall()
    {
        isWall = true;
        id = 13;
    }
    public String material()
    {
        return "dirt";
    }
    
}
