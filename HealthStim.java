import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealthStim here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthStim extends Stim
{
    /**
     * Act - do whatever the HealthStim wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public String type()
    {
        return "health";
    }  
    public String getItemName()
    {
        return "Health Stim";
    }
}
