import greenfoot.*; 

/**
 * Write a description of class Chest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Chest extends Block
{
    private SlotStorage slotStorage;//Long term storage
    private Slot[] slots;//Should be null except when active
    private static final int capacity = 23;
    public String material()
    {
        return "dirt";
    }
    public void save()
    {
        int[] ids = new int[slots.length];
        int[] amo = new int[slots.length];
        int[] dams = new int[slots.length];
        for(int i = 0;i<slots.length;i++)
        {
            ids[i] =slots[i].blockType;
            amo[i] =slots[i].stackSize;
            dams[i] = slots[i].blockDamage;
        }
        slotStorage = new SlotStorage(amo,ids,dams);
        slots = null;
    }
    public String getItemName()
    {
        return "Chest";
    }
    public Slot[] load(int xx, int yy)
    {
        if(slots!= null)
        {
            System.out.println("You loaded a chest twice?");
        }
        slots = new Slot[capacity];
        int begX = xx-144;
        int begY = yy-30;
        int dist = 36;
        
        for(int i = 0;i<slots.length;i++)
        {
            int x;
            int y;
            if(i<=4)
            {
                x = begX+dist*i;
                y=begY;
            }
            else if(i<=13)
            {
                x= begX+dist*(i-5);
                y=begY+dist;
            }
            else
            {
                x= begX+dist*(i-14);
                y=begY+dist*2;
            }
            slots[i] = new Slot(x,y);
            slots[i].setItem(slotStorage.getTypeAt(i),slotStorage.getSizeAt(i),slotStorage.getDamageAt(i));
        }
        return slots;
        
    }
    public Chest()
    {
        slotStorage=new SlotStorage(capacity);
        id = 83;
    }
    public static void SetChestContentsFromString(String loadString)
    {
        System.out.println("Ay:"+loadString);
        int[] nums = SaveLoad.loadNumberArray(loadString);
        if(nums.length<50)
        {
        	int[] sizes = new int[capacity];
            int[] types = new int[capacity];
            for(int i=2;i<nums.length-1;i+=2)
            {
                sizes[i/2-1]=nums[i];
                types[i/2-1]=nums[i+1];
            }
           ((Chest)Block.blocks[nums[0]][nums[1]]).slotStorage=new SlotStorage(sizes,types);
        }
        else
        {
        	
        	int[] sizes = new int[capacity];
            int[] types = new int[capacity];
            int[] damages = new int[capacity];
            for(int i=2;i<nums.length-1;i+=3)
            {
                sizes[i/3]=nums[i];
                types[i/3]=nums[i+1];
                damages[i/3]=nums[i+2];
            }
           ((Chest)Block.blocks[nums[0]][nums[1]]).slotStorage=new SlotStorage(sizes,types,damages);
        }
        
    }
     @Override
    protected void onRightClick(int id)
    {
        ModeManager.createInventoryMenu("chest");       
    } 
    public void act() 
    {        
        onFrameActions();
    }
    public String toString()
    {
        if(slotStorage == null)
        {
            System.out.println("For some reason, a chest has not been closed before saving.");
            return "";
        }
        String string = "[";
        string+=(x+32)/64+";";
        string+=(y+32)/64+";";
        for(int i = 0;i<capacity;i++)
        {
        	string+=slotStorage.getSizeAt(i)+";";
        	string+=slotStorage.getTypeAt(i)+";";
        	string+=slotStorage.getDamageAt(i)+";";
        }
        string+=slotStorage.toString();
        string+="]";
        return string;
        
       
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
