import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BasaltCobblestone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BasaltCobblestone extends Block
{
    /**
     * Act - do whatever the BasaltCobblestone wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        onFrameActions();
    }    
    public BasaltCobblestone()
    {
        id = 40;
    }
    protected int getResistance()
    {
        return 18;
    }   
    public String getItemName()
    {
        return "Basalt Cobblestone";
    }
}
