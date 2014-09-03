import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
/**
 * Write a description of class InputButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InputButton extends Button
{
    protected boolean selected;
    protected String inputString = "";
    protected int max;
    protected int min;
    protected boolean backDown;
    public InputButton(String s, int max,int min,int start)
    {
        string = s;
        this.max = max;
        this.min = min;
        inputString+= start+""; 
        getButtonImage();

    }

    public void act()
    {
        super.act();
        if(MCWorld.mouseDown.equals("lp")&&!checkMouse()&&selected)
        {
            deselect();
        }
        if(selected)
        {
            getButtonImage();
            selectedOnFrame();
        }
    }

    protected void onClickAction()
    {
        select();
    }

    protected void select()
    {
        selected = true;
        getButtonImage();
        inputString = "";
    }

    private void adjustValue()
    {
        int value = Math.max(Math.min(getValue(),max),min);
        inputString = value+"";
    }

    protected void deselect()
    {
        selected = false;
        adjustValue();
        getButtonImage();
    }

    public int getValue()
    {
        if(inputString.length() == 0)
            return 0;
        return Integer.parseInt(inputString);
    }

    public void selectedOnFrame()
    {

        int i = KeyboardHandler.getPressed();
        if(i!=-1)
        {
            if(inputString.length()<5)
            {
                inputString+= i+""; 
            }
        }
        else if(Greenfoot.isKeyDown("backspace"))
        {
            if(!backDown)
            {
                backDown = true;
                if(inputString.length()>0)
                {
                    inputString = inputString.substring(0,inputString.length()-1);
                }
            }

        }
        else if(Greenfoot.isKeyDown("enter"))
        {
            deselect();
        }
        else
        {
            backDown = false;
        }

    }

    protected void getButtonImage()
    {
        int center = 150;
        String im = "inputbutton";
        if(selected)
            im+="selected";
        GreenfootImage image = new GreenfootImage("images\\"+im+".png");
        Font font = MCWorld.mcFont;
        font = font.deriveFont(16.0f);
        image.setFont(font); 

        image.setColor(new Color(56, 56, 56, 255));        
        image.drawString(string, 12, 27);

        image.setColor(new Color(224, 224, 224, 255));
        image.drawString(string, 10, 25);   

        image.drawString(inputString, 185, 27); 

        setImage(image);
    }

    
}
