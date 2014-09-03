import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MOpenFurnace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MOpenFurnace extends Module
{
    private OpenHearthFurnace furnace;
    private static final GreenfootImage MAIN = new GreenfootImage("images\\moduleopenfurnace.png");
    private static final GreenfootImage FIRE = new GreenfootImage("images\\furnaceicon2.png");
    private static final GreenfootImage OVERLAY = new GreenfootImage("images\\furnaceicon.png");
    private static final GreenfootImage THREE = new GreenfootImage("images\\furnaceicon3.png");
    private static final int MAX = 26;
    private static final int MAXARROW = 32;
    private int timer = 0;
    public void act() 
    {
        setIcons();
    }  
    public MOpenFurnace(OpenHearthFurnace parFurnace)
    {
        furnace = parFurnace;
    }
    public void setIcons()
    {
        if(!furnace.burning)
        {
            setImage(MAIN);
            return;
        }
        
        timer++;
        if(timer == 2)
        {
            timer = 0;
            GreenfootImage theimage = new GreenfootImage(32,29);
              GreenfootImage cover = new GreenfootImage(MFurnace.THREE);
            GreenfootImage fire = new GreenfootImage(MFurnace.FIRE);
            GreenfootImage main = new GreenfootImage(MAIN);
            GreenfootImage overlay = new GreenfootImage(MFurnace.OVERLAY);  
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
            main.drawImage(theimage,124,49);
            if(furnace.getProgress() == 0)
            {
                setImage(main);
                return;
            }
            int height2 = (MAXARROW*furnace.getProgress())/100;
            GreenfootImage cover2 = new GreenfootImage(MFurnace.RESULT1);
            GreenfootImage fire2 = new GreenfootImage(MFurnace.RESULT2);
            GreenfootImage theimage2 = new GreenfootImage(42,36);
            theimage2.drawImage(fire2,height2-MAXARROW-4,0);
            theimage2.drawImage(cover2,0,0);
            main.drawImage(theimage2,185,48);
            setImage(main);
        }
    }
}
