import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Zombie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Zombie extends PhysicalMob
{
    
    
	public Zombie()
    {
        MAXSPEED = 5;
        health = 30;
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
            setImage("images\\zombieright.png");
        }
        else
        {
            moveLeft();
            setImage("images\\zombieleft.png");
        }
        if(checkHit())
        {
            AchievementManager.getAchievement("Zombie Slayer").achieve();
            return;
        }
        if(checkForPlayer())
        {
            float newspeed = -xspe*4;
            if(newspeed>0)
            {
              Block.playerSpeed=Math.min(newspeed,20); 
            }
            else
            {
                Block.playerSpeed=Math.max(newspeed,-20);
            }
            
            
            Block.verticalForce=9;
            Health.damage(5);
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
    
    public Slot drop()
    {
        return new Slot().setItem(Block.getBlockClass("Sugar"),1,0);
    }
}
