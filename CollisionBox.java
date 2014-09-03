import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CollisionBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CollisionBox extends Player
{
    public boolean active = true;
    public String type;
    public boolean dir;
    protected int timer;
    /**
     * Act - do whatever the CollisionBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(MCWorld.paused)
        {
            return;
        }
        if(timer >0)
        {
            timer--;
            if(timer == 0)
            {
                active = true;
            }
        }           
    }
    public CollisionBox()
    {
        if(MCWorld.DEVMODE)
        {
            setImage("images\\playercollisionDEBUG.png");
        }
    }
    public void setNonCollide(int time)//Zero for indefinite invulnerability
    {
        if(time<0)
        {
            time = 1;
        }
        active = false;
        timer = time;
    }
}
