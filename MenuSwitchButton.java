import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MenuSwitchButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MenuSwitchButton extends Button
{
    private final String menu;
    private final GreenfootImage image;
    public boolean active = false;
    private final boolean disabled;
    /**
     * Act - do whatever the MenuSwitchButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public MenuSwitchButton(String parMenu)
    {
        if(parMenu == null)
        {
            disabled = true;
            menu = null;
            image = null;
        }
        else
        {
            menu = parMenu;
            image = new GreenfootImage("images\\menuicon"+parMenu+".png");
            disabled = false;
            getButtonImage();
        }
        leftside = -9;
        rightside = 9;
        upside = -9;
        downside = 9;
        
    }   
    protected void getButtonImage()
    {
        //int center = 150;
        if(active)
        {
            setImage("images\\menuselector2.png");
        }
        else
        {
            setImage("images\\menuselector.png");
        }
        //GreenfootImage image = new GreenfootImage(200, 32);
        //Font font = MCWorld.mcFont;
        //font = font.deriveFont(16.0f);
        //image.setFont(font);
//         if(hovered)
//         {
//             image.setColor(new Color(63, 63, 40, 255));
//         }
//         else
//         {
//             image.setColor(new Color(56, 56, 56, 255));
//         }
//         image.drawString(string, 2, 27);
//         if(hovered)
//         {
//             image.setColor(new Color(255, 255, 160, 255));
//         }
//         else
//         {
//             image.setColor(new Color(224, 224, 224, 255));
//         }        
//         image.drawString(string, 0, 25);        
        GreenfootImage finalimage = getImage();
        finalimage.drawImage(image,3,3);
        setImage(finalimage);
    }
    protected boolean checkMouse()
    {
        if(checkMouseOver() &&MCWorld.mouseDown == "lp")
        {
            return true;
        }
        return false;
    }
    public void act() 
    {
        if(disabled||active)
        {
            return;
        }
        if(checkMouse())
        {
            System.out.println("!!");
            onClickAction(menu);
        }
    }
    protected void onClickAction(String parMenu)
    {
        
            ModeManager.clearInventoryMenu();
            ModeManager.createInventoryMenu(parMenu);
        
    }
    
}
