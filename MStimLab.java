import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MStimLab here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MStimLab extends Module
{
    /**
     * Act - do whatever the MStimLab wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    public void refresh()
    {
        StimLabManager.refresh();
    }
    public void cutStacks()
    {
        StimLabManager.cutStacks();
    }
}
