import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pistol here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pistol extends Gun
{
    @Override
    public String getItemName()
    {
        return "Pistol";
    }
    @Override
    public Projectile getFiredProjectile()
    {
        return new Bullet();
    }
    public int barrelLength()
    {
        return 60;
    }
    public int barrelHeight()
    {
        return 8;
    }
    public int cooldownTime()
    {
        return 20;
    }
    public float getFirepower()
    {
        return 13;
    }
    public float getProjectileDamage()
    {
        return 5;
    }
    public int deviation()
    {
        return 6;
    }
    public int numberOfProjectiles()
    {
        return 1;
    }  
    public Vec2 getHeldPosition()
    {
        return new Vec2(75,0);
    }
    protected boolean ammoCheck()
	{
		return (Slot.containsItem(Block.getBlockClass("BulletItem"), 0)!= null)&&(Slot.containsItem(Block.getBlockClass("Gunpowder"), 0)!= null);
	}
    protected boolean consumeAmmo()
   	{
    	if(ammoCheck())
    	{
    		Slot.consumeItem(Block.getBlockClass("Gunpowder"), 0);
    		Slot.consumeItem(Block.getBlockClass("BulletItem"), 0);
    		return true;
    	}
    	return false;
   	}
}
