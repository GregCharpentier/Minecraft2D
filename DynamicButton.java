import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import java.awt.Desktop;
/**
 * Write a description of class DynamicButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DynamicButton extends Button
{
    protected int action;
    
    /**
     * Act - do whatever the DynamicButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(active&&checkMouse())
        {
            onClickAction();
        }
    }      

    public DynamicButton(String text,int paraction)
    {
        action = paraction;
        string = text;
        getButtonImage();
    }

    public DynamicButton(String text,int paraction, int parOffset)
    {
        action = paraction;
        string = text;
        offset = parOffset;
        getButtonImage();
    }

    protected void onClickAction()
    {
        MCWorld.sm.play("click",50);
        switch(action)
        {
            case 0://Save
            {
                Hourglass hrgl = new Hourglass();
                getWorld().addObject(hrgl,getX()-100, getY());
                getWorld().repaint();
                SaveLoad.save();
                getWorld().removeObject(hrgl);
            }
            break;
            case 1://Load
            {
                if(!SaveLoad.checkIfFilesExist())
                {
                    return;
                }
                Hourglass hrgl2 = new Hourglass();
                getWorld().addObject(hrgl2,getX()-100, getY());
                getWorld().repaint();
                getWorld().removeObject(hrgl2);
                ModeManager.clearMainMenu();
                ModeManager.createWorld(true);

            }
            break;
            case 2://Respawn
            {
                Block.offSetX = MCWorld.respawnOffX;
                Block.offSetY = MCWorld.respawnOffY;
                ModeManager.clearDeathMenu();
                ChunkManager.refreshChunks();
                Health.removeHealthBar();
                Health.iniHealthBar(20);
                Block.playerSpeed = 0;
                Block.verticalForce = 0;
            }
            break;
            case 3://Help
            {
                ModeManager.createHelpMenu(); 

            }
            break;
            case 4://Previous Help Page
            {
                ModeManager.theRecipeHelp.previousPage();
            }
            break;
            case 5://Next Help Page
            {
                ModeManager.theRecipeHelp.nextPage();
            }
            break;
            case 6:
            ModeManager.theRecipeHelp.close();ModeManager.createHelpMenu();
            ModeManager.theHelpMenu.changeButtons(1);
            break;

            case 7:
            ModeManager.clearPauseMenu();
            ModeManager.createAchievementScreen();
            break;
            case 8://donate
            String htmlFile = "https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=98M6WADRXPN3Y&lc=US&item_name=Minecraft%202D&currency_code=USD&bn=PP-DonationsBF%3Abtn_donateCC_LG.gif%3ANonHostedGuest"; // path to your new file
            try
            {
                Desktop.getDesktop().browse(java.net.URI.create(htmlFile));
            }
            catch(java.io.IOException e)
            {
                
            }
            break;
            case 9://Options open pm
            {
                ModeManager.clearPauseMenu();
                ModeManager.createOptionsMenu(false);
            }
            break;
            case 10://Options close pm
            {
                ModeManager.clearOptionsMenu(false);
                ModeManager.createPauseMenu();
            }
            break;
            case 11://Options open mm
            {
                ModeManager.clearMainMenu();
                ModeManager.createOptionsMenu(true);
            }
            break;
            case 12://Options close mm
            {
                ModeManager.clearOptionsMenu(true);
                ModeManager.createMainMenu();
            }
            break;
            //helpmenu buttons
            case 50:ModeManager.createHelpMenu();
            break;
            case 51:ModeManager.theHelpMenu.changeButtons(3);
            break;
            case 52:ModeManager.theHelpMenu.changeButtons(1);
            break;
            case 53:ModeManager.theHelpMenu.changeButtons(2);
            break;
            case 54:ModeManager.clearHelpMenu();
            break;
            case 55:ModeManager.theHelpMenu.changeButtons(0);
            break;
            case 56:ModeManager.theHelpMenu.close();ModeManager.createRecipeHelp("crafting");    
            break;
            case 57:ModeManager.theHelpMenu.close();ModeManager.createRecipeHelp("furnace"); 
            break;
            case 58:ModeManager.theHelpMenu.close();ModeManager.createRecipeHelp("anvil"); 
            break;
            case 59:ModeManager.theHelpMenu.changeButtons(4); 
            break;
            case 60:ModeManager.theHelpMenu.changeButtons(5);
            break;
            case 61:ModeManager.theHelpMenu.close();ModeManager.createRecipeHelp("openhearth"); 
            break;
        }
    }
    
}
