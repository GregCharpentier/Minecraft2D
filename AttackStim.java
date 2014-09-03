import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AttackStim here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AttackStim extends Stim
{
    /**
     * Act - do whatever the AttackStim wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public String type()
    {
        return "attack";
    } 
    public String getItemName()
    {
        return "Attack Stim";
    }
}
