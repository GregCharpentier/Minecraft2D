import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Projectile extends Entity
{
    public enum CollisionReaction {NO_COLLIDE,REFLECT,STICK,DESPAWN,SPECIAL,THROUGH_ENEMIES}
    public enum Direction {HORIZONTAL,VERTICAL}
    /**
     * For actors that use stuck collision reaction
     */
    public Actor stuckIn;
    public Vec2 stuckInPoint;
    public int despawnCheckTimer = 20;
    public float damageFromGun = 0;
    protected boolean friendly = true;
    protected int lifeTime = -1;
    public Projectile()
    {
        setImage("images\\projectiles\\"+getImageName()+".png");
        lifeTime = lifeTime();
    }

    protected int lifeTime()
	{
		return -1;
	}

	public int despawnDistance()
    {
        return 3000;
    }

    public void setHostile()
    {
        friendly = false;
    }

    public void act() 
    {
        if(MCWorld.paused)
        {
            return;
        }
        
        if(lifeTime!=-1)
        {
        	if(lifeTime<=0)
        	{
        		this.remove();
        		return;
        	}
        	else
        	{
        		lifeTime--;
        	}
        }
        if(stuckIn!=null)
        {
            if(stuckIn.getWorld()==null)
            {
                remove();
            }
            else
            {
                setLocation(stuckInPoint.X+stuckIn.getX(),stuckInPoint.Y+stuckIn.getY());
            }
            return;
        }
        
        yspe+=gravity();
        
        if(collisionReaction()!=CollisionReaction.NO_COLLIDE)
        {
            Actor a = getHorizontalCollide();
            if(a == null)
                a = getVerticalCollide();
            if(a!= null)
            {
                if(a.equals(Player.rightarm))
                {
                    Health.damage((int)Math.round(getProjectileDamage()));
                    if(!(this instanceof ExplosionProjectile))
                    {
                    	remove();
                        return;
                    }
                    
                }
                if(a instanceof Mob)
                {
                    hitMob((Mob)a);
                }
                if(collisionReaction()==CollisionReaction.STICK&&a.getWorld()!=null)
                {

                    stuckIn = a;
                    double xspeed = (new Vec2((int)(xspe*40),(int)(yspe*40))).normalizeX();
                    double yspeed = (new Vec2((int)(xspe*40),(int)(yspe*40))).normalizeY();
                    double offX =0;
                    double offY =0;
                    if(xspeed == 0.0 ||yspeed == 0)
                    {
                        stuckInPoint = new Vec2(getX()-stuckIn.getX(),getY()-stuckIn.getY());
                        return;
                    }
                    int i = 0;
                    while(true)
                    {
                        offX-=xspeed;
                        offY-=yspeed;
                        Vec2 point = getRotationAdjustedCollisionPoint();
                        ArrayList<Actor> collided = (ArrayList<Actor>)getObjectsAtOffset(point.X-(int)offX,point.Y-(int)offY,null);
                        if(collided.contains(a))
                        {
                            break;
                        }
                        i++;
                        if(i > 30)
                        {
                            offX = 0;
                            offY = 0;
                            break;
                        }
                    }
                    stuckInPoint = new Vec2(getX()-(int)offX-stuckIn.getX(),getY()-(int)offY-stuckIn.getY());
                    return;
                }
                if(collisionReaction() == CollisionReaction.DESPAWN)
                {
                    this.remove();
                    return;
                }
                if(collisionReaction() == CollisionReaction.SPECIAL)
                {
                	if(!specialCollide(a))
                	{
                		return;
                	}
                	
                }
            }

        }
        refreshRotation();
        despawnCheckTimer--;
        if(despawnCheckTimer<=0)
        {
            despawnCheckTimer = 5;
            Vec2 distance = new Vec2(MCWorld.mx-getX(),MCWorld.my-getY());
            if(distance.getMagnitude()>despawnDistance())
            {
                remove();
                return;
            }
        }
        x+=xspe;
        y+=yspe;
        super.act();
    }    

    /**
     * Returns the position of the collision detection point relative to center of image
     */
    public abstract Vec2 collisionPoint();

    public abstract String getImageName();

    /**
     * Adjusts the projectile's collisionPoint() for rotation.
     */
    public Vec2 getRotationAdjustedCollisionPoint()
    {
        Vec2 point = collisionPoint();
        double ang = Math.atan2(point.Y,point.X);
        double mag = Math.sqrt(Math.pow(point.X,2)+Math.pow(point.Y,2));
        double newy = Math.sin(ang+Math.toRadians(getRotation()))*mag;
        double newx = Math.cos(ang+Math.toRadians(getRotation()))*mag;
        return new Vec2((int)newx,(int)newy);
    }

    /**
     * Whether or not this projectile is affected by gravity.
     */
    public abstract double gravity();

    /**
     * Returns behavior of projectile upon colliding with a block
     */
    public abstract CollisionReaction collisionReaction();

    /**
     * Returns mob or block that the projectile will collide with horizontally.
     */
    public Actor getHorizontalCollide()
    {
        setLocation(getX()+(int)xspe,getY());
        Vec2 point = getRotationAdjustedCollisionPoint();
        ArrayList<Actor> collidedX = (ArrayList<Actor>)getObjectsAtOffset(point.X,point.Y,null);
        setLocation(getX()-(int)xspe,getY());
        collidedX.remove(this);
        if(collidedX.size()==0)
            return null;
        for(Actor a :collidedX)
        {
            if(!friendly)
            {
                Class c = a.getClass();
                if(c.equals(Player.class)||c.equals(Head.class))
                    return Player.rightarm;  
            }

            if(a instanceof Mob&&friendly)
            {
                return a;
            }
            if(a instanceof Block)
            {
                Block actor = (Block)a;
                if(!actor.isWall&&actor.isCollidable())
                {
                    return a;
                }
            }
        }
        return null;
    }

    public float getProjectileDamage()
    {
        return damageFromGun;
    }

    /**
     * Returns mob or block that the projectile will collide with vertically.
     */
    public Actor getVerticalCollide()
    {
        setLocation(getX(),getY()+(int)yspe);
        Vec2 point = getRotationAdjustedCollisionPoint();
        ArrayList<Actor> collidedY = (ArrayList<Actor>)getObjectsAtOffset(point.X,point.Y,null);
        setLocation(getX(),getY()-(int)yspe);
        collidedY.remove(this);
        if(collidedY.size()==0)
            return null;
        for(Actor a :collidedY)
        {
            if(!friendly)
            {
                Class c = a.getClass();
                if(c.equals(Player.class)||c.equals(Head.class))
                    return Player.rightarm;  
            }
            if(a instanceof Mob&&friendly)
            {
                return a;
            }
            if(a instanceof Block)
            {
                Block actor = (Block)a;
                if(!actor.isWall&&actor.isCollidable())
                {
                    return a;
                }
            }
        }
        return null;
    }

    /**
     * Whether the projectile rotates with regard to its x/y velocities
     */
    public abstract boolean rotates();

    public void refreshRotation()
    {
        if(rotates())
            setRotation((int)Math.toDegrees(Math.atan2(yspe,xspe)));
    }

    public void fire(int x, int y, int direction, float speed)
    {
        MCWorld.entities.add(this);
        double dir = Math.toRadians(direction);
        MCWorld.theWorld.addObject(this,x,y);
        xspe = (float)Math.cos(dir)*speed;
        yspe = (float)Math.sin(dir)*speed;
        refreshRotation();
    }

    public float getSpeed()
    {
        return (float)Math.sqrt(Math.pow(xspe,2)+Math.pow(yspe,2));
    }

    public void hitMob(Mob mob)
    {
        mob.damage(getProjectileDamage());
    }
    
    public boolean specialCollide(Actor a)
    {
    	return true;
    }
    
    
}

