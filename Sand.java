import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Sand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sand extends Block
{
   
    public String material()
    {
        return "dirt";
    }
    public String getItemName()
    {
        return "Sand";
    }
    public void act() 
    {
        
        onFrameActions();
        
    }    
    public Sand()
    {
        id = 9;
    }
    protected int getResistance()
    {
        return 8;
    }
    
    
}
