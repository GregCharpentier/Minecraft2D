import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SteelSword here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SteelSword extends SteelTool
{
    /**
     * Act - do whatever the SteelSword wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    public String toolType()
    {
        return "sword";
    }
    public int getSecondaryAttackSpeed()
    {
        return 20;
    }
    @Override
    public int getSecondaryKnockback()
    {
        return 50;
    }

    public int getSecondaryDamage()
    {
        return 10;
    }
}
