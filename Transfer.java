import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Transfer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Transfer extends Slot
{
    /**
     * Act - do whatever the Transfer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setLocation(MCWorld.mx,MCWorld.my);
    } 
    public Transfer(int i, int j, int q)
    {
        super(i,j);
        blockType = q;
        getStackImage();
        
    }
    public void checkNull()
    {
        if(stackSize < 1)
        {
            getWorld().removeObject(this);
        }
    }
}
