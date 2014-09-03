import java.util.Arrays;
import java.util.ArrayList;
/**
 * Write a description of class FurnaceManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FurnaceManager  
{
    static final int NULL = 0, STONE = 1, COBBLESTONE = 2,BRICKS = 3, DIRT = 4, PLANKS = 5, PLATFORM = 6,WOOD = 7, GRASS = 8, SAND = 9, STONEBRICKS = 10, STONEWALL = 11, PLANKWALL = 12, DIRTWALL = 13,
    BRICKWALL = 14, WOODWALL = 15, SEEDS = 16, COAL = 17, STICK = 18, COALORE = 19, IRONORE = 20, GOLDORE = 21, DIAMONDORE = 22, GLASS = 23, MOSSYSTONEBRICKS = 24, MOSSYCOBBLESTONE = 25,
    CRAFTINGBENCH = 26, DOOR = 27, WOODENPICKAXE = 28,WOODENAXE = 29,WOODENSHOVEL = 30,STONEPICKAXE = 31,STONEAXE = 32,STONESHOVEL = 33,WOODENHAMMER = 34, STONEHAMMER = 35,FURNACE = 36,IRONINGOT = 37, 
    BASALT = 38, BASALTWALL = 39, BASALTCOBBLESTONE = 40;

    public static Slot[] crafting = new Slot[5];
    public static final int[] slotX;
    public static final int[] slotY;
    public static final int[] slotBX;
    public static final int[] slotBY;
    public static final int[] fuels = new int[Block.classIDs.length];

    //public static final int[][] recipes;
    public static ArrayList<SquareRecipe> recipes = new ArrayList();
    public static ArrayList<SquareRecipe> OHrecipes = new ArrayList<SquareRecipe>();
    public static Slot getResult(Furnace furnace)
    {

        Slot[][] placed = new Slot[1][3];
        for(int i = 0;i<3;i++)
        {
        	if(furnace.slots[i+1].blockType!= 0)
        		placed[0][i] =  new Slot().setItem(furnace.slots[i+1].blockType, 1, furnace.slots[i+1].blockDamage);
        }
        Slot result = SquareRecipe.getProduct(placed,recipes);
        return result;
    }

    public static int[] getResult(OpenHearthFurnace furnace)
    {
        int[] result = new int[2];
        Slot placed = new Slot().setItem(furnace.slots[1].blockType,1);
        Slot lime = new Slot().setItem(furnace.slots[4].blockType,1);
        
        
        if(furnace.slots[4].blockType != Block.LIMESTONE)
        {
            return null;
        }
        Slot result1 = SquareRecipe.getProduct(new Slot[][]{{placed,lime}},OHrecipes);
        if(result1 == null)
        	return null;
        
        
        return new int[]{result1.blockType,result1.stackSize};
    }

    public static void refresh()
    {
        //int[] placed = {crafting[1].blockType,crafting[2].blockType,crafting[3].blockType};
    }

    public static int checkFuel(int id)
    {
        return fuels[id];
    }

    public static void generateFurnaceSlots()
    {
        for(int t = 0;t<crafting.length;t++)
        {
            crafting[t] = new Slot(slotX[t]+224,slotY[t]+164).setAsCrafting();
        }
        crafting[4].setAsProduct();

    }
    /*
    public static final int[] products = {COAL,37,STONE,BASALT,Block.SILVERINGOT,Block.GLASS,73,Block.getBlockClass("StrangeSeeds")};
    public static final int[] productsAmount = {1,1,2,3,1,3,1,1};
    public static final int[] productsB = {Block.STEELINGOT};
    public static final int[] productsAmountB = {1};
    public static final int[] recipesB = { Block.IRONINGOT};
     */
    static{
        recipes.add(new SquareRecipe("Coal",1,":XWood","00X"));
        recipes.add(new SquareRecipe("IronIngot",1,":XIronOre","0XX"));
        recipes.add(new SquareRecipe("Stone",2,":XCobblestone","XXX"));
        recipes.add(new SquareRecipe("Basalt",3,":XBasaltCobblestone","0XX"));
        recipes.add(new SquareRecipe("SilverIngot",1,":XBasaltSilverOre","00X"));
        recipes.add(new SquareRecipe("Glass",3,":XSand",":YLimestone","XYX"));
        recipes.add(new SquareRecipe("LowBase",1,":XBottleOfWater","00X"));
        recipes.add(new SquareRecipe("StrangeSeeds",1,":XLeaves",":YSeeds","XYX"));
        recipes.add(new SquareRecipe("StringItem",1,":XFlax",":YBottleOfWater","XYX"));
        OHrecipes.add(new SquareRecipe("SteelIngot",1,":XIronIngot",":YLimestone","XY"));
        OHrecipes.add(new SquareRecipe("Rubber",1,":XWood",":YLimestone","XY"));
        OHrecipes.add(new SquareRecipe("Propane",8,":XGunpowder",":YLimestone","XY"));
        int[] Xs = {215,179,215,251,324};
        int[] Ys = {107,37,37,37,74};
        int[] BXs = {381,362,491,491,398};
        int[] BYs = {291,221,241,277,221};
        slotX = Xs;
        slotY = Ys;
        slotBX = BXs;
        slotBY = BYs;
        {
            fuels[COAL] = 800;
        }
        /*
        int[] rec1 = {0,WOOD,0};
        int[] rec2 = {IRONORE,IRONORE,0};
        int[] rec3 = {COBBLESTONE,COBBLESTONE,0};
        int[] rec4 = {BASALTCOBBLESTONE,BASALTCOBBLESTONE,BASALTCOBBLESTONE};
        int[] rec5 = {0,Block.BASALTSILVERORE,0};
        int[] rec6 = {SAND,53,SAND};
        int[] rec7 = {0,77,0};
        int[] rec8 = {Block.getBlockClass("Leaves"),Block.getBlockClass("Seeds"),Block.getBlockClass("Leaves")};
        int[][] recTotes = {rec1,rec2,rec3,rec4,rec5,rec6,rec7,rec8};
        recipes = recTotes;
         */
    }
}
