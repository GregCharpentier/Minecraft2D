import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class selector here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Selector extends Actor
{
    public static String blockType;
    public static int position = 0;
    public static boolean wall= false;
    public static int qTimer = 0;
    private static int storedWall=0;
    private static int storedBlock=9;
    private static int lastID = 0;
    private static final int bottomSize = 10;
    private static final int sideSize = 5;
    private static final int totalSize = bottomSize +sideSize;
    /**
     * Act - do whatever the selector wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {

        setPosition();
        moveToPosition();
        setBlockType();
        checkKey();
        if(getBlockType() !=lastID)
        {

            paintItem();
        }
        lastID = getBlockType();
        if(!wall)
        {
            for(int i =1;i<=9;i++)
            {
                if(Greenfoot.isKeyDown(""+i))
                {
                    position =i-1;
                }
            }
            if(Greenfoot.isKeyDown("0"))
            {
                position=9;
            }
        }
        else
        {
            for(int i =1;i<=5;i++)
            {
                if(Greenfoot.isKeyDown(""+i))
                {
                    position =i+9;
                }
            }
        }

    }    

    public static void paintItem()
    {

        if(ModeManager.creative)
        {
            ModeManager.thePlayer.item1.setImage("images\\emptyslot.png");
            ModeManager.thePlayer.item2.setImage("images\\emptyslot.png");
            return;
        }
        Slot slot = getSelectedSlot();
        Block b = Block.getHeldItem();
        if(slot.blockType == 0)
        {
            if(Head.lastDirection)
            {
                ModeManager.thePlayer.item1.setImage("images\\emptyslot.png");
                return;
            }
            else
            {
                ModeManager.thePlayer.item2.setImage("images\\emptyslot.png");
                return;
            }
        }
        GreenfootImage item = new GreenfootImage("images\\itemicons\\"+b.getBlockImageName()+".png");
        GreenfootImage image2;

        item.scale((int)(item.getWidth()*b.getHeldScale()),(int)(item.getHeight()*b.getHeldScale()));
        image2 = new GreenfootImage((int)(item.getWidth()*b.getHeldScale()),(int)(item.getHeight()*b.getHeldScale()));
        image2.drawImage(item,0,0);

        if(Head.lastDirection)
        {
            ModeManager.thePlayer.item1.setImage(image2);
        }
        else
        {
            image2.mirrorVertically();
            ModeManager.thePlayer.item2.setImage(image2);

        }
    }
    private void setPosition()
    {
        if(!MCWorld.paused)
        {
            position+=MCWorld.Scroll;
        }

        if(!wall)
        {
            if(position > bottomSize-1)
                position = 0;
            if(position < 0)
                position = bottomSize-1;
        }
        else
        {
            if(position < bottomSize)
                position = totalSize-1;
            if(position > totalSize-1)
                position = bottomSize;
        }
        if(Greenfoot.isKeyDown("f"))
        {
            if(wall)
            {
                wall = false;
                setRotation(0);
            }
            position =0;
        }

    }

    public static int getBlockType()
    {
        if(position == 0)
        {
            return HotBar.BarIDs[15];
        }
        return HotBar.BarIDs[position];        
    }

    public static Slot getSelectedSlot()
    {
        if(position == 0)
        {
            return HotBar.HotSlots[15];
        }
        return HotBar.HotSlots[position];
    }

    private void setBlockType()
    {
        if(position<9)
        {
            switch(HotBar.BarIDs[position] + 1)
            {
                case -1:blockType = "null";
                break;
                case 0:blockType = "stone";
                break;
                case 1:blockType = "cobblestone";
                break;
                case 2:blockType = "bricks";
                break;
                case 3:blockType = "dirt";
                break;
                case 4:blockType = "planks";
                break;
                case 5:blockType = "wood";
                break;
                case 6:blockType = "grass";
                break;
                case 7:blockType = "sand";
                break;
                case 8:blockType = "stonebricks";
                break;
            }
        }
        else
        {

        }
    }

    private void checkKey()
    {
        if(qTimer == 16)
        {

            if(Greenfoot.isKeyDown("q"))
            {
                qTimer = 0;
                if(wall)
                {
                    wall = false;
                    turn(270);
                    storedWall = position;
                    position = storedBlock;
                    setLocation(getBottomX(),ModeManager.sizeY-26);
                }
                else
                {
                    wall = true;
                    turn(90);
                    storedBlock = position;
                    position = storedWall;
                    setLocation(23,getSideY());
                    //setLocation(getBottomX(),ModeManager.sizeY-26);
                    //setLocation(52,getSideY());
                }
            }
        }
    }

    private int getBottomX()
    {
        return ModeManager.center-158 + 40*position;
    }

    private int getSideY()
    {
        return 40*position+ModeManager.centerY-480;
    }

    private void moveToPosition()
    {
        if(!wall)
        {
            setLocation(getBottomX(),ModeManager.sizeY-26); 
        }
        else
        {
            setLocation(23,getSideY());
            //setLocation(getBottomX(),ModeManager.sizeY-26);
        }
    }    
}
