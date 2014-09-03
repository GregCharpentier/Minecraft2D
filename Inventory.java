import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class iNVENTORY here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Inventory extends Actor
{
    public static Slot[] slots = new Slot[32];
    public static Trash trash;
    public static Slot redSlot;
    private static final int[] slotX;
    private static final int[] slotY;
    public static final int offX = 45;
    public static final int offY = 18;
    public static final int xx = ModeManager.center-176;//199
    public static final int yy = ModeManager.centerY-136;//184
    public static boolean holding = false;

    public void act() 
    {
        if(Slot.timer)
        {
            Slot.timer = false;
        }
    }  

    public Inventory()
    {

    }

    public static void generateSlots()
    {
        for(int t = 0;t<slots.length;t++)
        {
            slots[t] = new Slot(slotX[t]+xx,slotY[t]+yy);
        }
        redSlot = new Slot(xx-25+offX,yy+190+offX);
        if(MCWorld.DEVMODE)
        {
             slots[1].setItem(7 ,8 );
             slots[2].setItem(17,32);
             slots[3].setItem(46,1 );
             slots[4].setItem(52,1 );
             slots[5].setItem(36,1 );
             slots[6].setItem(35,1 );
             slots[7].setItem(20,24);
             slots[8].setItem(43,16);
             slots[9].setItem(53,1 );
            slots[10].setItem(63,8 );
            slots[11].setItem(50,8 );
            slots[12].setItem(49,1 );
            slots[13].setItem(75,1 );
            slots[14].setItem(73,8 );
            slots[15].setItem(70,8 );
            slots[16].setItem(72,8 );
            slots[17].setItem(71,18);
            slots[18].setItem(78,2 );
            slots[19].setItem(9 ,18);
            slots[20].setItem(53,18);
            slots[21].setItem(76,18);
            slots[22].setItem(57,1 );
        }
        CraftingManager.generateCraftingSlots();

    }

    public static void showInventory()
    {
        for(int t = 0;t<slots.length;t++)
        {
            MCWorld.theWorld.addObject(slots[t],slots[t].x,slots[t].y);
        }

        if(holding)
        {
            MCWorld.theWorld.addObject(Slot.heldItem,MCWorld.mx,MCWorld.my);
        }
        MCWorld.theWorld.addObject(redSlot,xx-32+offX,yy+193+offX);

    }

    public static void addTrash(int x,int y)
    {
        trash = new Trash(xx+x+75+offX,yy+y+20+offX);
        MCWorld.theWorld.addObject(trash,xx+x+75+offX,yy+y+20+offX);
    }

    public static void removeTrash()
    {
        MCWorld.theWorld.removeObject(trash);
    }

    public static void showPartialInventory(int x, int y)
    {
        for(int t = 0;t<slots.length;t++)
        {
            MCWorld.theWorld.addObject(slots[t],slots[t].x+xx+x,slots[t].y+yy+y);
        }

        if(holding)
        {
            MCWorld.theWorld.addObject(Slot.heldItem,MCWorld.mx,MCWorld.my);
        }
        MCWorld.theWorld.addObject(redSlot,xx-32+offX+x,y+193+offX+yy);

    }

    public static void clearInventory()
    {
        for(int t = 0;t<slots.length;t++)
        {
        	slots[t].onRemove();
            MCWorld.theWorld.removeObject(slots[t]);
        }
        for(int t = 0;t<CraftingManager.crafting.length;t++)
        {
        	CraftingManager.crafting[t].onRemove();
            MCWorld.theWorld.removeObject(CraftingManager.crafting[t]);
        }
        MCWorld.theWorld.removeObject(trash);
        if(holding)
        {
        	Slot.heldItem.onRemove();
            MCWorld.theWorld.removeObject(Slot.heldItem);
        }
        MCWorld.theWorld.removeObject(redSlot);
    }

    public static void clearCraftingBenchInventory()
    {
        for(int t = 0;t<slots.length;t++)
        {
        	slots[t].onRemove();
            MCWorld.theWorld.removeObject(slots[t]);
        }

        if(holding)
        {
        	Slot.heldItem.onRemove();
            MCWorld.theWorld.removeObject(Slot.heldItem);
        }
        redSlot.onRemove();
        MCWorld.theWorld.removeObject(redSlot);
    }
    static
    {
        int[] Xs = {-32+offX,-32+offX,-32+offX,-32+offX,-32+offX,16+offX,52+offX,88+offX,124+offX,160+offX,196+offX,232+offX,268+offX,304+offX,
                16+offX,52+offX,88+offX,124+offX,160+offX,196+offX,232+offX,268+offX,304+offX,
                16+offX,52+offX,88+offX,124+offX,160+offX,196+offX,232+offX,268+offX,304+offX};
        int[] Ys = {28+offY,64+offY,100+offY,136+offY,172+offY,136+offY,136+offY,136+offY,136+offY,136+offY,136+offY,136+offY,136+offY,136+offY,
                172+offY,172+offY,172+offY,172+offY,172+offY,172+offY,172+offY,172+offY,172+offY,
                220+offY,220+offY,220+offY,220+offY,220+offY,220+offY,220+offY,220+offY,220+offY};
        slotX = Xs;
        slotY = Ys;
        //141+offX,177+offX,141+offX,177+offX,253+offX,
        //36+offY,36+offY,72+offY,72+offY,56+offY,
    }
}
