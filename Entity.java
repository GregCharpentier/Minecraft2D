import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Entity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Entity extends Actor
{
    public float x;
    public float y;
    public float xspe;
    public float yspe;
    
    //public int [] bounds;
    public void act() 
    {
        refreshLocation();
        if(MCWorld.mode == "main")
        {
            MCWorld.theWorld.removeObject(this);
        }
        
    }

    protected int[] box()
    {
        return null;
    }

    protected int getHitImmunityLength()
    {
        return 0;
    }

    

    public Entity()//int xx, int yy)
    {
        // x = xx;
        //y = yy;
    }

    public void addedToWorld(World wrld)
    {
        x = getX()-Block.offSetX;
        y = getY()-Block.offSetY;
    }

    protected void onFrame()
    {

    }

    protected void refreshLocation()
    {
        setLocation((int)(x+Block.offSetX),(int)(y+Block.offSetY));
    }

    protected void setLoc(int xx, int yy)
    {
        x=xx;
        y=yy;
    }
    /**
     * Removes entity from entity list and world, returns true if entity was on the entity list
     */
    public boolean remove()
    {
        MCWorld.theWorld.removeObject(this);
        return MCWorld.entities.remove(this);
    }
}
