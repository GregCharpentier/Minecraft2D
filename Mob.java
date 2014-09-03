import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mob here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mob extends Entity
{
    public float health;
    protected int hitTimer;
    public void act() 
    {
        if(hitTimer>0)
        {
            hitTimer--;
        }
        super.act();
    }    
    protected void knockback()
    {

    }
    protected Slot drop()
    {
        return null;
    }
    public boolean damage(float damage)
    {
        if(hitTimer>0)
        {
            return false;
        }
        health-=damage;
        knockback();
        hitTimer = getHitImmunityLength();
        if(health<=0)
        {
            death();
            return true;
        }
        return false;
    }
    
    public void death()
    {
        dropDrops();
        remove();
    }
    public void dropDrops()
    {
    	if(drop() == null)
    		return;
    	int rand = Greenfoot.getRandomNumber(3);
        if(rand == 0)
        {
        	Slot.addItem(drop().blockType,drop().blockDamage);
        }
    
    }
}
