import java.util.Arrays;
import java.util.ArrayList;

/**
 * Write a description of class CraftingManager here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class CraftingManager
{

	static final int NULL = 0, STONE = 1, COBBLESTONE = 2, BRICKS = 3, DIRT = 4, PLANKS = 5, PLATFORM = 6, WOOD = 7, GRASS = 8, SAND = 9, STONEBRICKS = 10, STONEWALL = 11, PLANKWALL = 12,
			DIRTWALL = 13, BRICKWALL = 14, WOODWALL = 15, SEEDS = 16, COAL = 17, STICK = 18, COALORE = 19, IRONORE = 20, GOLDORE = 21, DIAMONDORE = 22, GLASS = 23, MOSSYSTONEBRICKS = 24,
			MOSSYCOBBLESTONE = 25, CRAFTINGBENCH = 26, DOOR = 27, WOODENPICKAXE = 28, WOODENAXE = 29, WOODENSHOVEL = 30, STONEPICKAXE = 31, STONEAXE = 32, STONESHOVEL = 33, WOODENHAMMER = 34,
			STONEHAMMER = 35;

	public static Slot[] crafting = new Slot[5];
	public static Slot[] craftingBench = new Slot[10];
	public static boolean usingFull;
	private static final int craftingY = -7;
	private static final int[] slotX;
	private static final int[] slotY;
	private static final int[] slotBX;
	private static final int[] slotBY;
	// public static final int[][] recipes;
	public static int[][] recipesFull;
	public static ArrayList<SquareRecipe> recipes = new ArrayList();

	public CraftingManager()
	{

	}

	public static void generateCraftingSlots()
	{
		for (int t = 0; t < crafting.length; t++)
		{
			crafting[t] = new Slot(slotX[t] + 174 + 70, slotY[t] + 89 + 106).setAsCrafting();
		}
		crafting[4].setAsProduct();
		generateCraftingBenchSlots();
	}

	public static void generateCraftingBenchSlots()
	{
		for (int t = 0; t < craftingBench.length; t++)
		{
			craftingBench[t] = new Slot(slotBX[t], slotBY[t]).setAsCrafting();
		}
		craftingBench[9].setAsProduct();
	}

	public static void cutStacks()
	{
		if (MCWorld.mode == "craftingbench")
		{
			for (int t = 0; t < 9; t++)
			{
				craftingBench[t].addStackSize(-1);
			}
		} else
		{
			crafting[0].addStackSize(-1);
			crafting[1].addStackSize(-1);
			crafting[2].addStackSize(-1);
			crafting[3].addStackSize(-1);
		}
	}

	public static void refresh()
	{
		boolean full = (Module.getCurrentModule() == "craftingbench");
		int[] placed = new int[9];
		int[] placedDamage = new int[9];
		if (full)
		{
			for (int i = 0; i < 9; i++)
			{
				placed[i] = craftingBench[i].blockType;
				placedDamage[i] = craftingBench[i].blockDamage;
			}
		} else
		{
			placed[3] = crafting[0].blockType;
			placed[4] = crafting[1].blockType;
			placed[6] = crafting[2].blockType;
			placed[7] = crafting[3].blockType;

			placedDamage[3] = crafting[0].blockDamage;
			placedDamage[4] = crafting[1].blockDamage;
			placedDamage[6] = crafting[2].blockDamage;
			placedDamage[7] = crafting[3].blockDamage;
		}
		Slot[] slots = new Slot[9];
		for (int i = 0; i < slots.length; i++)
		{
			if (placed[i] != 0)
			{
				slots[i] = new Slot();
				slots[i].setItem(placed[i], 1, placedDamage[i]);
			}
		}
		Slot[][] placed2 = exfoliate(slots);
		Slot product = SquareRecipe.getProduct(placed2, recipes);
		if (product != null)
		{

			produce(full, product.blockType, product.stackSize, product.blockDamage);
			return;
		}
		if (full)
		{
			craftingBench[9].removeItem();
			usingFull = false;
			return;
		} else
		{
			crafting[4].removeItem();
			usingFull = false;
			return;
		}

	}

	/**
	 * converts a 9 array into a 3x3 array
	 */
	public static Slot[][] exfoliate(Slot[] array)//
	{
		Slot[] a =
		{ array[0], array[1], array[2] };
		Slot[] b =
		{ array[3], array[4], array[5] };
		Slot[] c =
		{ array[6], array[7], array[8] };
		Slot[][] ret =
		{ a, b, c };
		return ret;
	}

	private static void produce(boolean bench, int id, int num, int damage)
	{
		if (bench)
		{
			craftingBench[9].setItem(id, num, damage);
		} else
		{
			crafting[4].setItem(id, num, damage);
		}
	}

	/*
	 * public static final int[] products = {10,6,6,26,18,18,5,70,63}; public
	 * static final int[] productsAmount = {4,2,2,1,4,4,4,4,4}; public static
	 * final int[] productsB =
	 * {28,WOODENSHOVEL,WOODENAXE,STONEPICKAXE,STONESHOVEL
	 * ,STONEAXE,WOODENHAMMER,
	 * STONEHAMMER,PLANKWALL,Block.FURNACE,45,65,76,75,83,66}; public static
	 * final int[] productsAmountB = {1,1,1,1,1,1,1,1,8,1,1,1,1,1,1,1};
	 */
	static
	{
		recipes.add(new SquareRecipe("Planks", 4, ":XWood", "000", "000", "X00"));
		recipes.add(new SquareRecipe("StoneBricks", 4, ":XStone", "000", "XX0", "XX0"));
		recipes.add(new SquareRecipe("Platform", 2, ":XPlanks", "000", "000", "XX0"));
		recipes.add(new SquareRecipe("CraftingBench", 1, ":XPlanks", "000", "XX0", "XX0"));
		recipes.add(new SquareRecipe("Stick", 4, ":XPlanks", "000", "X00", "X00"));
		recipes.add(new SquareRecipe("Stim", 4, ":XIronIngot", ":YGlass", "000", "X00", "Y00"));
		recipes.add(new SquareRecipe("Torch", 4, ":XStick", ":YCoal", "000", "Y00", "X00"));
		recipes.add(new SquareRecipe("CraftingBench", 1, ":XPlanks", "000", "XX0", "XX0"));
		recipes.add(new SquareRecipe("DoorBottom", 1, ":XPlanks", "XX0", "XX0", "XX0"));
		recipes.add(new SquareRecipe("WoodenPickaxe", 1, ":XPlanks", ":YStick", "XXX", "0Y0", "0Y0"));
		recipes.add(new SquareRecipe("WoodenAxe", 1, ":XPlanks", ":YStick", "XX0", "XY0", "0Y0"));
		recipes.add(new SquareRecipe("WoodenAxe", 1, ":XPlanks", ":YStick", "0XX", "0YX", "0Y0"));
		recipes.add(new SquareRecipe("WoodenShovel", 1, ":XPlanks", ":YStick", "0X0", "0Y0", "0Y0"));
		recipes.add(new SquareRecipe("WoodenHammer", 1, ":XPlanks", ":YStick", "XX0", "XX0", "0Y0"));
		recipes.add(new SquareRecipe("WoodenSword", 1, ":XPlanks", ":YStick", "0X0", "0X0", "0Y0"));
		recipes.add(new SquareRecipe("StonePickaxe", 1, ":XCobblestone", ":YStick", "XXX", "0Y0", "0Y0"));
		recipes.add(new SquareRecipe("StoneAxe", 1, ":XCobblestone", ":YStick", "0XX", "0YX", "0Y0"));
		recipes.add(new SquareRecipe("StoneShovel", 1, ":XCobblestone", ":YStick", "0X0", "0Y0", "0Y0"));
		recipes.add(new SquareRecipe("StoneHammer", 1, ":XCobblestone", ":YStick", "XX0", "XX0", "0Y0"));
		recipes.add(new SquareRecipe("StoneSword", 1, ":XCobblestone", ":YStick", "0X0", "0X0", "0Y0"));
		recipes.add(new SquareRecipe("PlankWall", 12, ":XPlanks", "XXX", "XXX", "XXX"));
		recipes.add(new SquareRecipe("Furnace", 1, ":XCobblestone", "XXX", "X0X", "XXX"));
		recipes.add(new SquareRecipe("Anvil", 1, ":XIronIngot", "000", "XXX", "0XX"));
		recipes.add(new SquareRecipe("Bottle", 1, ":XGlass", "000", "X0X", "0X0"));
		recipes.add(new SquareRecipe("StimLab", 1, ":1Bottle", ":2BottleOfWater", ":3Platform", ":4Stick", "121", "333", "404"));
		recipes.add(new SquareRecipe("Chest", 1, ":XPlanks", "XXX", "X0X", "XXX"));
		recipes.add(new SquareRecipe("OpenHearthFurnace", 1, ":XIronIngot", ":YSilverIngot", "X0X", "Y0Y", "XXX"));
		recipes.add(new SquareRecipe("Bow", 1, ":XStick", ":YStringItem", "0XY", "X0Y", "0XY"));
		recipes.add(new SquareRecipe("WoodenStock", 1, ":XPlanks", ":YStick", "000", "XY0", "XX0"));
		recipes.add(new SquareRecipe("Rifle", 1, ":XWoodenStock", ":YRifleBarrel", "000", "XY0", "000"));
		recipes.add(new SquareRecipe("Pistol", 1, ":XIronGrip", ":YPistolBarrel", "000", "Y00", "X00"));
		recipes.add(new SquareRecipe("RubberHose", 1, ":XRubber", "XXX", "000", "XXX"));
		recipes.add(new SquareRecipe("Flamethrower", 1, ":APropane",":XIronGrip", ":YIgnitionNozzle",":ZRubberHose", "000", "ZZY", "A0X"));
		int[] Xs =
		{ 141, 177, 141, 177, 253 };
		int[] Ys =
		{ 36, 36, 72, 72, 56 };
		int[] BXs =
		{ 342, 378, 414, 342, 378, 414, 342, 378, 414, 495 };
		int[] BYs =
		{ 224, 224, 224, 260, 260, 260, 296, 296, 296, 260 };
		slotX = Xs;
		slotY = Ys;
		slotBX = BXs;
		slotBY = BYs;
		/*
		 * int[] rec1 = {1,1,1,1}; int[] rec2 = {5,5,0,0}; int[] rec3 =
		 * {0,0,5,5}; int[] rec4 = {5,5,5,5}; int[] rec5 = {5,0,5,0}; int[] rec6
		 * = {0,5,0,5}; int[] rec7 = {0,0,WOOD,0}; int[] rec10 = {0,37,0,23};
		 * int[] rec12 = {17,0,18,0}; int[][] recTotes =
		 * {rec1,rec2,rec3,rec4,rec5,rec6,rec7,rec10,rec12}; int[] recB1 =
		 * {0,1,0,0,1,0,0,1,1}; int[] recB2 = {5,5,5,0,18,0,0,18,0}; int[] recB3
		 * = {0,5,0,0,18,0,0,18,0}; int[] recB4 = {5,5,0,5,18,0,0,18,0}; int[]
		 * recB5 = {2,2,2,0,18,0,0,18,0}; int[] recB6 = {0,2,0,0,18,0,0,18,0};
		 * int[] recB7 = {2,2,0,2,18,0,0,18,0}; int[] recB8 =
		 * {5,5,0,5,5,0,0,18,0}; int[] recB9 = {2,2,0,2,2,0,0,18,0}; int[]
		 * recB10 = {5,5,0,5,5,0,5,5,0}; int[] recB11 = {2,2,2,2,0,2,2,2,2};
		 * int[] recB12 = {0,0,0,37,37,37,0,37,37}; int[] recB13 =
		 * {0,5,0,0,5,0,0,18,0}; int[] recB14 = {0,0,0,23,0,23,0,23,0}; int[]
		 * recB15 = {76,77,76,6,6,6,18,0,18}; int[] recB16 =
		 * {PLANKS,PLANKS,PLANKS,PLANKS,0,PLANKS,PLANKS,PLANKS,PLANKS}; int[]
		 * recB17 = {0,2,0,0,2,0,0,18,0}; int[][] recBTotes =
		 * {recB2,recB3,recB4,
		 * recB5,recB6,recB7,recB8,recB9,recB10,recB11,recB12,
		 * recB13,recB14,recB15,recB16,recB17}; recipes = recTotes; recipesFull
		 * = recBTotes;
		 */
	}

}
