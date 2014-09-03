import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Flare here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Flare extends Projectile
{
    int time;
    public String getImageName() 
    {
        return "flame";
    }   
    public double gravity()
    {
        return -.3;
    }
    public void act()
    {
        if(MCWorld.paused)
        {
            return;
        }
        super.act();
        time++;
        if(time>15)
        {
            GreenfootImage image = getImage();
            int transp = (255+(15-time)*25);
            if(transp>0)
            image.setTransparency(transp);
        }
        FVec2 speed = new FVec2(xspe,yspe);
        if(speed.getMagnitude()>.2)
        {
        	speed = speed.multiply(.78);
        	xspe = speed.X;
        	yspe = speed.Y;
        }
        if(Math.random()<.05 && ParticleEffect.particlesOn)
        {
        	if(getWorld()!=null)
        		(new ParticleEffect(ParticleEffect.Particles.SMOKE)).fire(getX(), getY(), 0, 0);
        }
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
        return CollisionReaction.THROUGH_ENEMIES;
    }
    public int despawnDistance()
    {
        return 30;
    }
}
