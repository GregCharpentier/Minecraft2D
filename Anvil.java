import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Anvil here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Anvil extends Block
{
   
    /**
     * Act - do whatever the Anvil wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        onFrameActions();
    }    
    public String getItemName()
    {
        return "Anvil";
    }
    @Override
    protected void onRightClick(int id)
    {
        ModeManager.createInventoryMenu("anvil");       
    } 
    public Anvil()
    {
        id = 45;
    }
    public GreenfootImage shadeImage()
    {
        return new GreenfootImage("images\\anvilblack.png");
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
