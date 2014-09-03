   import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Glass here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Glass extends Block
{
    /**
     * Act - do whatever the Glass wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Glass()
    {
        id = 23;
    }
    public String getItemName()
    {
        return "Glass";
    }
    public void act() 
    {
        onFrameActions();
    }    
}
