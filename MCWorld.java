import greenfoot.*;  // (Actor, World, Greenfoot, GreenfootImage)
import greenfoot.core.WorldHandler;  
import java.awt.event.*;  
import java.io.*;
import java.net.URLDecoder;
import java.awt.Font;
import greenfoot.core.*;  
import javax.swing.*;  
import java.awt.*;
import java.util.ArrayList;
public class MCWorld extends World
{

    static int mx, my;
    
    public static boolean paused = true;
    public static String mode = "main";
    public static Sun theSun = new Sun();
    public static Sun theMoon = new Sun().setAsMoon();
    public static String mouseDown;
    public static World theWorld;
    private static int timerPause = 0;
    public static ModeManager modeManager;
    public static Font mcFont;
    public static String filePath;
    public static boolean interactEnabled = true;
    private static int time=0;
    private static int chunkTimer = 0;
    public static final boolean COMPRESSED = false;
    public static int lightcount = 0;
    public static GreenfootImage skyGradient = new GreenfootImage("images\\lightscale.png");
    public static int lastDaylight = 0;
    public static final boolean DEVMODE = false;
    public static final int respawnOffXF = -500*64;
    public static final int respawnOffYF = -500;
    public static int respawnOffX = -500*64;
    public static int respawnOffY = -500;
    public static SoundManager sm;
    ScrollingListener scroll=new ScrollingListener();  
    static int Scroll = 0;  
    public static ArrayList<Entity> entities = new ArrayList<Entity>();
    public MCWorld() 
    {
        
        super(ModeManager.sizeX, ModeManager.sizeY, 1,false);
        //super(750, 640, 1,false);
        //super(850, 720, 1,false);
        
        Greenfoot.start();
        
        Greenfoot.setSpeed(50);
        addObject(new FPS(), 50, 10);
        String path = Block.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        /*
        JPanel panel = WorldHandler.getInstance().getWorldCanvas();  
        GreenfootImage image = new GreenfootImage("images\\cursor.png");  
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(image.getAwtImage(), new Point(0, 0), "");  
        panel.setCursor(cursor);
        */
        try {

             filePath = URLDecoder.decode(path, "UTF-8");
        }
        catch(java.io.UnsupportedEncodingException r){
            System.err.println("UnsupportedEncodingException: " + r.getMessage());

        }
        filePath = filePath.split("Minecraft2D.jar")[0]+"/";
        
        
        mcFont = new Font("q",0,0);
        
        try {
            mcFont = Font.createFont(0,new File(filePath+"resources\\MCFont.ttf"));
        }
        catch(java.awt.FontFormatException r){
            System.err.println("FontFormatException: " + r.getMessage());

        }
        catch(java.io.IOException r){
            System.err.println("FontFormatException: " + r.getMessage());

        }
        mcFont = mcFont.deriveFont(16.0f);
        
        modeManager = new ModeManager(this);
        theWorld = this;
        
        setPaintOrder(Actor.class,FPS.class,ImageObj.class,DebugButton.class,AchievementPopup.class,OverButton.class,ExitButton.class,TextBox.class,Slot2.class,RecipeHelp.class,DynamicButton2.class,Cancel.class,Achievement.class,AchievementLine.class,AchievementBackgroundSquare.class,Blurb.class,Transfer.class,Slot.class,SkillIncreaseButton.class,SkillBar.class,Module.class,MenuSwitchButton.class,Inventory.class,Hourglass.class,Button.class,Cursor.class,
        PauseMask.class,Health.class,Selector.class,HotBar.class,Mask.class,Break.class,Block.class,TitleBanner.class,BlockBorder.class,Item1.class,Arm.class,Head.class,Player.class,Leg.class,Item2.class,Arm2.class,Entity.class,Torch.class,WallBreak.class,Wall.class,Sun.class,BackgroundOverlay.class);
        
        setActOrder(Block.class,Furnace.class,Module.class,Wall.class,Button.class,Cursor.class,Selector.class,HotBar.class,Mask.class,Player.class);
        WorldHandler.getInstance().getWorldCanvas().addMouseWheelListener(scroll);  
        if(DEVMODE)
        {
            DebugButton deb = new DebugButton();
            addObject(deb,20,200);
                
                
        }
        ModeManager.createMainMenu();
        
        //addObject(new Cursor(), 160, 616);
        LightingImageBank.initializeBank();
        sm = new SoundManager();
    }
    
    public static void updateBackground()
    {
        Color color = skyGradient.getColorAt(Time.getDaylight(),0);
        GreenfootImage image = new GreenfootImage(1,1);
        image.setColor(color);
        image.fillRect(0,0,1,1);//image.getWidth(),image.getHeight());
        image.scale(MCWorld.theWorld.getWidth(),MCWorld.theWorld.getHeight());
        ModeManager.theOverlay.setImage(image);
        int oldBrightness = Block.SUNLIGHT;
        int newBrightness = 0;
        int dayLight = Time.getDaylight();
        float d = 0;
        if(dayLight>300)
        {
            newBrightness = 17;
        }
        else if(dayLight<150)
        {
            newBrightness = 6;
        }
        else
        {
            
            float b = dayLight-150;
            float c = 150/11;
            d = b/c+6;
            newBrightness = Math.round(d);
            if(newBrightness > 17)
            {
                newBrightness = 17;
            }
            else if(newBrightness < 6)
            {
                newBrightness = 6;
            }
        }
        
        if(newBrightness != oldBrightness)
        {
            Block.SUNLIGHT = newBrightness;
            for(int x=1015;x>6;x--)
                {

                    Block.refreshLight(x,15,true,true);
                }
            Block.refreshLight(250,15,true,true);
            Block.refreshLight(550,15,true,true);
            Block.refreshLight(900,15,true,true);
        }
        
    }

    

    
    
    
    public static void addObject(Actor a, Vec2 pos)
    {
        theWorld.addObject(a,pos.X,pos.Y);
    }
    public void act() {
        KeyboardHandler.act();
        if(!paused)
        {
            Time.addTime();
            if(Time.getDaylight() != lastDaylight)
            {
                updateBackground();
                lastDaylight = Time.getDaylight();
            }
            MobSpawner.act();
            Health.onFrame();
        }
    
        if(mode == "world")
        {
            chunkTimer++;
            if(chunkTimer == 4)
            {
               chunkTimer = 0;
               ChunkManager.refreshChunks();
               
               
            }
        }
        if(Greenfoot.getMouseInfo() != null) 
        { // Update the current mouse position.
            mx = Greenfoot.getMouseInfo().getX();
            my = Greenfoot.getMouseInfo().getY();
        }
        mouseDown = MouseHelper.getMouse();

        if(Selector.qTimer < 16)
        {
            Selector.qTimer++;
        }
        Scroll=scroll.getScroll();
        if(timerPause < 20)
        {
            timerPause++;
        }
        if(mode == "world"&&timerPause == 20)
        {
            if(Greenfoot.isKeyDown("escape"))
            {

                ModeManager.createPauseMenu();
                timerPause = 0;
            }   
        }
        if(mode == "pause"&&timerPause == 20)
        {
            if(Greenfoot.isKeyDown("escape"))
            {
                ModeManager.clearPauseMenu();
                timerPause = 0;
            }  

        }
        if(mode == "world"&&timerPause == 20)
        {
            if(Greenfoot.isKeyDown("e"))
            {
                if(ModeManager.creative)
                {
                    ModeManager.createInventoryMenu("creative");
                }
                else
                {
                    ModeManager.createInventoryMenu("inventory");
                }
                
                //paused = true;
                timerPause = 0;
            }   
        }
        
        if(Greenfoot.isKeyDown("e"))
        {
            if(ModeManager.inventory&&timerPause == 20)
            {
                ModeManager.clearInventoryMenu();
                timerPause = 0;
            }
            
        }  
        if(ModeManager.theBlock!=null)
        {
            ModeManager.theBlock.blockOnFrame();
        }
        if(ModeManager.theAchievementScreen!=null)
            ModeManager.theAchievementScreen.act();
        
    }

    private void onPauseFrame()
    {

    }
    
    

}



class ScrollingListener implements MouseWheelListener  
{  
    int scroll = 0;  

    public void mouseWheelMoved(MouseWheelEvent MWE)  
    {  
        scroll+=MWE.getWheelRotation();    
        MWE.consume();  
    }  

    public int getScroll()  
    {  
        int a=scroll;  
        scroll=0;  
        return a;  
    }  
}
