import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RangedWeapon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class SpecWeapon extends Item
{
    protected int charge;
    protected int cooldown;

    public void onHeld()//called from block, every frame that it is held
    {
    	if(ModeManager.creative)
    		return;
        if(cooldown>0)
            cooldown--;
        if(!ammoCheck()&&!ModeManager.creative)
        {
        	charge = 0;
        	return;
        }
        if(MouseHelper.buttonsDown != 3)//right mouse not pressed
        {
            if(charge!=0)
            {
                if(charge>minCharge())
                {
                    fire();
                }
                charge = 0;
            } 
        }

        if(MouseHelper.buttonsDown == 3)
        {
            if(maxCharge() == 0)
            {
                if(cooldown == 0)
                {
                    
                    fire();
                    cooldown = cooldownTime();
                }
            }
            else
            {
                if(cooldown <= 0)
                {
                    if(charge<maxCharge())
                    {
                        charge++;
                    }
                }

            } 
        }

    }

    protected boolean ammoCheck()
	{
    	if(ammoItem()==null)
    		return true;
		return Slot.containsItem(ammoItem().blockType, ammoItem().blockDamage)!= null;
	}
    protected boolean consumeAmmo()
   	{
    	if(ammoItem()==null)
    		return true;
    	return Slot.consumeItem(ammoItem().blockType, ammoItem().blockDamage);
   	}
    public Slot ammoItem()
    {
    	return  null;
    }

	public abstract void fire();

    /**
     * Max charge for charged weapons, 0 if not a charged weapon
     */
    public int maxCharge()
    {
        return 0;
    }

    /**
     * If a charged weapon is released with a charge less than this, it is not fired
     */
    public int minCharge()
    {
        return 0;
    }

    public abstract int cooldownTime();
    public double getChargePercent()
    {   
        return charge/(double)maxCharge();
    }
    
    public String getBlockImageName()
    {
        return "spec\\"+getItemName();
    }
    @Override
    public int maxStackSize()
    {
    	return 1;
    }
    
   
}
