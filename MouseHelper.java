import greenfoot.*;
/**
 * Write a description of class MouseHelper here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MouseHelper  
{
    // instance variables - replace the example below with your own
    static int buttonsDown = 0;  
    static final int btnNEITHER = 0, btnLEFT = 1, btnRIGHT = 3;  

    public static String getMouse()
    {
        if(buttonsDown < 0||buttonsDown == 2||buttonsDown > 4)
        {
            buttonsDown = 0;
        }
        if (Greenfoot.mousePressed(null))  
        {  
            MouseInfo mi = Greenfoot.getMouseInfo();
            if(mi == null)
            {
                return" null";
            }
            buttonsDown += mi.getButton();  
            if (buttonsDown == btnLEFT) return "lp";
            if (buttonsDown == btnRIGHT) return "rp";
            if (buttonsDown == btnLEFT + btnRIGHT) return "bp";
        }  
        if (buttonsDown != btnNEITHER && Greenfoot.mouseClicked(null))   
        {  
            MouseInfo mi = Greenfoot.getMouseInfo();  
            if(mi == null)
            {
                return " null";
            }
            int wasDown = buttonsDown;  
            buttonsDown -= mi.getButton();  
            int buttonReleased = wasDown - buttonsDown;  
            if (buttonReleased == btnLEFT) return "lr"; 
            if (buttonReleased == btnRIGHT) return "rr"; 
            if (buttonsDown == btnNEITHER) return "br";  
        }  
        return "none";
    }
    
}
