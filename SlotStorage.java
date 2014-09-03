import greenfoot.*;
/**
 * Write a description of class SlotStorage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SlotStorage  
{
    private final int[] sizes;
    private final int[] types;
    private final int[] damages;
    private final int size;
    

    public SlotStorage(int size)
    {
        this.size = size;
        sizes = new int[size];
        types = new int[size];
        damages = new int[size];
    }
    public SlotStorage(int[] parSizes, int[] parTypes,int[] parDamages)
    {
        if(parSizes.length != parTypes.length)
        {
            System.out.println("Error with SlotStorage, check yourself fool");
            Greenfoot.stop();
            
        }
        size = parSizes.length;
        sizes = parSizes;
        types = parTypes;
        damages = parDamages;
        
    }
    public SlotStorage(int[] parSizes, int[] parTypes)
    {
        if(parSizes.length != parTypes.length)
        {
            System.out.println("Error with SlotStorage, check yourself fool");
            Greenfoot.stop();
            
        }
        size = parSizes.length;
        sizes = parSizes;
        types = parTypes;
        damages = new int[size];
        
    }
    
    public int[] getSizes()
    {
       return sizes; 
    }
    public int[] getTypes()
    {
       return types; 
    }
    public int[] getDamages()
    {
       return types; 
    }
    public int getSizeAt(int index)
    {
       return sizes[index]; 
    }
    public int getTypeAt(int index)
    {
       return types[index]; 
    }
    public int getDamageAt(int index)
    {
       return damages[index]; 
    }
    public int getSize()
    {
       return size; 
    }
    public static SlotStorage condenseInventory()
    {
        int[] types = new int[33];
        int[] sizes = new int[33];
        int[] damages = new int[33];
        for(int t = 0; t<32; t++)
        {
            types[t] = Inventory.slots[t].blockType;
            sizes[t] = Inventory.slots[t].stackSize;
            damages[t] = Inventory.slots[t].blockDamage;
        }
        
        types[32] = Inventory.redSlot.blockType;
        sizes[32] = Inventory.redSlot.stackSize;
        damages[32] = Inventory.redSlot.blockDamage;
        
        return new SlotStorage(sizes,types,damages);
    }
    public String getString()
    {
        StringBuilder str = new StringBuilder("");
        for(int t = 0; t < size; t++)
        {
        	if(damages[t]!=0)
        		str.append(types[t]+","+sizes[t]+","+damages[t]+";");
        	else
        		str.append(types[t]+","+sizes[t]+";");
        }
        return str.toString();
    }
}
