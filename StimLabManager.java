/**
 * Write a description of class StimLabManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StimLabManager  
{

    public static Slot[] crafting = new Slot[4];
    private static int[] slotX = {355,399,443,399};
    private static int[] slotY = {289,289,289,213};

    public static void generateStimLabSlots()
    {
        for(int t = 0;t<crafting.length;t++)
        {
            crafting[t] = new Slot(slotX[t],slotY[t]).setAsCrafting();
        }
        crafting[3].setAsProduct();

    }

    public static void refresh()
    {
        if(checkCrafting())
        {

        }
        else
        {
            crafting[3].removeItem();
        }
    }

    public static boolean checkCrafting()
    {
        System.out.println("StimLabRefresh");
        int[] placed = {crafting[0].blockType,crafting[1].blockType,crafting[2].blockType};
        System.out.println(placed[0]+" "+placed[1]+ " "+placed[2]);
        if(placed[1] != needle)
        {
            return false ;
        }
        int baseID = -1;
        int ingredientID = -1;
        for(int i = 0;i<bases.length;i++)
        {
            if(placed[2] == bases[i])
            {
                baseID = i;
                break;
            }
        }
        for(int i = 0;i<ingredients.length;i++)
        {
            if(placed[0] == ingredients[i])
            {
                ingredientID = i;
                break;
            }
        }
        System.out.println(baseID+" "+ingredientID);
        if(baseID == -1||ingredientID==-1)
        {
            return false;
        }
        produce(new Slot().setItem(products[ingredientID], 1, baseID+1));
        return true;
    }

    public static void cutStacks()
    {
        for(int t = 0;t<=2;t++)
        {
            crafting[t].addStackSize(-1);
        }
        Slot.addItems(76,1);
    }
    private Slot s(int type,int damage)
    {
    	return new Slot().setItem(type, 1,damage);
    }
    private static void produce(Slot item)
    {                
        crafting[3].setItem(item.blockType,1,item.blockDamage);   
    }
    private static int needle = 70;
    private static int[] ingredients = {72};
    private static int[] bases = {73,74};
    private static int[] products = {71};
}
