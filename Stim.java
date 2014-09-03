import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stim here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stim extends Item
{
    /**
     * Act - do whatever the Stim wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public String type()
    {
        return "empty";
    }
    public double strength()
    {
        return getDamage();
    }
    public String getItemName()
    {
        return "Hypodermic Needle";
    }
    public int maxUsedDamageValues()//Returns the largest damage value that the block makes use of (used for shading)
    {
        return 1;
    }

    public void use()
    {
        if(type().equals("empty"))
        {
            return;
        }
        Slot.addItems(70,1);
        if(type().equals("health"))
        {
            Health.boost((int)Math.round(strength()*6));
            Slot.consumeCurrentItem();
            Health.setRegenMultiplier(2,300);
            return;
        }
        if(type().equals("speed"))
        {
            Block.drugSpeedBoost = (int)(4+3*strength());
            return;
        }
        
    }

}
