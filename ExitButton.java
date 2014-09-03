import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ExitButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExitButton extends Button
{
    /**
     * Act - do whatever the ExitButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public ExitButton()
    {
        setImage("images\\achievements\\exit.png");
        leftside = -20;
        rightside = 20;
    }

    protected void onClickAction()
    {
        ModeManager.clearAchievementScreen();
    }

    protected void getButtonImage()
    {

    }

}
