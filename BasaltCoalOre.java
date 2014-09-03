import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BasaltCoalOre here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BasaltCoalOre extends Block
{
    /**
     * Act - do whatever the BasaltCoalOre wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        onFrameActions();
    }    
    public BasaltCoalOre()
    {
        id = 42;
    }
    public String getItemName()
    {
        return "Basalt Coal Ore";
    }
    @Override
    protected int itemOnDestroy()
    {
        return 17;
    }   
    public int blockTier()
    {
        return 3;
    }
}
