import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Leaves here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Leaves extends Block
{
    public String material()
    {
        return "grass";
    }
    public void act() 
    {
       
        onFrameActions();
    }  
    public String getItemName()
    {
        return "Leaves";
    }
    public Leaves()
    {
        id = 41;
    }
    @Override
    protected int getResistance()
    {
        return 4;
    }
}
