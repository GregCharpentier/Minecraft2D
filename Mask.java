import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mask here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mask extends Actor
{
    /**
     * Act - do whatever the Mask wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setTrans(getTrans(Block.offSetY));
    }    
    public Mask()
    {
        GreenfootImage image =getImage();
        image.setTransparency(100);
        setImage(image);
    }
    private int getTrans(int j)
    {
        j *= -1;
        j -= 650;
        if(j > 1350)
        {
            return 150;
        }
        if(j<0)
        {
            return 0;
        }
        return j/9;
    }
    private void setTrans(int trans)
    {
        GreenfootImage image =getImage();
        image.setTransparency(trans);
        setImage(image);
    }
}
