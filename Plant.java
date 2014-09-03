import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Plant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plant extends Block
{
    int counter = 0;
    public void act() 
    {
        onFrameActions();
        counter++;
        checkIfAdvance();
    }    
    public String getItemName()
    {
        return "Plant";
    }
    public boolean checkIfAdvance()
    {
        return false;
    }
    @Override
    protected int getResistance()
    {
        return 1;
    }
    protected int itemOnDestroy()
    {
        return 0;
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
}
