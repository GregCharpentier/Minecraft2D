import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Title here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Title extends Button
{
    /**
     * Act - do whatever the CreateWorld wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Title()
    {
        string = "Quit to Title";
        offset = 15;
        getButtonImage();
    }
     
       
    protected void onClickAction()
    {
        if(ModeManager.blockButtons == true)
        {
            return;
        }
        ModeManager.clearWorld();
        if(MCWorld.mode.equals("death"))
        {
            ModeManager.clearDeathMenu();
        }
        else
        {
            ModeManager.clearPauseMenu();
        }
        
        ModeManager.createMainMenu();
        
        
    }
}
