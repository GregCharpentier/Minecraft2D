import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Planks here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Planks extends Block
{
    /**
     * Act - do whatever the dirt wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        onFrameActions();
        
    }    
    public Planks()
    {
        id = 5;
    }
    public String material()
    {
        return "wood";
    }
    public String getItemName()
    {
        return "Planks";
    }
    
}
