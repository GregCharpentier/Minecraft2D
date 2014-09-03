import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Zombie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Creeper extends PhysicalMob
{
    
    
	public Creeper()
    {
        MAXSPEED = 5;
        health = 20;
    }

    protected int[] box()
    {
        int[] L ={-10,10,-53,53};
        return L;
    }

    public void act()
    {
    	
        if(MCWorld.paused)
            return;
        if(ModeManager.creative)
    	{
    		remove();
    		return;
    	}
        if(getX()<ModeManager.center)
        {
            moveRight();
            setImage("images\\creeperright.png");
        }
        else
        {
            moveLeft();
            setImage("images\\creeperright.png");
        }
        if(checkHit())
        {
            return;
        }
        if(checkForPlayer())
        {
        	ExplosionProjectile.startExplosion(getX(), getY(),10,false);
        	this.remove();
        	return;
        }
        super.act();
        
    }

    protected void onBlocked(boolean grounded)
    {
        if(grounded)
        {
            jump();
        }

    }

    protected void jump()
    {
        yspe-=25;
    }
    public void dropDrops()
    {
    	int rand = Greenfoot.getRandomNumber(4)+3;
        for(int i = 0;i<rand;i++)
            Slot.addItem(drop().blockType,drop().blockDamage);
        
    
    }
    public Slot drop()
    {
        return new Slot().setItem(Block.getBlockClass("Gunpowder"),1,0);
    }
}
