import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DynamicButton2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DynamicButton2 extends DynamicButton
{
    public DynamicButton2(String text,int paraction,int x,int y)
    {
        super(text,paraction);
        MCWorld.theWorld.addObject(this,x,y);
    }
    public DynamicButton2(String text,int paraction)
    {
        super(text,paraction);
    }
}
