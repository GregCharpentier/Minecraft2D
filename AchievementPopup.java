import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
public class AchievementPopup extends Actor
{

    public GreenfootImage base = new GreenfootImage("images\\achievements\\popup.png");
    public static final int maxHeight = 40;
    public int stage = 0;
    public int timer = 140;
    public AchievementPopup(String title, GreenfootImage icon, int points)
    {
        GreenfootImage image = new GreenfootImage(base);
        image.drawImage(icon,14,14);
        Font font = MCWorld.mcFont;
        font = font.deriveFont(16.0f);
        image.setFont(font);
        image.setColor(new Color(255, 255, 255, 255));
        image.drawString(shorten(title),59,50);
        image.setColor(new Color(0, 255, 0, 255));
        font = font.deriveFont(8.0f);
        image.setFont(font);
        image.drawString("+"+points+" skill points",230,28);
        setImage(image);
        MCWorld.theWorld.addObject(this,ModeManager.sizeX-180,-35);
    }
    public static String shorten(String s)
    {
        if(s.length()<28)
        {
            return s;
        }
        s = s.substring(0,26);
        s += "...";
        return s;
    }
    public void act() 
    {
        if(stage == 0)
        {
            setLocation(getX(),getY()+2);
            if(getY()>=maxHeight)
            {
                setLocation(getX(),maxHeight);
                stage = 1;
            }
        }
        else if(stage ==1)
        {
            timer--;
            if(timer<=0)
            {
                stage = 2;
            }
        }
        else if(stage == 2)
        {
            setLocation(getX(),getY()-2);
            if(getY()<=-35)
            {
                MCWorld.theWorld.removeObject(this);
            }
        }
    }    
}
