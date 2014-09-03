import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Controls here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Controls extends Button
{
    /**
     * Act - do whatever the CreateWorld wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Controls()
    {
        string = "Controls";
        getButtonImage();
    }
    
       
    protected void onClickAction()
    {
        if(ModeManager.blockButtons == true)
        {
            return;
        }
        //ModeManager.createBlurb();       
        
    }
}
