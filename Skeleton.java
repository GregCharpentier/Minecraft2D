import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Skeleton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Skeleton extends PhysicalMob
{
    private int cooldown = 0;
    public Skeleton()
    {
        MAXSPEED = 5;
        health = 18;
    }

    protected int[] box()
    {
        int[] L ={-10,10,-53,53};
        return L;
    }

    public void act()
    {
    	if(ModeManager.creative)
    	{
    		remove();
    		return;
    	}
        if(MCWorld.paused)
            return;
        if(checkHit())
        	return;
        Vec2 playerDist=new Vec2(ModeManager.theHead.getX()-getX(),ModeManager.theHead.getY()-getY());
        if(playerDist.getMagnitude()<400&&cooldown<0)
        {
            xspe = 0;
            Projectile arrow = new Arrow();
            arrow.setHostile();
            arrow.damageFromGun = .15f;
            arrow.fire(getX(),getY(),(int)Math.toDegrees(playerDist.getAngle()),20);
            cooldown = 70;
            
        }
        
        if(!(playerDist.getMagnitude()<300))
        {
            if(getX()<ModeManager.center)
            {
                moveRight();
            }
            else
            {
                moveLeft();
            }
        }
        if(getX()<ModeManager.center)
        {
            setImage("images\\skeletonright.png");
        }
        else
        {
            setImage("images\\skeletonleft.png");
        }
        cooldown--;
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

    public Slot drop()
    {
        return new Slot().setItem(Block.getBlockClass("ArrowItem"), 1);
    }    
    public void dropDrops()
    {
    	int rand = Greenfoot.getRandomNumber(3)+1;
        for(int i = 0;i<rand;i++)
            Slot.addItem(drop().blockType,drop().blockDamage);
        
    
    }
}
