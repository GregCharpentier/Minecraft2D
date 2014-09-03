import greenfoot.*;
import java.util.Arrays;
import java.util.ArrayList;
/**
 * Write a description of class AnvilManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AnvilManager  
{
    public static Slot[] anvil = new Slot[11];
    //public  static final int[][][] recipes;
    public static ArrayList<SquareRecipe> recipes = new ArrayList<SquareRecipe>();
    /**
     * Constructor for objects of class AnvilManager
     */
    public AnvilManager()
    {
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public static void generateAnvilSlots()
    {
        for(int t = 0;t<anvil.length;t++)
        {
            anvil[t] = new Slot(Xs[t]+50,Ys[t]-1).setAsCrafting();
        }

        anvil[9].setAsProduct();
    }

    public static void cutStacks()
    {
        for(int t = 0;t<9;t++)
        {
            anvil[t].addStackSize(-1);
        }
    }

    public static void refresh()
    {
        int[] placedB = {anvil[0].blockType,anvil[1].blockType,anvil[2].blockType,anvil[3].blockType,anvil[4].blockType,
                anvil[5].blockType,anvil[6].blockType,anvil[7].blockType,anvil[8].blockType};
        
        int[] placedBDamage = {anvil[0].blockDamage,anvil[1].blockDamage,anvil[2].blockDamage,anvil[3].blockDamage,anvil[4].blockDamage,
                anvil[5].blockDamage,anvil[6].blockDamage,anvil[7].blockDamage,anvil[8].blockDamage};
        Slot[] placedSlots = new Slot[placedB.length];
        for(int i = 0;i<placedB.length;i++)
        {
        	if(placedB[i]!=0)
        		placedSlots[i] = new Slot().setItem(placedB[i], 1, placedBDamage[i]);
        }
        Slot[][] placed = CraftingManager.exfoliate(placedSlots);
        int[] ham = {anvil[10].blockType};
        
        Slot product = SquareRecipe.getProductWithExtras(placed,ham,recipes);
        if(product!=null)
        {
            anvil[9].setItem( product.blockType,product.stackSize,product.blockDamage);
            return;
        }      
        anvil[9].removeItem();
    }
    public static int getItem(int loc1,int loc2)
    {
        int[][] items = new int[20][20];
        items[0][0] = 46;//iron
        items[0][1] = 47;
        items[0][2] = 48;
        items[0][3] = 49;
        items[0][4] = 67;

        items[1][0] = 59;//silver
        items[1][1] = 60;
        items[1][2] = 61;
        items[1][3] = 62;
        items[1][4] = 68;

        items[2][0] = 55;//steel
        items[2][1] = 56;
        items[2][2] = 57;
        items[2][3] = 58;
        items[2][4] = 69;

        if(items[loc1][loc2] == 0)
        {
            System.out.println("Derp. Anvil recipe error, shutting down.");
            Greenfoot.stop();
            return 0;    
        }
        else
        {
            return items[loc1][loc2];  
        }
    }

    private static void addRecipe(String material,String name,String hammer)
    {
        int ham = Block.getBlockClass(hammer+"Hammer");
        recipes.add((new SquareRecipe(name+"Pickaxe",1,":XStick",":Y"+material,"YYY","0X0","0X0")).setExtras(ham));
        recipes.add((new SquareRecipe(name+"Axe",1,":XStick",":Y"+material,"YY0","YX0","0X0")).setExtras(ham));
        recipes.add((new SquareRecipe(name+"Axe",1,":XStick",":Y"+material,"0YY","0XY","0X0")).setExtras(ham));
        recipes.add((new SquareRecipe(name+"Shovel",1,":XStick",":Y"+material,"0Y0","0X0","0X0")).setExtras(ham));
        recipes.add((new SquareRecipe(name+"Hammer",1,":XStick",":Y"+material,"YY0","YY0","0X0")).setExtras(ham));
        recipes.add((new SquareRecipe(name+"Sword",1,":XStick",":Y"+material,"0Y0","0Y0","0X0")).setExtras(ham));
    }

    static{
        String[] materials = {"IronIngot","SilverIngot","SteelIngot"};
        String[] names = {"Iron","Silver","Steel"};
        String[] hammer = {"Stone","Iron","Silver"};
        recipes.add((new SquareRecipe("BulletItem",6,":XIronIngot","000","0X0","000")).setExtras(Block.getBlockClass("StoneHammer")));
        recipes.add((new SquareRecipe("RifleBarrel", 1, ":XIronIngot", "000", "XXX", "000")).setExtras(Block.getBlockClass("StoneHammer")));
        recipes.add((new SquareRecipe("IronGrip", 1, ":XIronIngot", "000", "XX0", "X00")).setExtras(Block.getBlockClass("StoneHammer")));
		recipes.add((new SquareRecipe("PistolBarrel", 1, ":XIronIngot", ":YSilverIngot", "000", "0XY", "000")).setExtras(Block.getBlockClass("IronHammer")));
		recipes.add((new SquareRecipe("IgnitionNozzle", 1, ":XSteelIngot", ":YCoal", "X00", "Y00", "X00")).setExtras(Block.getBlockClass("SilverHammer")));
        for(int i=0;i<materials.length;i++)
        {
            addRecipe(materials[i],names[i],hammer[i]);;
        }
        
        /*
        int[][] iron = getMaterialRecipes(37,35);
        //int[][] silver = getMaterialRecipes(37,35);
        int[][] silver = getMaterialRecipes(44,49);
        int[][] steel = getMaterialRecipes(50,62);
        int[][][] recTotes = {iron,silver,steel};
        recipes = recTotes;
         */
    }

    private final static int[] Xs = {342,378,414,342,378,414,342,378,414,495,306};
    private final static int[] Ys = {224,224,224,260,260,260,296,296,296,260,260};
}
