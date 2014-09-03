import greenfoot.*; 
import java.util.ArrayList;
/**
 * Manages modes.
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ModeManager  
{
    public static boolean creative = false;
    public static boolean inventory = false;
    public static int sizeX =SaveLoad.getWorldWidth();//750
    public static int sizeY =SaveLoad.getWorldHeight();//640
    
    public static int center =sizeX/2;//750
    public static int centerY= sizeY/2;//640
    private static Button cancelButton;
    private static Button quitButton;
    private static Button startButton; 
    private static Button helpButton;
    private static Button particleEffectsButton;
    private static Button mobSpawnButton;
    private static MenuSwitchButton buttonA;
    private static MenuSwitchButton buttonB;
    private static MenuSwitchButton buttonC;
    private static Button resumeButton; 
    private static Button titleButton;
    private static Button creativeButton;
    private static Button saveButton;
    private static Button optionsButton;
    private static Button loadButton;
    private static Button achButton;
    private static Button donateButton;
    public static InputButton xResButton;
    public static InputButton yResButton;
    private static TitleBanner title;
    private static Tag theTag;
    public static HotBar hotBar;
    private static PauseMask pauseMask;
    public static WallBanner wallbanner;
    public static Player thePlayer;
    public static Selector theSelector;
    public static MCWorld theWorld;
    public static Head theHead;
    private static MCCursor theCursor;
    public static Block theBlock;
    private static Mask theMask;
    public static Inventory theInventory;
    public static Module theModule;
    public static InventoryCrafting theInventoryCrafting;
    public static BackgroundOverlay theOverlay;
    public static boolean blockButtons;
    public static int virtualsizeX =sizeX;//750
    public static int virtualsizeY =sizeY;//640

    public static RecipeHelp theRecipeHelp;
    public static HelpMenu theHelpMenu;
    public static AchievementScreen theAchievementScreen;

    public ModeManager(MCWorld wrld)
    {
        theWorld = wrld;
    }

    public static void createHelpMenu()
    {
        theHelpMenu = new HelpMenu(0,center,centerY);
        if(MCWorld.mode != "pause")
        {
            startButton.setInactive();               
            loadButton.setInactive();    
            helpButton.setInactive(); 
            optionsButton.setInactive(); 
            quitButton.setInactive();  
        }
        else
        {
            titleButton.setInactive();      
            helpButton.setInactive();      
            resumeButton.setInactive();   
            optionsButton.setInactive();
            creativeButton.setInactive();      
            saveButton.setInactive();  
            achButton.setInactive();  
            titleButton.setInactive();  
        }
        MCWorld.paused = true;
    }

    public static void createRecipeHelp(String type)
    {
        ModeManager.theRecipeHelp = new RecipeHelp(type,center,centerY);
    }

    public static void createAchievementScreen()
    {
        ModeManager.theAchievementScreen = new AchievementScreen();
        theAchievementScreen.draw();
        MCWorld.paused = true;
        MCWorld.mode = "achievement";
    }

    public static void setActiveMenuButton(MenuSwitchButton button)
    {
        buttonA.active = false;
        buttonB.active = false;
        buttonC.active = false;
        button.active = true;
        buttonA.getButtonImage();
        buttonB.getButtonImage();
        buttonC.getButtonImage(); 
    }

    public static void createMainMenu()
    {
        MCWorld.mode = "main";
        MCWorld.paused = true;
        startButton = new CreateWorld();                
        loadButton = new DynamicButton("Load Game",1);
        helpButton = new DynamicButton("Help",3);
        quitButton = (Quit)new Quit().togRed();
        optionsButton = new DynamicButton("Options",11);
        title = new TitleBanner();
        theTag = new Tag();

        Button[] buttons = {startButton,loadButton,helpButton,optionsButton,quitButton};
        buildMenu(buttons);

        theWorld.addObject(title, center, 70);
        theWorld.addObject(theTag, 100, sizeY-10);
        theWorld.setBackground("images\\blocks\\Dirt Wall.png");

    }

    public static void createWorld(boolean loaded)
    {
        TextBox box = new TextBox("Loading...");
        theWorld.repaint();
        MCWorld.mode = "world";
        box.edit("Generating chunks...");
        theWorld.repaint();
        ChunkManager.generateChunks();
        MCWorld.paused = false;
        Block.light = new int[1024][1024];
        theBlock = new Block();
        theWorld.addObject(theBlock, 0, 0);
        Selector.position = 0;
        Selector.wall = false;
        theSelector = new Selector();
        Time.setRegTime(700);
        MCWorld.theWorld.addObject(MCWorld.theSun,0,0);
        MCWorld.theWorld.addObject(MCWorld.theMoon,0,0);
        //theCursor = new Cursor();
        //theWorld.addObject(theCursor, 160, 616);
        box.edit("Assembling Steve...");
        theWorld.repaint();
        theHead = new Head();

        thePlayer = new Player();
        theMask = new Mask();
        theMask.getImage().scale(sizeX,sizeY);
        hotBar = new HotBar();
        wallbanner = new WallBanner();
        Block.offSetX = MCWorld.respawnOffX;
        Block.offSetY = MCWorld.respawnOffY;
        Block.verticalForce = 0;
        Block.playerSpeed=0;

        box.edit("Generating inventory slots...");
        theWorld.repaint();
        Inventory.generateSlots();
        StimLabManager.generateStimLabSlots();
        AnvilManager.generateAnvilSlots();
        AchievementManager.initialize();
        Health.iniHealthBar(20);
        Health.removeHealthBar();
        if(loaded)
        {
            //box.edit("Loading terrain from file...");
            box.hide();
            theWorld.repaint();
            SaveLoad.load();   
        }
        else
        {
            //box.edit("Generating terrain...");
            box.hide();
            theWorld.repaint();
            GenerationManager.generateInitialLandscape();
            Health.adjustMaxHealth(20);
            
            SkillBar.mineSpeed.setLevel(0,false);
            SkillBar.moveSpeed.setLevel(0,false);
            SkillBar.damage.setLevel(0,false);
            SkillBar.health.setLevel(0,false);
            PointCounter.points = 0;
        }
        box.show();
        ModeManager.hotBar.refreshBar();

        box.edit("Refreshing chunks...");
        theWorld.repaint();
        ChunkManager.refreshChunks();
        theWorld.setBackground("sky.jpg");
        theOverlay = new BackgroundOverlay();
        Health.showHealthBar();
        theWorld.addObject(theOverlay,center,centerY);
        box.hide();
        theWorld.addObject(theHead, center-1, centerY -73);
        thePlayer.initializeLimbs();
        theWorld.addObject(theMask, center, centerY);
        theWorld.addObject(hotBar, center+22, sizeY-25);  
        theWorld.addObject(wallbanner, 23, centerY); 
        theWorld.addObject(theSelector, center-158,ModeManager.sizeY-26);
        if(creative)
        {
            setCreative();
        }

        
    }
    
    public static void createPauseMenu()
    {
        MCWorld.mode = "pause";
        titleButton = new Title();
        helpButton = new DynamicButton("Help",3);
        resumeButton = new Resume();
        try
		{
			try
			{
				creativeButton = new BooleanButton(ModeManager.class.getField("creative"), "Creative Mode", ModeManager.class.getDeclaredMethod("setCreative"), ModeManager.class.getDeclaredMethod("setSurvival"));
			} catch (NoSuchMethodException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchFieldException e)
		{
			e.printStackTrace();
		} catch (SecurityException e)
		{
			e.printStackTrace();
		}
        saveButton = new DynamicButton("Save Game",0);
        optionsButton = new DynamicButton("Options",9);
        pauseMask = new PauseMask();
        achButton = new DynamicButton("Achievements",7);
        optionsButton = new DynamicButton("Options",9);
        GreenfootImage image = pauseMask.getImage();
        image.scale(ModeManager.sizeX,ModeManager.sizeY);
        pauseMask.setImage(image);
        Button[] buttons = {resumeButton,helpButton,creativeButton,achButton,optionsButton,saveButton,titleButton};
        buildMenu(buttons);
        MCWorld.paused = true;
        theWorld.addObject(pauseMask, center, centerY); 
    }
    
    public static void createOptionsMenu(boolean mainMenu)
    {
        MCWorld.mode = "options";
        titleButton = new Title();
        theTag = new Tag();
        theTag.setImage("images\\tag2.png");
        try{
				particleEffectsButton = new BooleanButton(ParticleEffect.class.getField("particlesOn"), "Particle Effects", null,null);
				mobSpawnButton = new BooleanButton(MobSpawner.class.getField("spawnMobs"),"Mob Spawning",null,null);
		} catch (NoSuchFieldException e){
			e.printStackTrace();
		} catch (SecurityException e){
			e.printStackTrace();
		}
        helpButton = new Slider("Volume",0,"SoundManager","volMultiplier",null);
        if(mainMenu)
        cancelButton = new DynamicButton("Back",12).togRed();
        else
        cancelButton = new DynamicButton("Back",10).togRed();
        xResButton = new InputButton("Window Width",1720,750,virtualsizeX);
        yResButton = new InputButton("Window Height",1280,640,virtualsizeY);
        pauseMask = new PauseMask();
        GreenfootImage image = pauseMask.getImage();
        image.scale(ModeManager.sizeX,ModeManager.sizeY);
        pauseMask.setImage(image);
        Button[] buttons = {helpButton,xResButton,yResButton,particleEffectsButton,mobSpawnButton,cancelButton};
        buildMenu(buttons);
        MCWorld.paused = true;
        if(!mainMenu)
            theWorld.addObject(pauseMask, center, centerY);
            
        theWorld.addObject(theTag, sizeX/2, sizeY-20);
    }

    public static void createDeathMenu()
    {
        MCWorld.mode = "death";
        titleButton = new Title();
        resumeButton = new DynamicButton("Respawn",2);
        pauseMask = new DeathMask();
        pauseMask.getImage().scale(sizeX,sizeY);
        Button[] buttons = {resumeButton,titleButton};
        buildMenu(buttons);
        MCWorld.paused = true;
        theWorld.addObject(pauseMask, center, centerY); 
    }

    public static void createInventoryMenu(String module)
    {
        theWorld.removeObject(theInventory); 
        Module.removeModule();
        MCWorld.mode = module;
        inventory = true;

        theInventory = new Inventory();
        Inventory.showInventory();
        MCWorld.paused = true;
        theWorld.addObject(theInventory, center, centerY); 
        Module.attachModule(module);
        MCWorld.theWorld.removeObject(buttonA);
        MCWorld.theWorld.removeObject(buttonB);
        MCWorld.theWorld.removeObject(buttonC);
        if(creative)
        {
            buttonA = new MenuSwitchButton("inventory");
            buttonB = new MenuSwitchButton("creative");
        }
        else
        {
            buttonA = new MenuSwitchButton("inventory");
            buttonB = new MenuSwitchButton("equipment");
            buttonC = new MenuSwitchButton("skills");
        }

        if(module == "inventory")
        {
            buttonA.active = true;
            buttonA.getButtonImage();
        }
        if(module == "equipment"||module=="creative")
        {
            buttonB.active = true;
            buttonB.getButtonImage();
        }
        if(module == "skills")
        {
            buttonC.active = true;
            buttonC.getButtonImage();
        }
        if(creative)
        {
            MCWorld.theWorld.addObject(buttonA,theInventory.getX()-185,theInventory.getY()-119);
            MCWorld.theWorld.addObject(buttonB,theInventory.getX()-166,theInventory.getY()-119);
        }
        else
        {
            MCWorld.theWorld.addObject(buttonA,theInventory.getX()-185,theInventory.getY()-119);
            //MCWorld.theWorld.addObject(buttonB,theInventory.getX()-147,theInventory.getY()-119);
            MCWorld.theWorld.addObject(buttonC,theInventory.getX()-166,theInventory.getY()-119);
        }
    }

    public static void createCraftingBenchMenu()
    {
        MCWorld.mode = "craftingbench";
        theInventoryCrafting = new InventoryCrafting();
        Inventory.showPartialInventory(0,0);
        MCWorld.paused = true;
        theWorld.addObject(theInventoryCrafting, center, 320); 
    }

    public static void clearAchievementScreen()
    {
        theAchievementScreen.clear();
        theAchievementScreen = null;
        createPauseMenu();
    }

    public static void clearHelpMenu()
    {
        if(theRecipeHelp!=null)
        {
            ModeManager.theRecipeHelp.close();
            createHelpMenu();
        }
        if(theHelpMenu!=null)
        {
            theHelpMenu.close();
        }

        if(MCWorld.mode != "pause")
        {
            startButton.setActive();               
            loadButton.setActive();    
            helpButton.setActive(); 
            optionsButton.setActive();              
            quitButton.setActive(); 
        }
        else
        {
            titleButton.setActive();      
            helpButton.setActive();      
            resumeButton.setActive(); 
            achButton.setActive();
            optionsButton.setActive();                          
            creativeButton.setActive();      
            saveButton.setActive();      
        }
    }

    public static void clearInventoryMenu()
    {

        Inventory.clearInventory();
        inventory = false;
        MCWorld.paused = false;
        MCWorld.interactEnabled = true;

        for(int g = 23;g<32;g++)
        {
            Inventory.slots[g].clearLights();
        }

        hotBar.refreshBar();
        theWorld.removeObject(theInventory); 
        Module.removeModule();
        MCWorld.theWorld.removeObject(buttonA);
        MCWorld.theWorld.removeObject(buttonB);
        MCWorld.theWorld.removeObject(buttonC);
        MCWorld.mode = "world";

    }

    public static void clearCraftingBenchMenu()
    {
        MCWorld.mode = "world";
        Inventory.clearCraftingBenchInventory();
        MCWorld.paused = false;
        for(int g = 23;g<32;g++)
        {
            Inventory.slots[g].clearLights();
        }

        hotBar.refreshBar();
        theWorld.removeObject(theInventoryCrafting); 
    }

    public static void clearRecipeHelp()
    {
        startButton.setActive();               
        loadButton.setActive();    
        helpButton.setActive();     
        quitButton.setActive();    
        ModeManager.theRecipeHelp = null;
    }

    public static void clearDeathMenu()
    {
        MCWorld.mode = "world";
        theWorld.removeObject(titleButton);
        theWorld.removeObject(resumeButton);
        theWorld.removeObject(pauseMask);
        theWorld.removeObject(saveButton);
        MCWorld.paused = false;
    }

    public static void clearPauseMenu()
    {

        clearHelpMenu();
        MCWorld.mode = "world";
        theWorld.removeObject(titleButton);
        theWorld.removeObject(helpButton);
        theWorld.removeObject(resumeButton);
        theWorld.removeObject(pauseMask);
        theWorld.removeObject(creativeButton);
        theWorld.removeObject(saveButton);
        theWorld.removeObject(achButton);
        theWorld.removeObject(optionsButton);
        MCWorld.paused = false;
    }
    public static void clearOptionsMenu(boolean mainMenu)
    {
        MCWorld.mode = "world";
        theWorld.removeObject(helpButton);
        theWorld.removeObject(cancelButton);
        virtualsizeX = xResButton.getValue();
        virtualsizeY = yResButton.getValue();
        SaveLoad.saveRes(virtualsizeX,virtualsizeY);   
        theWorld.removeObject(xResButton);
        theWorld.removeObject(yResButton);
        theWorld.removeObject(particleEffectsButton);
        theWorld.removeObject(mobSpawnButton);
        if(!mainMenu)
        {
            theWorld.removeObject(pauseMask);
        }
        theWorld.removeObject(theTag);
        MCWorld.paused = false;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public static void clearMainMenu()
    {
        theWorld.removeObject(cancelButton);
        theWorld.removeObject(startButton);
        theWorld.removeObject(quitButton);
        theWorld.removeObject(helpButton);
        theWorld.removeObject(loadButton);
        theWorld.removeObject(theTag);
        theWorld.removeObject(title);
        theWorld.removeObject(optionsButton);
    }

    public static void clearWorld()
    {
        if(creative)
        {
            creative = false;
            Block.removeBlockAt(Player.rightSide,Player.headHeight);
            Block.removeBlockAt(Player.rightSide,Player.footHeight+1);
            Block.removeBlockAt(Player.leftSide,Player.headHeight);
            Block.removeBlockAt(Player.leftSide,Player.footHeight+1);
            Block.removeBlockAt(Player.rightSide,(Player.footHeight+Player.headHeight)/2);
            Block.removeBlockAt(Player.leftSide,(Player.footHeight+Player.headHeight)/2);
        }
        ArrayList<Entity> entities = new ArrayList<Entity>(MCWorld.entities);
        for(Entity i :entities)
        {
            i.remove();
        }
        ChunkManager.removeAll();
        MCWorld.paused = true;
        thePlayer.removePlayer();
        Health.removeHealthBar();
        MCWorld.theWorld.removeObject(MCWorld.theSun);
        MCWorld.theWorld.removeObject(MCWorld.theMoon);
        theWorld.removeObject(theBlock);theWorld.removeObject(thePlayer);
        theWorld.removeObject(theSelector);theWorld.removeObject(hotBar);
        theWorld.removeObject(theCursor);theWorld.removeObject(wallbanner);
        theWorld.removeObject(theHead);theWorld.removeObject(theMask);
        theWorld.removeObject(theOverlay);
        Block.blocks = new Block[1024][1024];
        Block.walls = new Wall[1024][1024];
        Health.removeHealthBar();
        creative = false;
    }

    public static void buildMenu(Button[] buttons)
    {
        Quit.timer = 0;
        int y = centerY-(((buttons.length-1)*60)/2);
        for(int i = 0;i<buttons.length;i++)
        {
            theWorld.addObject(buttons[i], center, y);
            y += 60;
        }
    }

    public static void setCreative()
    {
        ModeManager.creative = true;
        ModeManager.thePlayer.removePlayer();
        MCWorld.theWorld.removeObject(ModeManager.thePlayer);
        MCWorld.theWorld.removeObject(ModeManager.theHead);
    }

    public static void setSurvival()
    {
        ModeManager.creative = false;
        ModeManager.thePlayer.initializeLimbs();
        MCWorld.theWorld.addObject(ModeManager.theHead, center-1, centerY-73);
        Block.removeBlockAt(Player.rightSide,Player.headHeight);
        Block.removeBlockAt(Player.rightSide,Player.footHeight-1);
        Block.removeBlockAt(Player.leftSide,Player.headHeight);
        Block.removeBlockAt(Player.leftSide,Player.footHeight-1);
        Block.removeBlockAt(Player.rightSide,(Player.footHeight+Player.headHeight)/2);
        Block.removeBlockAt(Player.leftSide,(Player.footHeight+Player.headHeight)/2);
    }

}
