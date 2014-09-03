import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MAnvil here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MAnvil extends Module
{
    /**
     * Act - do whatever the MAnvil wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    public void cutStacks()
    {
        AnvilManager.cutStacks();
    }
    public void refresh()
    {
        AnvilManager.refresh();
    }
}
