import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cobblestone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MossyCobblestone extends Block
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
        return "Mossy Cobblestone";
    }
    public MossyCobblestone()
    {
        id = 25;
    }
    protected int getResistance()
    {
        return 12;
    }
    
}
