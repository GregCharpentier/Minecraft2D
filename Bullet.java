import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Projectile
{
    public Bullet()
    {
        super();
    }
    public String getImageName() 
    {
        return "bullet";
    }   
    public double gravity()
    {
        return 0;
    }
    public boolean rotates()
    {
        return false;
    }
    public Vec2 collisionPoint()
    {
        return new Vec2(0,0);
    }
    public CollisionReaction collisionReaction()
    {
        return CollisionReaction.DESPAWN;
    }
   
}
