import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Rifle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rifle extends Gun
{

    @Override
    public String getItemName()
    {
        return "Rifle";
    }
    @Override
    public Projectile getFiredProjectile()
    {
        return new Bullet();
    }
    public int barrelLength()
    {
        return 80;
    }
    public int barrelHeight()
    {
        return 8;
    }
    public int cooldownTime()
    {
        return 50;
    }
    public float getFirepower()
    {
        return 20;
    }
    public float getProjectileDamage()
    {
        return 8;
    }
    public int deviation()
    {
        return 2;
    }
    public int numberOfProjectiles()
    {
        return 1;
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
