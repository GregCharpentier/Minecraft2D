import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Head here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Head extends Actor
{
    /**
     * Act - do whatever the Head wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public static boolean lastDirection = false;
    public void act() 
    {
        if(MCWorld.paused)
        {
            return;
        }
        if(checkMouse())
        {
            if(lastDirection == false)
            {
                setImage("images\\head.png");
                lastDirection = true;
                ModeManager.thePlayer.item2.setImage("images\\emptyslot.png");
                Selector.paintItem();
            }
        }
        else
        {
            if(lastDirection == true)
            {
                setImage("images\\headreverse.png");
                lastDirection = false;
                ModeManager.thePlayer.item1.setImage("images\\emptyslot.png");
                Selector.paintItem();
            }
        }
    }   

    public static boolean checkMouse()
    {
        if(MCWorld.mx > ModeManager.center)
        {

            return true;
        }

        return false;
    }
}
