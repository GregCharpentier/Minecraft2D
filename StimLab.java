import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StimLab here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StimLab extends Block
{
    /**
     * Act - do whatever the StimLab wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        onFrameActions();
    }  
    public StimLab()
    {
        id = 75;
    }
    public String getItemName()
    {
        return "Stim Lab";
    }
    @Override
    protected void onRightClick(int id)
    {
        ModeManager.createInventoryMenu("stimlab");       
    } 
    public GreenfootImage shadeImage()
    {
        return new GreenfootImage("images\\stimlabblack.png");
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
}
