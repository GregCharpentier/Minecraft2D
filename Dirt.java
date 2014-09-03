import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class dirt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dirt extends Block
{
    /**
     * Act - do whatever the dirt wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        onFrameActions();
    }    
    public Dirt()
    {
        id = 4;
    }
    @Override
    protected void onRightClick(int id)
    {
        if(id == 16)
        {
            removeBlockAt(MCWorld.mx,MCWorld.my);
            place(MCWorld.mx,MCWorld.my,new Grass());
            Slot.consumeCurrentItem();
        }
    }
    public String getItemName()
    {
        return "Dirt";
    }
    
    protected int getResistance()
    {
        return 5;
    }
    public String material()
    {
        return "dirt";
    }

    
    
}
