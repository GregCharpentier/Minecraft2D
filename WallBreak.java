import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WallBreak here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WallBreak extends Break
{
    /**
     * Act - do whatever the WallBreak wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setLocation(xx+Block.offSetX,yy+Block.offSetY);
    }    
    public WallBreak(int parTime,int x, int y,boolean parWall)
    {
        super(parTime,x,y,parWall);
    }
    
}
