import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Limestone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Limestone extends Block
{
    /**
     * Act - do whatever the Limestone wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        onFrameActions();
    }    
    public String getItemName()
    {
        return "Limestone";
    }
    public Limestone()
    {
        id = 53;
    }
    protected int getResistance()
    {
        return 15;
    }  
    public int blockTier()
    {
        return 4;
    }
}
