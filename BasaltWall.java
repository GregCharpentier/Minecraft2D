import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BasaltWall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BasaltWall extends Wall
{   
    public void act() 
    {
        
        onFrameActions();
    }    
    public BasaltWall()
    {
        isWall = true;
        id = 39;
    }
    public String getItemName()
    {
        return "Basalt Wall";
    }
}
