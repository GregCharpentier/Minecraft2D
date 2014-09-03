import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DebugButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DebugButton extends Button
{   
    protected boolean checkMouse()
    {
        if(checkMouseOver() &&MCWorld.mouseDown == "lp")
        {
            return true;
        }
        return false;
    }
    protected void getButtonImage()
    {
    }
    public void act() 
    {
        
        if(checkMouse())
        {
            System.out.println("!!");
            onClickAction();
        }
    }
    protected void onClickAction()
    {
        MobSpawner.generateMob(Greenfoot.getRandomNumber(2)==0,new Creeper());
            
        
    }
}
