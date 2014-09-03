import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Resume here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Resume extends Button
{
    /**
     * Act - do whatever the CreateWorld wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Resume()
    {
        string = "Back to Game";
        getButtonImage();
    }
    
      
    protected void onClickAction()
    {
        if(ModeManager.blockButtons == true)
        {
            return;
        }
        
        ModeManager.clearPauseMenu();       
        
    }
}
