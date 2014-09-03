import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Module here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Module extends Actor
{
    protected static final int attachX = ModeManager.center+29;
    protected static final int attachY = ModeManager.centerY-65;
    protected static Slot[] moduleSlots;
    private static String currentModule;
    /**
     * Act - do whatever the Module wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }   

    public void setModSlots(Slot[] slots)
    {
        moduleSlots = slots;
    }

    public static void attachModule(String module)
    {
        currentModule = module;
        if(module.equals("inventory"))
        {
            ModeManager.theModule = new MInventory();
            MCWorld.theWorld.addObject(ModeManager.theModule,attachX,attachY);
            moduleSlots = CraftingManager.crafting;
            addSlots();
            Inventory.trash = new Trash(319,249);
            MCWorld.theWorld.addObject(Inventory.trash,319-404+attachX,249-255+attachY);
            return;
        }

        if(module.equals("craftingbench"))
        {
            ModeManager.theModule = new MCrafting();
            MCWorld.theWorld.addObject(ModeManager.theModule,attachX,attachY);
            moduleSlots = CraftingManager.craftingBench;
            addSlots();
            Inventory.trash = new Trash(279-404+attachX,256-255+attachY);
            MCWorld.theWorld.addObject(Inventory.trash,279-404+attachX,256-255+attachY);
            return;
        }

        if(module.equals("furnace"))
        {
            Furnace furnace = (Furnace)Block.getBlockAt(MCWorld.mx,MCWorld.my);
            ModeManager.theModule = new MFurnace(furnace);
            MCWorld.theWorld.addObject(ModeManager.theModule,attachX,attachY);
            moduleSlots = furnace.slots;
            addSlots();
            Inventory.trash = new Trash(297-404+attachX,257-255+attachY);
            MCWorld.paused = false;
            MCWorld.interactEnabled = false;
            return;
        }

        if(module.equals("anvil"))
        {
            ModeManager.theModule = new MAnvil();
            MCWorld.theWorld.addObject(ModeManager.theModule,attachX,attachY);
            moduleSlots = AnvilManager.anvil;
            addSlots();
            Inventory.trash = new Trash(279-404+attachX,256-255+attachY);
            MCWorld.theWorld.addObject(Inventory.trash,279-404+attachX,256-255+attachY);
            return;
        }
        if(module.equals("openfurnace"))
        {
            OpenHearthFurnace furnace = (OpenHearthFurnace)Block.getBlockAt(MCWorld.mx,MCWorld.my);
            ModeManager.theModule = new MOpenFurnace(furnace);
            MCWorld.theWorld.addObject(ModeManager.theModule,attachX,attachY);
            moduleSlots = furnace.slots;
            addSlots();
            Inventory.trash = new Trash(297-404+attachX,257-255+attachY);
            MCWorld.theWorld.addObject(Inventory.trash,297-404+attachX,257-255+attachY);
            MCWorld.paused = false;
            MCWorld.interactEnabled = false;
            return;
        }
        if(module.equals("equipment"))
        {
            ModeManager.theModule = new MEquipment();
            MCWorld.theWorld.addObject(ModeManager.theModule,attachX,attachY);
            return;
        }
        if(module.equals("skills"))
        {
            ModeManager.theModule = new MSkills();
            MCWorld.theWorld.addObject(ModeManager.theModule,attachX,attachY);
            MSkills.pointcounter = new PointCounter(attachX+60,attachY-45,40,200);
            int cent = attachX+25;
            for(int i=0;i<SkillBar.skillBars.length;i++)
            {
                MCWorld.theWorld.addObject(SkillBar.skillBars[i],cent+60*(i-SkillBar.skillBars.length/2),attachY);
            }
            return;
        }
        if(module.equals("stimlab"))
        {
            ModeManager.theModule = new MStimLab();
            MCWorld.theWorld.addObject(ModeManager.theModule,attachX,attachY);
            moduleSlots = StimLabManager.crafting;
            addSlots();
            Inventory.trash = new Trash(282-404+attachX,258-255+attachY);
            MCWorld.theWorld.addObject(Inventory.trash,282-404+attachX,258-255+attachY);
            return;
        }

        if(module.equals("chest"))
        {
            ModeManager.theModule = new MChest((Chest)Block.getBlockAt(MCWorld.mx,MCWorld.my));
            MCWorld.theWorld.addObject(ModeManager.theModule,attachX,attachY);
            moduleSlots = ((MChest)ModeManager.theModule).chest.load(404,255);
            addSlots();
            MCWorld.mouseDown = "none";
            //Inventory.trash = new Trash(279-404+attachX,256-255+attachY);
            //MCWorld.theWorld.addObject(Inventory.trash,279-404+attachX,256-255+attachY);
            return;
        }
        if(module.equals("creative"))
        {
            ModeManager.theModule = new MCreative();
            MCWorld.theWorld.addObject(ModeManager.theModule,attachX,attachY);
            addSlots();
            MCWorld.mouseDown = "none";
            return;
        }

    }

    public static void removeModule()
    {
        if(currentModule == null)
        {
            return;
        }
        ModeManager.theModule.onRemove();
        MCWorld.theWorld.removeObject(ModeManager.theModule);
        for(int t=0;t<moduleSlots.length;t++)
        {
        	moduleSlots[t].onRemove();
            MCWorld.theWorld.removeObject(moduleSlots[t]);
        }
        MCWorld.theWorld.removeObject(Inventory.trash);
        currentModule = null;
        return;

    }

    public static String getCurrentModule()
    {
        return currentModule;
    }

    public void onRemove()
    {

    }

    public void refresh()
    {

    }

    public void cutStacks()
    {

    }

    private static void addSlots()
    {
        for(int t=0;t<moduleSlots.length;t++)
        {
            MCWorld.theWorld.addObject(moduleSlots[t],moduleSlots[t].x-404+attachX,moduleSlots[t].y-255+attachY);//255
        }
    }

}
