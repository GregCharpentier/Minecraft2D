import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    protected boolean clicked = false;
    protected int leftside = -150;
    protected int rightside = 150;
    protected int upside = -20;
    protected int downside = 20;
    protected boolean hovered = false;
    protected int offset = 0;
    protected String string;
    public boolean active = true;
    public boolean rollover = true;
    private boolean red = false;
    protected boolean otherImage = false;
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(active&&checkMouse())
       {
           MCWorld.sm.play("click");
            onClickAction();
       }
    }  
    public void setInactive()
    {
        active = false;
        hovered = false;
        getButtonImage(); 
    }
    public void setActive()
    {
        active = true;
    }
    protected boolean checkMouseOver()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse == null)
        {
            if(hovered)
            {
                hovered = false;
                if(rollover)
                getButtonImage(); 
            }
            return false; 
        }
        if(MCWorld.mx > leftside + getX()&&MCWorld.mx < rightside + getX())
        {
            if(MCWorld.my > upside + getY()&&MCWorld.my < downside + getY())
            {
                if(!hovered)
                {
                    hovered = true;
                    if(rollover)
                    getButtonImage(); 
                }

                return true;

            }
        }
        if(hovered)
        {
            hovered = false;
            if(rollover)
            getButtonImage(); 
        }

        return false;
    }

    protected boolean checkMouse()
    {
        if(checkMouseOver() &&MCWorld.mouseDown == "lp")
        {
            return true;
        }
        return false;
    }
    
    public Button togRed()
    {
        red = !red;
        getButtonImage();
        return this;
    }

    protected void getButtonImage()
    {
        int center = 150;
        String im = "button";
        if(red)
        im+="red";
        if(hovered)
        im+="lit";
        setImage("images\\"+im+".png");
        
        GreenfootImage image = new GreenfootImage(200, 32);
        Font font = MCWorld.mcFont;
        font = font.deriveFont(16.0f);
        image.setFont(font);
        if(hovered)
        {
            image.setColor(new Color(63, 63, 40, 255));
        }
        else
        {
            image.setColor(new Color(56, 56, 56, 255));
        }
        image.drawString(string, 2, 27);
        if(hovered)
        {
            image.setColor(new Color(255, 255, 160, 255));
        }
        else
        {
            image.setColor(new Color(224, 224, 224, 255));
        }        
        image.drawString(string, 0, 25);        
        GreenfootImage finalimage = getImage();
        finalimage.drawImage(image,center-string.length()*5+offset , 0);
        setImage(finalimage);
    }

    protected void checkClick()
    {

        //clicked = false;
        if(Greenfoot.mouseClicked(getWorld()))
        {
            clicked = true;
            onClickAction();

        }
        if(MCWorld.mouseDown != "lp")
        {
            //return false;
        }
    }
    
    protected void onClickAction()
    {
        
    }
    public void setOtherImage(GreenfootImage image)
    {
        super.setImage(image);
        otherImage = true;
    }
    public void setImage(GreenfootImage image)
    {
        if(!otherImage)
        {
            super.setImage(image);
        }
    }
    public void setOtherImage(String image)
    {
        super.setImage(image);
        otherImage = true;
    }
    public void setImage(String image)
    {
        if(!otherImage)
        {
            super.setImage(image);
        }
    }
}
