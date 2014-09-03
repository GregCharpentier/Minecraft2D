import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Platform here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Platform extends Block
{
    /**
     * Act - do whatever the dirt wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public String material()
    {
        return "wood";
    }
    public String getItemName()
    {
        return "Platform";
    }
    public void act() 
    {
        
        onFrameActions();
    } 
    public int getResistance()
    {
    	return 2;
    }
    public Platform()
    {
        isPlatform = true;
        id = 6;
    }
    public GreenfootImage shadeImage()
    {
        return new GreenfootImage("images\\platformblack.png");
    }
    public int[] boundingBox(int[] startingpoint)
    {
        int[] box = new int[5];
        box[0] = startingpoint[0]+0;
        box[1] = startingpoint[0]+64;
        box[2] = startingpoint[1]+0;
        box[3] = startingpoint[1]+64;
        box[4] = 5;
        return box;
    }
    public boolean isTransparent()
    {
    	return true;	
    }
    
}
