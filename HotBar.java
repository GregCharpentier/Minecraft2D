import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class hotBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HotBar extends Actor
{
    private static final int offX = 8;
    private static final int offY = 7;
    private static final int offX2 = 8;
    private static final int offY2 = 8;
    public static int[] BarIDs= new int[16];
    public static Slot[] HotSlots= new Slot[16];
    //Positions 0-9 are the bottom bar, 10-14 are the side bar, 15 is the red slot
    /**
     * Act - do whatever the hotBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    public void refreshBar()
    {
        Slot ss[] = Inventory.slots;
        GreenfootImage[] IDS = new GreenfootImage[16];
        for(int t = 23;t<32;t++)
        {
            BarIDs[t-22] =ss[t].blockType;
            HotSlots[t-22] =ss[t];
            IDS[t-22] = new GreenfootImage(Slot.getStackImage(ss[t].stackSize,ss[t].blockType,ss[t].blockDamage));
        }
        for(int t = 0;t<5;t++)
        {
            BarIDs[t+10] =ss[t].blockType;
            HotSlots[t+10] =ss[t];
            IDS[t+10] = new GreenfootImage(Slot.getStackImage(ss[t].stackSize,ss[t].blockType,ss[t].blockDamage));
        }
        {
            BarIDs[15] =Inventory.redSlot.blockType;
            HotSlots[15] =Inventory.redSlot;
            IDS[15] = new GreenfootImage(Slot.getStackImage(Inventory.redSlot.stackSize,Inventory.redSlot.blockType,Inventory.redSlot.blockDamage));
        }
        GreenfootImage hotbar = new GreenfootImage("images\\hotbar1.png");
        for(int t = 1;t<10;t++)
        {
            if(ss[t+22] != null)
            hotbar.drawImage(IDS[t],offX + (40*t),offY);
        }
        hotbar.drawImage(IDS[15],offX + (40*0),offY);
        GreenfootImage hotbar2 = new GreenfootImage("images\\hotbar2.png");
        for(int t = 10;t<15;t++)
        {
            if(ss[t-10] != null)
            hotbar2.drawImage(IDS[t],offX2,offY2 + (40*(t-10)));
        }
        ModeManager.wallbanner.setImage(hotbar2);
        setImage(hotbar);
    }
    
}
