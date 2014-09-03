import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SkillIncreaseButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SkillIncreaseButton extends Button
{
    private int ID;
    /**
     * Act - do whatever the SkillIncreaseButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(checkMouse())
        {
            
            onClickAction();
        }
    }    
    public SkillIncreaseButton(int id)
    {
        ID = id;
        leftside = -15;
        rightside = 15;
        upside = -12;
        downside = 12;
    }
    protected void getButtonImage()
    {
        
    }
    protected void onClickAction()
    {
        SkillBar.skillBars[ID].addPoint();
    }
}
