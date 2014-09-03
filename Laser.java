import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Laser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Laser extends Projectile
{
   public String getImageName() 
    {
        return "laser";
    }   
    public double gravity()
    {
        return 0;
    }
    public boolean rotates()
    {
        return true;
    }
    public Vec2 collisionPoint()
    {
        return new Vec2(0,0);
    }
    public CollisionReaction collisionReaction()
    {
        return CollisionReaction.NO_COLLIDE;
    } 
    public int despawnDistance()
    {
        return 20;
    }
}
