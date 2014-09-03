import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StoneBricks here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MossyStoneBricks extends Block
{
    /**
     * Act - do whatever the dirt wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        onFrameActions();
    }  
    public MossyStoneBricks()
    {
        id = 24;
    }
    protected int getResistance()
    {
        return 15;
    }
    public String getItemName()
    {
        return "Mossy Stone Bricks";
    }
    
}
