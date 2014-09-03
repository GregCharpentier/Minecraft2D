import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bow extends Gun
{
    int displayState = 0;
    @Override
    public String getItemName()
    {
        return "Bow";
    }
    @Override
    public Projectile getFiredProjectile()
    {
        return new Arrow();
    }
    public String getBlockImageName()
    {
        if(getChargePercent()<.02)
        {
            return super.getBlockImageName();
        }
        else if(getChargePercent()<.50)
        {
            return "spec\\Bow1";
        }
        else if(getChargePercent()<.95)
        {
            return "spec\\Bow2";
        }
        else 
        {
            return "spec\\Bow3";
        }
    }
    public int barrelLength()
    {
        return 20;
    }
    public int barrelHeight()
    {
        return 0;
    }
    public int cooldownTime()
    {
        return 10;
    }
    public int minCharge()
    {
        return 10;
    }
    public int maxCharge()
    {
        return 60;
    }
    public float getFirepower()
    {
        return 26*(float)getChargePercent();
    }
    public int deviation()
    {
        return 4;
    }
    public int numberOfProjectiles()
    {
        return 1;
    }    
    public void onHeld()
    {
        super.onHeld();
        int currentDisplayState = 0;
        if(getChargePercent()<.02)
        {
            currentDisplayState = 0;
        }
        else if(getChargePercent()<.5)
        {
            currentDisplayState = 1;
        }
        else if(getChargePercent()<.95)
        {
            currentDisplayState = 2;
        }
        else 
        {
            currentDisplayState = 3;
        }
        if(currentDisplayState!=displayState)
        {
            displayState = currentDisplayState;
            Selector.paintItem();
        }
        displayState = currentDisplayState;
        
    }
    @Override
    public int getHeldAngle()
    {
        return 270;
    }
    @Override
    public float getProjectileDamage()
    {
        return .8f;
    }
    @Override
    public Slot ammoItem()
    {
    	return  new Slot().setItem(Block.getBlockClass("ArrowItem"), 1,0);
    }
}
