import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Nurse here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Nurse extends PhysicalMob
{
    /**
     * Act - do whatever the Nurse wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean hasHealed = false;
    private int timeout = 1800;

    public Nurse() 
    {
        MAXSPEED = 3;
        health = 10;
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
        timeout--;
        if(timeout<0)
        {
            MCWorld.theWorld.removeObject(this);
            return;
        }
        
        if(!hasHealed)
        {
            if(getX()<ModeManager.center)
            {
                moveRight();
                setImage("images\\nurseright.png");
            }
            else
            {
                moveLeft();
                setImage("images\\nurseleft.png");
            }
        }
        else
        {
           if(getX()<ModeManager.center)
            {
                moveLeft();
                setImage("images\\nurseleft.png");
            }
            else
            {
                moveRight();
                setImage("images\\nurseright.png");
            } 
        }
        if(checkHit())
        {
            return;
        }
        if(checkForPlayer())
        {
            Health.heal(10);
            hasHealed = true;
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
}
