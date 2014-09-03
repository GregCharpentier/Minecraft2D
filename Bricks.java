import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bricks here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bricks extends Block
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
        return "Bricks";
    }
    public Bricks()
    {
        id = 3;
    }
    protected int getResistance()
    {
        return 20;
    }
    
}
