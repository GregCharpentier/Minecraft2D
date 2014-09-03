import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
/**
 * Write a description of class TextOverButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SmallButton extends DynamicButton
{
    /**
     * Act - do whatever the TextOverButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    public SmallButton(String text,int paraction,String image)
    {
        super(text,paraction);
        leftside =-110;
        rightside =110;
    }
    protected void getButtonImage()
    {
        int center = 150;
        if(hovered)
        {
            setImage("images\\smallbuttonlit.png");
        }
        else
        {
            setImage("images\\smallbutton.png");
        }
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
}
