import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.reflect.Field;
import java.awt.Color;
import java.awt.Font;
/**
 * Write a description of class Slider here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Slider extends Button
{
    double percent;//slider progress out of 1
    int length = 284;//physical length of slider track 
    Field variable;//variable that is adjusted by slider
    Object obj;//object containing the field to be changed, null if variable is static
    Vec2 sliderCenter;//center of slider track
    boolean held = false;//if the button is being currently held
    
    public Slider(String name,int offset,String c,String f,Object o)
    {
        this.offset = offset;
        string  = name;
        try
        {
            variable = Class.forName(c).getField(f);
        }
        catch(ClassNotFoundException e)
        {

        }
        catch(NoSuchFieldException e)
        {

        }
        obj = o;
        try
        {
            percent = variable.getDouble(obj);
        }
        catch(IllegalAccessException e)
        {

        }

        getButtonImage();
    }

    public  

    void OnClick()
    {
        held = true;
    }

    protected void getButtonImage()
    {
        int center = 150;
        GreenfootImage image = new GreenfootImage("images\\sliderback.png");
        image.drawImage(new GreenfootImage("images\\slider.png"),(int)((percent-.5)*length+image.getWidth()/2)-8,0);
        
        Font font = MCWorld.mcFont;
        font = font.deriveFont(16.0f);
        image.setFont(font);
        image.setColor(new Color(224, 224, 224, 255));     
        image.drawString(string, center-string.length()*5+offset, 25);       
        setImage(image);
    }

    public void act()
    {
        if (checkMouseOver())
        {
            if (MouseHelper.buttonsDown == 1)
            {
                held = true;
            }

        }
        if (MCWorld.mouseDown == "lr")
        {
            held = false;
        }
        if (held)
        {
            double per = ((MCWorld.mx - getX()) / (double)(length) + .5f);
            percent = (double)Math.max(0, Math.min(per, 1));
            try
            {
                variable.setDouble(obj, percent);
            }
            catch(IllegalAccessException e)
            {

            }
            
            getButtonImage();
        }
    }
}
