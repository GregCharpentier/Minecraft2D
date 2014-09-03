import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Sun here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sun extends Actor
{
    /**
     * Act - do whatever the Sun wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
    }    
    public void setToTime(int time)
    {
        
        float nutime = time;
        float divisor = Time.MAXTIME/6.28f;
        float newtime = nutime/divisor;
        int reseter;
        reseter = 1;
        
        if(newtime>3.14)
        {
            reseter = -1;
            if(newtime<3.145)
            {
                
            }
        }
        double x=ModeManager.center+(ModeManager.sizeX-150)*reseter*Math.cos(newtime);
        double y=ModeManager.centerY+(ModeManager.sizeY/2.133)+(ModeManager.sizeY-40)*-Math.abs(Math.sin(newtime));//300
        setLocation((int)x,(int)y);
    }
    public Sun setAsMoon()
    {
        setImage("images/moon.png");
        return this;
    }
}
