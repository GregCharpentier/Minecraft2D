import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
/**
 * Write a description of class TextBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TextBox extends Actor
{
    public static final int center = ModeManager.center;
    public static final int centerY = ModeManager.centerY;
    protected boolean shown;
    protected int xx;
    protected int yy;
    protected int height;
    protected int width;
    protected String text;
    public void act() 
    {
        // Add your action code here.
    }    
    protected void getTextImage(String parText)
    {
        int center = 200;
       
        GreenfootImage image = new GreenfootImage(width, height);
        Font font = MCWorld.mcFont;
        font = font.deriveFont(16.0f);
        
        image.setFont(font);
        image.setColor(new Color(56, 56, 56, 255));
        image.drawString(parText,center-parText.length()*5+ 2, height/2+2);
        image.setColor(new Color(224, 224, 224, 255));
        image.drawString(parText,center-parText.length()*5+ 0,height/2);
        
       
        
        setImage(image);
    }
    public TextBox(String parText, int x, int y,int parheight, int parwidth)
    {
           xx = x;
           yy = y;
           show();
           height = parheight;
           width = parwidth;
           shown = true;
           text = parText;
           getTextImage(text);
    }
    public TextBox(String parText)
    {
           xx = center;
           yy = centerY;
           show();
           height = 30;
           width = 400;
           shown = true;
           text = parText;
           getTextImage(text);
    }
    public void show()
    {
        MCWorld.theWorld.addObject(this,xx,yy);
        shown = true;
    }
    public void show(int xx, int yy)
    {
        MCWorld.theWorld.addObject(this,xx,yy);
        shown = true;
    }
    public void edit(String newstring)
    {
        getTextImage(newstring);
    }
    public void hide()
    {
        MCWorld.theWorld.removeObject(this);
        shown = false;
    }
    
}
