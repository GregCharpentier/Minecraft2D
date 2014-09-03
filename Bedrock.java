import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bedrock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bedrock extends Block
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
        return "Bedrock";
    }
    public Bedrock()
    {
        id = 64;
        isUnbreakable = true;
    }
    
}
