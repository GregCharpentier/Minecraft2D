import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Torch here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Torch extends Block
{
    /**
     * Act - do whatever the Torch wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
       onFrameActions();
    } 
    public Torch()
    {
        id = 63;
    }
    public boolean isLight()
    {
        return true;
    }
    protected int getResistance()
    {
        return 2;
    }
    public int lightLevel()
    {
        return 15;
    }
    public String getItemName()
    {
        return "Torch";
    }
    @Override
    public boolean onPlace(int x, int y)
    {
        if((Block.blocks[x][y+1] == null||!Block.blocks[x][y+1].isCollidable()) &&Block.walls[x][y] == null)
        {
           return true; 
        }
        return false;
    }
    public boolean isCollidable()
    {
        return false;
    }
    public int[] boundingBox(int[] startingpoint)
    {
        int[] box = new int[4];
        box[0] = startingpoint[0]+28;
        box[1] = startingpoint[0]+36;
        box[2] = startingpoint[1]+24;
        box[3] = startingpoint[1]+64;
        return box;
    }
}
