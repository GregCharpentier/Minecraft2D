import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
/**
 * Write a description of class advTextBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class advTextBox extends TextBox
{
    protected boolean shadow;
    protected Color color;
    protected float size;
    public void act() 
    {
        // Add your action code here.
    }

    public advTextBox(String parText, int x, int y,int parheight, int parwidth,float parsize,boolean parShadow,Color parColor)
    {
        super(parText,x,y,parheight,parwidth);
        size =parsize;
        shadow = parShadow;
        if(parColor == null)
        {
            color = Color.white;
        }
        else
        {
            color = parColor;
        }
        getTextImage(text);
        //setImage("images\\achievements\\popup.png");
    }
    protected void getTextImage(String parText)
    {
        int center = 200;

        GreenfootImage image = new GreenfootImage(width, height);
        Font font = MCWorld.mcFont;
        font = font.deriveFont(size);
        image.setFont(font);
        if(shadow)
        {
            image.setColor(new Color(56, 56, 56, 255));
            image.drawString(parText,center-parText.length()*5+ 2, height/2+2);
        }
        
        image.setColor(color);
        image.drawString(parText,center-parText.length()*5+ 0,height/2);

        
        setImage(image);
    }
}
