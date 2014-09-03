import java.util.ArrayList;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class OpenHearthFurnace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OpenHearthFurnace extends Block
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
        ModeManager.createInventoryMenu("openfurnace");       
    } 
    public String getItemName()
    {
        return "Open Hearth Furnace";
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
                f/=4;
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
            fuel--;
            if(canBurn())
            {

                progress++;
                if(progress == 100)
                {
                    int[] result = FurnaceManager.getResult(this);
                    if(result!= null)
                    {
                        if(slots[3].stackSize > 0)
                        {
                            slots[3].addStackSize(result[1]);
                        }
                        else
                        {
                            slots[3].setItem(result[0],result[1]);
                        }
                        if(slots[2].stackSize > 0)
                        {
                            slots[2].addStackSize(1);
                        }
                        else
                        {
                            slots[2].setItem(51,1);
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
        int[] result = FurnaceManager.getResult(this);
        if(result == null)
        {
            return false;
        }
        if(slots[3].blockType == 0)
        {
            return true;
        }
        if(slots[3].blockType == result[0])
        {
            if((result[1] + slots[3].stackSize) > Block.maxStackSize(result[0]))
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
    }

    public OpenHearthFurnace()
    {
        id = 52;
        slots = generateSlots();
    }

    public void setBurning()
    {
        setImage("images\\blocks\\Open Hearth Furnace2.png");
        burning = true;
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
        setImage("images\\blocks\\Open Hearth Furnace.png");
        burning = false;
    }

    private static Slot[] generateSlots()
    {
        Slot[] theslots = new Slot[5];
        for(int t = 0;t<theslots.length;t++)
        {
            theslots[t] = new Slot(FurnaceManager.slotBX[t],FurnaceManager.slotBY[t]).setAsCrafting();
        }
        theslots[3].setAsProduct();
        theslots[2].setAsProduct();
        return theslots;
    }  
    public int[] boundingBox(int[] startingpoint)
    {
        int[] box = new int[5];
        box[0] = startingpoint[0];
        box[1] = startingpoint[0]+64;
        box[2] = startingpoint[1];
        box[3] = startingpoint[1]+64;
        box[4] = 5;
        return box;
    }
}
