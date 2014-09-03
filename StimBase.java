import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StimBase here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StimBase extends Item
{
    public int getStrength()
    {
        return 0;
    }
    public static int getStrengthOfID(int id)
    {
        StimBase base = (StimBase)Block.getBlockOf(id);
        return base.getStrength();
    }
}
