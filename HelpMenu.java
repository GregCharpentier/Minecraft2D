import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class HelpMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HelpMenu extends Blurb
{
    DynamicButton[] buttons;
    advTextBox title;
    public int mode =0;
    public static final GreenfootImage image = new GreenfootImage("images\\inf.png");
    public HelpMenu(int parType,int parX,int parY)
    {
        
        x = parX;
        y = parY;
        MCWorld.theWorld.addObject(this,x,y);
        changeButtons(parType);
        disableUnderButtons();
    }
    public void disableUnderButtons()
    {
        
    }
    public void clearButtons()
    {
        if(buttons == null)
        {
            return;   
        }
        if(title != null)
        {
            MCWorld.theWorld.removeObject(title);
            title = null;
        }
        for(int t = 0;t<buttons.length;t++)
        {
            if(buttons[t] != null)
            {
                MCWorld.theWorld.removeObject(buttons[t]);
                buttons[t] = null;
            }
        }
    }
    public void close()
    {
        clearButtons();
        MCWorld.theWorld.removeObject(this);
        
    }
    public void changeButtons(int parMode)
    {
        //if(this.getWorld()!= null
        setImage(image);
        clearButtons();
        if(parMode == 0)//main
        {
            buttons = new DynamicButton[6];
            buttons[0] = new DynamicButton2("Controls",51);
            buttons[1] = new DynamicButton2("How to Play",59);
            buttons[2] = new DynamicButton2("Achievements/Skills",60);
            buttons[3] = new DynamicButton2("Recipes",52);
            buttons[4] = new DynamicButton2("Credits",53);
            buttons[5] = (DynamicButton)new DynamicButton2("Close",54).togRed(); 
            title = new advTextBox("Help",getX()-18,getY()-180,60, 400,32.0F,false,Color.BLACK);
            title.show();
            ModeManager.buildMenu(buttons);
        }
        if(parMode == 1)//recipes
        {
            buttons = new DynamicButton[5];
            buttons[0] = new DynamicButton2("Crafting",56);
            buttons[1] = new DynamicButton2("Furnace",57);
            buttons[2] = new DynamicButton2("Anvil",58);
            buttons[3] = new DynamicButton2("O-H Furnace",61);
            buttons[4] = (DynamicButton)new DynamicButton2("Back",55).togRed();
            title = new advTextBox("Recipes",getX()-35,getY()-150,60, 400,32.0F,false,Color.BLACK);
            //title = new advTextBox("Recipes",400,400,60, 400,32.0F,false,Color.BLACK);
            title.show();
            ModeManager.buildMenu(buttons);
        }
        if(parMode == 2)//credits
        {
            setImage("images\\credits.png");
            buttons[0] = (DynamicButton)new DynamicButton2("Back",55,x,y+165).togRed();
            buttons[1] = (DynamicButton)new DynamicButton2("",8,x,y+100).togRed();
            buttons[1].setOtherImage("images\\donate.png");
        }
        if(parMode == 3)//basic
        {
            setImage("images\\infcontrolsinstructions.png");
            buttons[0] = (DynamicButton)new DynamicButton2("Back",55,x,y+165).togRed();
        }
        if(parMode == 4)//basic
        {
            setImage("images\\infhowtoplay.png");
            buttons[0] = (DynamicButton)new DynamicButton2("Back",55,x,y+185).togRed();
        }
        if(parMode == 5)//basic
        {
            setImage("images\\infskillsach.png");
            buttons[0] = (DynamicButton)new DynamicButton2("Back",55,x,y+185).togRed();
        }
        mode = parMode;
    }
    
}
