import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Grass here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CoalOre extends Block
{
    /**
     * Act - do whatever the dirt wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        onFrameActions();
    }    
    public CoalOre()
    {
        id = 19;
    }
    public String getItemName()
    {
        return "Coal Ore";
    }
    @Override
    protected int itemOnDestroy()
    {
        return 17;
    }
    public int blockTier()
    {
        return 1;
    }
    
}
