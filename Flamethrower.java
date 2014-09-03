import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Flamethrower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Flamethrower extends Gun
{
    @Override
    public String getItemName()
    {
        return "Flamethrower";
    }
    @Override
    public Projectile getFiredProjectile()
    {
        return new Flare();
    }
    public int barrelLength()
    {
        return 52;
    }
    public float getProjectileDamage()
    {
        return .0065f;
    }
    public int barrelHeight()
    {
        return 4;
    }
    public int cooldownTime()
    {
        return 0;
    }
    public float getFirepower()
    {
        return 36;
    }
    public int deviation()
    {
        return 30;
    }
    public int numberOfProjectiles()
    {
        return 10;
    }
    public Vec2 getHeldPosition()
    {
        return new Vec2(-20,0);
    }
    @Override
    public Slot ammoItem()
    {
    	return new Slot().setItem(Block.getBlockClass("Propane"), 1);
    }
    @Override
    protected boolean consumeAmmo()
   	{
    	if(Math.random()<.5)
    	return Slot.consumeItem(ammoItem().blockType, ammoItem().blockDamage);
    	else
    		return true;
   	}
}
