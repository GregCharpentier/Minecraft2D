import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arrow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arrow extends Projectile
{
    public String getImageName() 
    {
        return "arrow";
    }   
    public double gravity()
    {
        return .6;
    }
    public boolean rotates()
    {
        return true;
    }
    public Vec2 collisionPoint()
    {
        return new Vec2(18,0);
    }
    public CollisionReaction collisionReaction()
    {
        return CollisionReaction.STICK;
    }
    public float getProjectileDamage()
    {
       
        return getSpeed()*damageFromGun;
    }
}
