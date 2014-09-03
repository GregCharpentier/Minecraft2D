import greenfoot.*; // (World, Actor, GreenfootImage, and Greenfoot)

import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;
import java.io.File;
/**
 * Write a description of class Slot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Slot extends Actor
{
    //protected String image = "off";
    protected boolean hover = false;
    protected static final int leftside = -18;
    protected static final int rightside = 18;
    protected static final int upside = -18;
    protected static final int downside = 18;
    public static final float FONT_SIZE = 16.0f;
    protected boolean isCrafting = false;
    protected boolean isProduct = false;
    public boolean isDisplay = false;
    public boolean isInfinite = false;
    public boolean isVirtual = false;
    public static final int WIDTH = 32;
    public static final int HEIGHT = 32;
    public ImageObj descRect;
    //Controls the in-game name as well as the name of the image used. !!DEPRECATED!!
    public static final String[] blockIDs = {"null","Stone","Cobblestone","Bricks","Dirt","Planks","Platform","Wood","Grass","Sand","Stone Bricks","Stone Wall","Plank Wall",//12
            "Dirt Wall","Brick Wall","Wood Wall","Seeds","Coal","Stick","Coal Ore","Iron Ore","Gold Ore","Diamond Ore","Glass","Mossy Stone Bricks","Mossy Cobblestone",//25
            "Crafting Table","Door","Wooden Pickaxe","Wooden Axe","Wooden Shovel","Stone Pickaxe","Stone Axe","Stone Shovel","Wooden Hammer","Stone Hammer","Furnace",//36
            "Iron Ingot","Basalt","Basalt Wall","Basalt Cobblestone","Leaves","Basalt Coal Ore","Silver Ore","Silver Ingot","Anvil","Iron Pickaxe","Iron Axe","Iron Shovel",//48
            "Iron Hammer","Steel Ingot","Impurities","Open-Hearth Furnace","Limestone","Limestone Wall","Steel Pickaxe","Steel Axe","Steel Shovel","Steel Hammer","Silver Pickaxe",//59
            "Silver Axe","Silver Shovel","Silver Hammer","Torch","Bedrock","Wooden Sword","Stone Sword","Iron Sword","Silver Sword","Steel Sword","Hypodermic Needle","Health Stim",//71
            "Cleome","Base Stim","Super Base Stim","Stim Lab","Bottle","Bottle of Water","Fountain","Plant1","Plant2","Plant3","Strange Seeds","Chest","Sugar"};
    public int blockType;
    public int blockDamage = 0;
    public int x;
    public int y;
    public static boolean timer = false;
    public Integer stackSize = 0;
    protected int prevStackSize;
    public boolean filled = false;
    public Item tiedItem;

    public static Transfer heldItem;
    protected static int slotsDisplayed;
    /**
     * Act - do whatever the Slot wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {

    	hoverCheck();
            
       
        if(!isDisplay)
            clickCheck();

    }  

    public Slot setAsCrafting()
    {
        isCrafting = true;
        return this;
    }

    public Slot setAsProduct()
    {
        isProduct = true;
        return this;
    }

    public Slot setAsInfinite()
    {
        isInfinite = true;
        return this;
    }

    public Slot setAsDisplay()
    {
        isDisplay = true;
        return this;
    }
    public Slot setAsVirtual()
    {
    	isVirtual = true;
    	return this;
    }
    protected void getStackImage()
    {
    	if(isVirtual)
    		return ;
        setIconImageToID();
        if(stackSize == 0||stackSize == 1)
        {
            setIconImageToID();
            return;
        }
        //setImage(finalimage);
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);
        Font font = MCWorld.mcFont;
        font = font.deriveFont(FONT_SIZE);
        image.setFont(font);
        image.setColor(new Color(0,0,0,100));
        image.drawString(stackSize.toString(), 2, 16);
        image.drawString(stackSize.toString(), 0, 16);
        image.drawString(stackSize.toString(), 2, 14);
        image.drawString(stackSize.toString(), 0, 14);
        image.setColor(new Color(255,255,255,255));
        image.drawString(stackSize.toString(), 1, 15);
        GreenfootImage finalimage = getImage();
        if(stackSize <10)
        {
            finalimage.drawImage(image, 16, 14);
        }
        else
        {
            finalimage.drawImage(image, 4, 14);
        }

        setImage(finalimage);
    }

    protected static GreenfootImage  getStackImage(Integer size,int id,int dam)
    {
    	
        GreenfootImage theImage = new GreenfootImage(idToIcon(id,dam));
        if(size == 0||size == 1)
        {

            return theImage;
        }

        //setImage(finalimage);
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);

        image.setColor(new Color(0, 0, 0, 160));
        //image.fillRect(0, 0, WIDTH, HEIGHT);
        image.setColor(new Color(255, 255, 255, 100));
        //image.fillRect(5, 5, WIDTH-10, HEIGHT-10);

        Font font = MCWorld.mcFont;
        font = font.deriveFont(FONT_SIZE);
        image.setFont(font);
        image.setColor(Color.WHITE);
        image.drawString(size.toString(), 0, 14);
        GreenfootImage finalimage = theImage;
        if(size <10)
        {
            finalimage.drawImage(image, 16, 16);
        }
        else
        {
            finalimage.drawImage(image, 6, 16);
        }

        return finalimage;
    }

    public void setStackSize(int size)
    {
        stackSize = size;
        if(stackSize < 1)
        {
            removeItem();
        }
        getStackImage();
    }

    public void addStackSize(int size)
    {
        stackSize += size;
        if(stackSize < 1)
        {
            removeItem();
        }
        getStackImage();
    }

    /*public Slot(int i, int j)
    {
    x = i;
    y = j;
    stackSize = 0;
    blockType = 0;
    }
     */
    public Slot show()
    {
        MCWorld.theWorld.addObject(this,x,y);
        return this;
    }

    public Slot(int i, int j)
    {
        x = i;
        y = j;
        stackSize = 0;
        blockType = 0;
    }
    public Slot()
    {
        x = 0;
        y = 0;
        stackSize = 0;
        blockType = 0;
        setAsVirtual();
    }
    

    public static String idToIcon(int id,int damage)
    {
        if(id == 0)
        {
            return "images\\itemicons\\null.png";
        }
        Block b = Block.getBlockOf(id,damage);
        return "images\\itemicons\\"+b.getIconImageName()+".png";
    }

    private void setIconImageToID()
    {
        setImage(idToIcon(blockType,blockDamage));
    }

    public void clearLights()
    {
        setImage(idToIcon(blockType,blockDamage));
        getStackImage();
        hover = false;
    }

    protected void clickCheck()
    {
    	if(isVirtual)
    		return;
        boolean resetImage = true;
        if(isClicked()=="lp")
        {
            if(isProduct)
            {
                switch(blockType)
                {
                    case 26:
                    AchievementManager.getAchievement("Benchmarking").achieve();
                    break;
                    case 28:
                    AchievementManager.getAchievement("Time to Mine!").achieve();
                    break;
                    case 65:
                    AchievementManager.getAchievement("Time to Strike!").achieve();
                    break;
                    case 45:
                    if(AchievementManager.getAchievement("Hammer Time!").achieve())
                    	MCWorld.sm.play("mchammer");
                    break;
                    case 52:
                    AchievementManager.getAchievement("We Need More Heat").achieve();
                    break;
                    case 36:
                        AchievementManager.getAchievement("Hot Topic").achieve();
                        break;
                    case 85:
                        AchievementManager.getAchievement("Time to Hunt!").achieve();
                        break;
                    case 89:
                        AchievementManager.getAchievement("Time to Shoot!").achieve();
                        break;
                    case 90:
                        AchievementManager.getAchievement("Mmph mph-mph mmmmph!").achieve();
                        break;
                }

            }
            if(!Inventory.holding)
            {

                if(blockType != 0)
                {
                    if(isProduct &&Greenfoot.isKeyDown("shift"))
                    {
                        while(blockType != 0)
                        {
                            addItems(blockType,stackSize,blockDamage);
                            removeItem();
                            ModeManager.theModule.cutStacks();
                            ModeManager.theModule.refresh();
                        }
                        return;
                    }
                    if(isInfinite)
                    {
                        heldItem = new Transfer(MCWorld.mx,MCWorld.my,blockType); 
                        if(Greenfoot.isKeyDown("shift"))
                            heldItem.stackSize = Block.maxStackSize(heldItem.blockType);
                        else
                            heldItem.stackSize = 1;
                        heldItem.getStackImage();
                        getWorld().addObject(heldItem,MCWorld.mx,MCWorld.my);
                        Inventory.holding = true;
                        return;
                    }
                    heldItem = new Transfer(MCWorld.mx,MCWorld.my,blockType);
                    heldItem.stackSize = stackSize;
                    heldItem.blockDamage = blockDamage;
                    removeItem();
                    getStackImage();
                    heldItem.getStackImage();
                    getWorld().addObject(heldItem,MCWorld.mx,MCWorld.my);
                    Inventory.holding = true;

                    if(isProduct)
                    {
                        ModeManager.theModule.cutStacks();
                        ModeManager.theModule.refresh();
                    }
                }
                else
                {
                    resetImage = false;
                }
            }
            else
            {
                if(isProduct)
                {
                    if(blockType != heldItem.blockType||blockDamage != heldItem.blockDamage)
                    {
                        return;
                    }
                    if(heldItem.stackSize + stackSize<=Block.maxStackSize(blockType))
                    {
                        heldItem.addStackSize(stackSize);
                        removeItem();
                        ModeManager.theModule.cutStacks();
                        ModeManager.theModule.refresh();
                    }
                    else
                    {
                        int over = (heldItem.stackSize + stackSize)-Block.maxStackSize(blockType);
                        setStackSize(over);
                        heldItem.setStackSize(Block.maxStackSize(blockType));
                    }
                    return;
                }
                if(isInfinite)
                {
                    if(blockType == 0)
                    {
                        getWorld().removeObject(heldItem);
                        heldItem = null;
                        Inventory.holding = false;
                        return;
                    }
                    if(heldItem.blockType == blockType&&heldItem.blockDamage == blockDamage)
                    {
                        heldItem.addStackSize(1);
                        if(Greenfoot.isKeyDown("shift"))
                        {
                            heldItem.setStackSize(Block.maxStackSize(blockType));
                        }
                    }
                    else
                    {
                        getWorld().removeObject(heldItem);
                        heldItem = null;
                        Inventory.holding = false;
                    }

                    return;
                }
                if(blockType == 0)
                {
                    setItem(heldItem.blockType,heldItem.stackSize,heldItem.blockDamage);
                    getWorld().removeObject(heldItem);
                    heldItem = null;

                    Inventory.holding = false;
                }
                else
                {
                    if(blockType != heldItem.blockType||heldItem.blockDamage != blockDamage)
                    {
                        int toInsert = heldItem.blockType;
                        int toExtract = blockType;
                        int sizeExtract = stackSize;
                        int sizeInsert = heldItem.stackSize;
                        int damageInsert = heldItem.blockDamage;
                        int damageExtract = blockDamage;
                        getWorld().removeObject(heldItem);
                        heldItem = new Transfer(MCWorld.mx,MCWorld.my,toExtract);
                        getWorld().addObject(heldItem,MCWorld.mx,MCWorld.my);
                        setBlockType(toInsert);
                        setIconImageToID();
                        setStackSize(sizeInsert);
                        setBlockDamage(damageInsert);
                        heldItem.setStackSize(sizeExtract);
                        heldItem.setBlockDamage(damageExtract);
                    }
                    else
                    {
                        int size = (heldItem.stackSize + stackSize);
                        if(size <= Block.maxStackSize(blockType))
                        {
                            setStackSize(size);
                            getWorld().removeObject(heldItem);
                            heldItem = null;
                            Inventory.holding = false;
                        }
                        else
                        {
                            size = size-Block.maxStackSize(blockType);
                            setStackSize(Block.maxStackSize(blockType));
                            heldItem.setStackSize(size);
                        }
                    }
                }
            }
            ModeManager.hotBar.refreshBar();
            if(isCrafting)
            {
                ModeManager.theModule.refresh();
            }
            if(resetImage)
            {
                hover = false;
                hoverCheck();
            }
        }
        else if(isClicked() == "rp")
        {
            if(isProduct)
            {
                return;
            }
            if(!Inventory.holding)
            {
                if(blockType != 0&&stackSize > 1)
                {
                    heldItem = new Transfer(MCWorld.mx,MCWorld.my,blockType);
                    int removed = stackSize/2;
                    heldItem.stackSize += removed;
                    stackSize -= removed;
                    heldItem.blockDamage = blockDamage;
                    getStackImage();
                    heldItem.getStackImage();
                    getWorld().addObject(heldItem,MCWorld.mx,MCWorld.my);
                    Inventory.holding = true;
                }
                else
                {
                    resetImage = false;
                }
            }
            else
            {

                if(blockType == heldItem.blockType&&blockDamage==heldItem.blockDamage)
                {
                    addStackSize(1);
                    if(heldItem.stackSize==1)
                    {
                        getWorld().removeObject(heldItem);
                        heldItem = null;
                        Inventory.holding = false;

                    }
                    else
                    {
                        heldItem.addStackSize(-1);
                    }
                }
                else if(blockType == 0)
                {
                    setBlockType(heldItem.blockType);
                    setStackSize(1);
                    setBlockDamage(heldItem.blockDamage);
                    heldItem.addStackSize(-1);
                    if(heldItem.stackSize==0)
                    {
                        getWorld().removeObject(heldItem);
                        heldItem = null;
                        Inventory.holding = false;
                    }

                }
                else
                {
                    resetImage = false;
                }
            }
            ModeManager.hotBar.refreshBar();

            if(resetImage)
            {
                hover = false;
                hoverCheck();
            }
            if(isCrafting)
            {
                ModeManager.theModule.refresh();
            }
        }

    }
    
    public static boolean addItem(int id,int damage)
    {

        if(id == 0)
            return true;
        if(Inventory.redSlot.canStack(id,damage)&&Inventory.redSlot.stackSize <Block.maxStackSize(id))
        {
            Inventory.redSlot.addStackSize(1); 
            ModeManager.hotBar.refreshBar();
            return true;
        }
        for(int t = 31;t>5;t--)
        {
            if(Inventory.slots[t].canStack(id,damage)&&Inventory.slots[t].stackSize <Block.maxStackSize(id))
            {
                Inventory.slots[t].addStackSize(1);  
                ModeManager.hotBar.refreshBar();
                return true;
            }
        }

        for(int h = 31  ;h>5;h--)
        {
            if(Inventory.slots[h].blockType == 0)
            {
            	Inventory.slots[h].setItem(id,1, damage);
                ModeManager.hotBar.refreshBar();
                return true;
            }
        }
        return false;

    }
    public static boolean addItems(int id,int size)
    {
    	return addItems(id,size,0);
    }
    public static boolean addItems(int id,int size,int damage)
    {
    	for(int i = 0;i<size;i++)
    	{
    	     if(!addItem(id, damage))
    	    	 return false;
    	}
    	return true;
    }

    public static void consumeCurrentItem()
    {

        if(Selector.getSelectedSlot().stackSize == 1)
        {
            Selector.getSelectedSlot().setBlockType(0);
            Selector.getSelectedSlot().setStackSize(0);
        }
        else
        {
            Selector.getSelectedSlot().addStackSize(-1);
        }
        ModeManager.hotBar.refreshBar();
    }

    public void removeItem()
    {
        setBlockType(0);
        stackSize = 0;
        setBlockDamage(0);
        setIconImageToID();
    }
    
    public Slot setItem(int id, int size)
    {
        setItem(id,size,0);
        
        return this;
    }
    
    public Slot setItem(int id, int size,int damage)
    {
        setBlockType(id);
        setStackSize(size);
        setBlockDamage(damage);
        return this;
    }

    public void setBlockType(int id)
    {
        blockType = id;
        if(Block.getBlockOf(id) instanceof SpecWeapon)
        {
            tiedItem = (Item)Block.getBlockOf(id);
        }
        else
        {
            tiedItem = null;
        }
        getStackImage(); 
    }
    public void setBlockDamage(int damage)
    {
        blockDamage = damage;
        getStackImage(); 
    }
    
    public static boolean consumeItem(int blockType,int blockDamage)
    {
    	Slot item = containsItem(blockType,blockDamage);
    	if(item!=null)
    	{
    		item.addStackSize(-1);
    		ModeManager.hotBar.refreshBar();
    		return true;
    	}
    	return false;
    }
    public static Slot containsItem(int blockType,int blockDamage)
    {
    	if(blockType==0)
    		return null;
    	for(int t = 31;t>=0;t--)
        {
    		if(Inventory.slots[t].blockType == blockType&&Inventory.slots[t].blockDamage == blockDamage)
    		{
    			return Inventory.slots[t];
    		}
        }
    	if(Inventory.redSlot.blockType== blockType&&Inventory.redSlot.blockDamage == blockDamage)
    	{
			return Inventory.redSlot;
    	}
    	return null;
    }
    
    
    protected void hoverCheck()
    {
        if(isOver())
        {
        	if(isDisplay&&MCWorld.DEVMODE)
        	{
        		if(Greenfoot.isKeyDown("left"))
        		{
        			setLocation(getX()-1,getY());
        		}
        		if(Greenfoot.isKeyDown("up"))
        		{
        			setLocation(getX(),getY()-1);
        		}
        		if(Greenfoot.isKeyDown("down"))
        		{
        			setLocation(getX(),getY()+1);
        		}
        		if(Greenfoot.isKeyDown("right"))
        		{
        			setLocation(getX()+1,getY());
        		}
        		if(isClicked() == "lp")
        		{
        			System.out.println(getX()+":"+getY());
        		}
        		
        	}
        	drawDesc();
            if(hover)
            {

                return;
            }
            GreenfootImage theimage = getImage();
            GreenfootImage thelight =new  GreenfootImage("images\\hoverslot.png");
            thelight.drawImage(getImage(),2,1);
            setImage(thelight);
            hover = true;
            //Item Info Bar
            if(blockType < 1)
            {
                return;
            }
            if(MCWorld.mode != "inventory")
            {
                return;
            }
            GreenfootImage image = new GreenfootImage(310, HEIGHT);
            //image.setColor(new Color(0, 0, 0, 160));
            image.setColor(new Color(255, 255, 255, 100));
            Font font = MCWorld.mcFont;
            font = font.deriveFont(FONT_SIZE/2);
            image.setFont(font);
            image.setColor(Color.WHITE);
            Block b = Block.getBlockOf(blockType,blockDamage);
            if(MCWorld.DEVMODE)
            	image.drawString(b.getItemName()+" "+blockType+" : "+blockDamage, 0, 14);
            else
            	image.drawString(b.getItemName(), 0, 14);
            GreenfootImage finalimage = new  GreenfootImage("images\\moduleinventory.png");
            finalimage.drawImage(image, 10, 102);
            ModeManager.theModule.setImage(finalimage);
            timer = true;
        }
        else
        {
        	clearDesc();
            if(!hover)
            {

                return;
            }
            if(!timer)
            {
                //if(isDisplay)
                //    ModeManager.theInventory.setImage("images\\inventory.png");
            }
            else
            {

            }

            clearLights();
        }

    }

    protected boolean isOver()
    {

        if(MCWorld.mx > leftside + getX()&&MCWorld.mx < rightside + getX())
        {
            if(MCWorld.my > upside + getY()&&MCWorld.my < downside + getY())
            {
                return true;
            }
        }
        return false;
    }

    protected String isClicked()
    {
        if(!isOver())
        {
            return "null";
        }

        return MCWorld.mouseDown;
    }
    protected boolean canStack(int block, int damage)
    {
    	return block == blockType && damage == blockDamage;
    }
    public void drawDesc()
    {
        if(descRect == null)
        {
            GreenfootImage image = new GreenfootImage("images\\itemdesc.png");
            Block b = Block.getBlockOf(blockType,blockDamage);
            if(b == null||MCWorld.mode.equals("inventory"))
            	return;
            Font font = MCWorld.mcFont;
            font = font.deriveFont(8.0f);
            image.setFont(font);
            image.setColor(new Color(255, 255, 255, 255));
            image.drawString(b.getItemName(),5,12);
            descRect = new ImageObj();
            descRect.setImage(image);
            MCWorld.theWorld.addObject(descRect,MCWorld.mx+descRect.getImage().getWidth()/2,MCWorld.my-5); 
        }
        else
        {
            descRect.setLocation(MCWorld.mx+descRect.getImage().getWidth()/2,MCWorld.my-5);
        }
    }
    public void clearDesc()
    {
        if(descRect != null)
        {
            MCWorld.theWorld.removeObject(descRect);
            descRect = null;
        }
    }

	public void onRemove()
	{
		clearDesc();
		
	}
}
