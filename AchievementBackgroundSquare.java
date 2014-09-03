import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AchievementBackgroundSquare here.
 */
public class AchievementBackgroundSquare extends Actor
{
    public Vec2 loc = new Vec2();
    
    public AchievementBackgroundSquare(Vec2 v)
    {
        loc.X = v.X;
        loc.Y = v.Y;
    }
    
    public AchievementBackgroundSquare(String block,Vec2 v)
    {
        this(v);
        setImage(new GreenfootImage("images\\blocks\\dark\\"+block+".png"));
    }
    
    public void act()
    {
        Vec2 loc2 = loc.multiply(48.0).subtract(ModeManager.theAchievementScreen.offset);
        setLocation(loc2.X+24,loc2.Y+24);
    }
}
