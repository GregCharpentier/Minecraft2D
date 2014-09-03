import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class MFurnace extends Module
{
    private Furnace furnace;
    private static final GreenfootImage MAIN = new GreenfootImage("images\\modulefurnace.png");
    public static final GreenfootImage FIRE = new GreenfootImage("images\\furnaceicon2.png");
    public static final GreenfootImage OVERLAY = new GreenfootImage("images\\furnaceicon.png");
    public static final GreenfootImage THREE = new GreenfootImage("images\\furnaceicon3.png");
    public static final GreenfootImage RESULT1 = new GreenfootImage("images\\furnaceresulticon.png");
    public static final GreenfootImage RESULT2 = new GreenfootImage("images\\furnaceresulticon2.png");
    private static final int MAX = 26;
    private static final int MAXARROW = 32;
    private int timer = 0;
    /**
     * Act - do whatever the MFurnace wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public MFurnace(Furnace parFurnace)
    {
        furnace = parFurnace;
    }
    public void act() 
    {
        //System.out.println(furnace.getFuel());
        setIcons();
    }    
    public void setIcons()
    {
        if(!furnace.burning)
        {
            setImage("images\\modulefurnace.png");
            return;
        }
        
        timer++;
        if(timer == 2)
        {
            timer = 0;
            GreenfootImage theimage = new GreenfootImage(32,29);
             GreenfootImage cover = new GreenfootImage(THREE);
            GreenfootImage fire = new GreenfootImage(FIRE);
            GreenfootImage main = new GreenfootImage(MAIN);
            GreenfootImage overlay = new GreenfootImage(OVERLAY);  
            int height = (MAX*furnace.getFuelPercent())/100;
            System.out.println(height);
            if(height < 1)
            {
                height = 1;
            }
            //fire.scale(32,(height));
            theimage.drawImage(fire,0,0);
            theimage.drawImage(cover,0,-2-height);
            theimage.drawImage(overlay,0,0);
            main.drawImage(theimage,157,49);
            if(furnace.getProgress() == 0)
            {
                setImage(main);
                return;
            }
            int height2 = (MAXARROW*furnace.getProgress())/100;
            GreenfootImage cover2 = new GreenfootImage(RESULT1);
            GreenfootImage fire2 = new GreenfootImage(RESULT2);
            GreenfootImage theimage2 = new GreenfootImage(42,36);
            theimage2.drawImage(fire2,height2-MAXARROW-4,0);
            theimage2.drawImage(cover2,0,0);
            main.drawImage(theimage2,218,48);
            setImage(main);
        }
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
    
}
