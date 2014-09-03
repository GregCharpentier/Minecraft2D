import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AchievementLine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AchievementLine extends Actor
{
    private Vec2 loc = new Vec2();
    public AchievementLine(int length, int width, String color,int x, int y)
    {
        GreenfootImage image = new GreenfootImage("images\\achievements\\"+color+"line.png");
        image.scale(length,width);
        setImage(image);
        loc.X = x;
        loc.Y = y;
        MCWorld.addObject(this,new Vec2());
        act();
        
    }
    public void act()
    {
        Vec2 loc2 = loc.subtract(ModeManager.theAchievementScreen.offset);
        setLocation(loc2.X,loc2.Y);
    }
}
