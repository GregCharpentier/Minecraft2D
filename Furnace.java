import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Furnace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Furnace extends Block
{
    public Slot[] slots;
    public boolean burning;
    private int fuel;
    private int fuelMax;
    private int progress;
    private int progressMax;
    @Override
    protected void onRightClick(int id)
    {
        ModeManager.createInventoryMenu("furnace");       
    } 
    public String getItemName()
    {
        return "Furnace";
    }
    public void act() 
    {   
        onFrameActions();
        if(!MCWorld.paused)
        {
            onFrameFuelCheck();
        }
    }   

    private void onFrameFuelCheck()
    {
        if(!burning)
        {
            int f = FurnaceManager.checkFuel(slots[0].blockType);
            if(f != 0)
            {
                if(canBurn())
                {
                    slots[0].addStackSize(-1);
                    setFuel(f);
                    fuelMax = f;
                    setBurning();
                }
            }

        }
        else
        {
            if(fuel == 0)
            {
                setOff();
                return;
            } 
            setBurning();
            fuel--;
            if(canBurn())
            {
                
                progress++;
                if(progress == 100)
                {
                    Slot result = FurnaceManager.getResult(this);
                    if(result!= null)
                    {
                        if(slots[4].stackSize > 0)
                        {
                            slots[4].addStackSize(result.stackSize);
                        }
                        else
                        {
                            slots[4].setItem(result.blockType,result.stackSize,result.blockDamage);
                        }
                        cutStacks();
                    }
                    progress = 0;
                }
            }
            else
            {
                progress = 0;
            }
        }
    }

    public boolean canBurn()
    {
        Slot result = FurnaceManager.getResult(this);
        if(result == null)
        {
            return false;
        }
        if(slots[4].blockType == 0)
        {
            return true;
        }
        if(slots[4].blockType == result.blockType&&slots[4].blockDamage == result.blockDamage)
        {
            if((result.stackSize + slots[4].stackSize) > Block.maxStackSize(result.blockType))
            {
                return false;
            }
            return true;
        }
        return false;
    }
    
    private void cutStacks()
    {
        slots[1].addStackSize(-1);
        slots[2].addStackSize(-1);
        slots[3].addStackSize(-1);
    }
    

    public Furnace()
    {
        id = 36;
        slots = generateSlots();
    }

    public void setBurning()
    {
        setImage("images\\blocks\\Furnace2.png");
        burning = true;
        refreshLight(posToArray(getX(),getY())[0],posToArray(getX(),getY())[1],true,true);
        setImage("images\\blocks\\Furnace2.png");
    }

    public void setFuel(int f)
    {
        fuel = f;
    }

    public int getFuel()
    {
        return (fuel*100); 
    }

    public int getFuelPercent()
    {
        int fuel = getFuel();
        return fuel/fuelMax; 
    }
   
    public int getProgress()
    {
        return progress;
    }

    public void refresh()
    {
        System.out.println("Refreshed.");
    }

    public void setOff()
    {
        setImage("images\\blocks\\Furnace.png");
        burning = false;
        refreshLight(posToArray(getX(),getY())[0],posToArray(getX(),getY())[1],true,true);
    }
    public boolean isLight()
    {
        
        return burning;
    }
    public int lightLevel()
    {
        return 7;
    }

    private static Slot[] generateSlots()
    {
        Slot[] theslots = new Slot[5];
        for(int t = 0;t<5;t++)
        {
            theslots[t] = new Slot(FurnaceManager.slotX[t]+199,FurnaceManager.slotY[t]+184).setAsCrafting();
        }
        theslots[4].setAsProduct();
        return theslots;
    }
    
    

}
