import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Basalt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Basalt extends Block
{
    /**
     * Act - do whatever the Basalt wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        onFrameActions();
    }    
    public String getItemName()
    {
        return "Basalt";
    }
    public Basalt()
    {
        id = 38;
    }
    protected int itemOnDestroy()
    {
        return 40;
    } 
    protected int getResistance()
        {
      return 6;
     }
    public int blockTier()
    {
        return 3;
    }
    
    
}
