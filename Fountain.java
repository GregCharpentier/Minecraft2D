import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fountain here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fountain extends Block
{
    /**
     * Act - do whatever the Fountain wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    @Override
    protected void onRightClick(int id)
    {
        if(id == 76)
        {
            if(Slot.addItems(77,1))            
            {
               Slot.consumeCurrentItem(); 
            }
            
            
        }
    }
    public String getItemName()
    {
        return "Fountain";
    }
    public Fountain()
    {
        id = 78;
        isUnbreakable = true;
    }
    public void act() 
    {
        onFrameActions();
    } 
    public GreenfootImage shadeImage()
    {
        return new GreenfootImage("images\\fountainblack.png");
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
