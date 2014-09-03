import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Plant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Flax extends Block
{
    
    public String getItemName()
    {
        return "Flax";
    }
    @Override
    protected int getResistance()
    {
        return 1;
    }
    public int[] boundingBox(int[] startingpoint)
    {
        int[] box = new int[5];
        box[0] = startingpoint[0];
        box[1] = startingpoint[0]+64;
        box[2] = startingpoint[1];
        box[3] = startingpoint[1]+64;
        box[4] = 5;
        return box;
    }
    public boolean isCollidable()
    {
        return false;
    }
    public Flax()
    {
    	id = 87;
    }
    public void act() 
    {
        
        onFrameActions();
    } 
    public GreenfootImage shadeImage()
    {
        return new GreenfootImage("images\\flaxblack.png");
    }
    public boolean isTransparent()
    {
    	return true;	
    }
    
}
