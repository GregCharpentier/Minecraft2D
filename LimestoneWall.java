import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LimestoneWall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LimestoneWall extends Wall
{
    /**
     * Act - do whatever the LimestoneWall wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        onFrameActions();
    }   
    public LimestoneWall()
    {
        isWall = true;
        id = 54;
    }   
    public String getItemName()
    {
        return "Limestone Wall";
    }
}
