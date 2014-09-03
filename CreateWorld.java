import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CreateWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CreateWorld extends Button
{
    
    public CreateWorld()
    {
        string = "Create New World";
        getButtonImage();
    }
       
    protected void onClickAction()
    {
        if(ModeManager.blockButtons == true)
        {
            return;
        }
        
        MCWorld.paused = false;
        
        ModeManager.clearMainMenu();
        ModeManager.createWorld(false);        
        
    }
}
