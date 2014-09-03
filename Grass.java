import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Grass here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Grass extends Block
{
    /**
     * Act - do whatever the dirt wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        onFrameActions();
    }    
    public Grass()
    {
        id = 8;
    }
    public String getItemName()
    {
        return "Grass";
    }
    protected int getResistance()
    {
        return 7;
    }
    @Override
    protected int itemOnDestroy()
    {
        if(Greenfoot.getRandomNumber(3) == 1)
        {
            return -2;
        }
        else
        {
            return 4;
        }
    }
    @Override
    protected int[] itemOnDestroyMultiple()
    {
        
        int[] items = {4,16};
        return items;
    }
    @Override
    protected int[] itemOnDestroyMultipleAmount()
    {
        int[] amounts = {1,1};
        return amounts;
    }
    public String material()
    {
        return "grass";
    }

}
