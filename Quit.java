import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Toolkit;  
import java.awt.event.WindowEvent;  
import java.lang.reflect.Field; 
import greenfoot.core.WorldHandler;  
import greenfoot.export.GreenfootScenarioViewer;  
import greenfoot.platforms.WorldHandlerDelegate;  
import greenfoot.platforms.standalone.WorldHandlerDelegateStandAlone;  
import javax.swing.RootPaneContainer;
import javax.swing.JFrame;
/**
 * Write a description of class quit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Quit extends Button

{
    public static int timer = 0;
    /**
     * Act - do whatever the CreateWorld wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Quit()
    {
        string = "Quit";
        offset = 5;
        getButtonImage();
    }

       

    public  void close() throws NoSuchFieldException,java.lang.IllegalAccessException{  
        WorldHandler wh = WorldHandler.getInstance();  
        Field whd_get = WorldHandler.class.getDeclaredField("handlerDelegate");  
        whd_get.setAccessible(true);  
        WorldHandlerDelegate w = (WorldHandlerDelegate)whd_get.get(wh);  
        if(w instanceof WorldHandlerDelegateStandAlone)  
        {  
            
            WorldHandlerDelegateStandAlone wsa = (WorldHandlerDelegateStandAlone)w;  
            Field gsv_get = WorldHandlerDelegateStandAlone.class.getDeclaredField("viewer");  
            gsv_get.setAccessible(true);  
            GreenfootScenarioViewer gsv = (GreenfootScenarioViewer)gsv_get.get(wsa);  
            Field f = GreenfootScenarioViewer.class.getDeclaredField("rootPaneContainer");  
            f.setAccessible(true);  
            System.out.println(f.get(gsv).getClass());
            JFrame rpc = (JFrame)f.get(gsv);  
            WindowEvent wev = new WindowEvent(rpc, WindowEvent.WINDOW_CLOSING);  
            Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);  
        }  
        else 
        {
            return;
            //Greenfoot.stop();
        }//Not stand-alone, don't continue.  
    }  

    protected void onClickAction()
    {
        if(ModeManager.blockButtons == true)
        {
            return;
        }
        try
        {
            close();
        }
        catch(NoSuchFieldException e)
        {

        }
        catch(IllegalAccessException e)
        {

        }

    }
}
