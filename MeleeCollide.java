import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SwordCollide here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MeleeCollide extends CollisionBox
{
    public static boolean lastDirection = false;
    public int damage=0;
    public int knockback;
    public int size =19;
    public final int LEFT = ModeManager.thePlayer.getX()-10,RIGHT = ModeManager.thePlayer.getX()+10;
    public int left = LEFT-10,right = RIGHT+10;
    public static final GreenfootImage base = new GreenfootImage("images\\playercollision.png");
    public void act() 
    {
        if(ModeManager.creative)
        {
            return;
        }
        if(MCWorld.paused)
        {
            return;
        }
        adjustDirection();
        if(timer >0)
        {
            timer--;
            if(timer == 0)
            {
                active = false;
            }
        }           
    }

    public void adjustDirection()
    {

        if(checkMouse())
        {
            if(lastDirection == false)
            {
                lastDirection = true;
                setLocation(right,getY());
            }
        }
        else
        {
            if(lastDirection == true)
            {
                lastDirection = false;
                setLocation(left,getY());
            }
        }
    }

    public static boolean checkMouse()
    {
        if(MCWorld.mx > ModeManager.center)
        {

            return true;
        }

        return false;
    }

    public void setSize(int size)
    {
        if(ModeManager.creative)
        {
            return;
        } 
        GreenfootImage image = new GreenfootImage(base);
        image.scale(size,image.getHeight());
        setImage(image);
        left = LEFT-image.getWidth()/2;
        right = RIGHT+image.getWidth()/2;
        if(lastDirection == true)
        {
            setLocation(right,getY());
        }
        if(lastDirection == false)
        {
            setLocation(left,getY());
        }
    }

    public void setActiveTimer(int time,int parDamage,int parKnockback,int parSize)
    {
        timer = time;
        active = true;
        damage = parDamage;
        knockback = parKnockback;
        System.out.println(parSize);
        if(parSize != size)
        {
            size = parSize;
            setSize(parSize);
            System.out.println(" ]"+size);
        }
    }
    public MeleeCollide()
    {
        super();
    }

}
