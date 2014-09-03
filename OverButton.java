import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class OverButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OverButton extends DynamicButton
{
    /**
     * Act - do whatever the OverButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public OverButton(String text,int paraction,String image)
    {
        super(text,paraction);
        setImage(image);
        rollover = false;
        leftside =-50;
        rightside =50;
    }
}
