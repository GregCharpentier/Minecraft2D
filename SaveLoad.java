import java.io.*;
import java.net.URLDecoder;

/**
 * Write a description of class SaveLoad here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class SaveLoad
{
	// instance variables - replace the example below with your own
	private int x;
	private final static boolean ZIPPED = false;

	/**
	 * Constructor for objects of class SaveLoad
	 */
	public SaveLoad()
	{
	}

	public static boolean checkIfFilesExist()
	{
		String block1 = MCWorld.filePath + "saves\\slot1";
		String wall1 = MCWorld.filePath + "saves\\slot1wall";
		String light1 = MCWorld.filePath + "saves\\slot1light";
		String inv1 = MCWorld.filePath + "saves\\slot1inv";
		String misc1 = MCWorld.filePath + "saves\\slot1misc";
		String mob1 = MCWorld.filePath + "saves\\slot1mob";
		String chest1 = MCWorld.filePath + "saves\\slot1chest";
		String ach1 = MCWorld.filePath + "saves\\slot1ach";

		if (!ReadWrite.checkExists(block1))
			return false;
		if (!ReadWrite.checkExists(wall1))
			return false;
		if (!ReadWrite.checkExists(light1))
			return false;
		if (!ReadWrite.checkExists(inv1))
			return false;
		if (!ReadWrite.checkExists(misc1))
			return false;
		if (!ReadWrite.checkExists(chest1))
			return false;
		if (!ReadWrite.checkExists(ach1))
		{

		}

		return true;
	}

	public static void saveRes(int x, int y)
	{
		ReadWrite.write("resources\\settings", x + "," + y);
	}

	public static void save()
	{
		String block1 = MCWorld.filePath + "saves\\slot1";
		String wall1 = MCWorld.filePath + "saves\\slot1wall";
		String light1 = MCWorld.filePath + "saves\\slot1light";
		String inv1 = MCWorld.filePath + "saves\\slot1inv";
		String misc1 = MCWorld.filePath + "saves\\slot1misc";
		String mob1 = MCWorld.filePath + "saves\\slot1mob";
		String chest1 = MCWorld.filePath + "saves\\slot1chest";
		String ach1 = MCWorld.filePath + "saves\\slot1ach";

		StringBuilder bcont = new StringBuilder("");// block contents
		StringBuilder wcont = new StringBuilder("");// wall contents
		StringBuilder lcont = new StringBuilder("");// light contents
		StringBuilder mcont = new StringBuilder("");// misc contents
		StringBuilder ccont = new StringBuilder("");// chest contents
		StringBuilder acont = new StringBuilder("");// achievement contents
		for (int t = 0; t < 1024; t++)
		{
			for (int g = 0; g < 1024; g++)
			{
				if (Block.blocks[t][g] == null)
				{
					bcont.append("0;");

				}
				else
				{
					if (Block.blocks[t][g].id == 83)
					{
						ccont.append(Block.blocks[t][g].toString());
					}
					if(Block.blocks[t][g].id == 27&&((DoorBottom)Block.blocks[t][g]).top)
					{
						bcont.append("0;");
					}
					else
					{
						if(Block.blocks[t][g].getDamage()!=0)
							bcont.append(Block.blocks[t][g].id +","+Block.blocks[t][g].getDamage()+ ";");
						else
							bcont.append(Block.blocks[t][g].id + ";");
					}
					
				}
				if (Block.walls[t][g] == null)
				{
					wcont.append("0;");

				}
				else
				{
					if(Block.walls[t][g].getDamage()!=0)
						wcont.append(Block.walls[t][g].id+","+Block.walls[t][g].getDamage()+ ";");
					else
						wcont.append(Block.walls[t][g].id + ";");
				}
				lcont.append(Block.light[t][g] + ";");
			}
		}
		mcont.append(Block.offSetX + ";" + Block.offSetY + ";");
		if (ModeManager.creative)
		{
			mcont.append(1 + ";");
		} else
		{
			mcont.append(0 + ";");
		}
		mcont.append(PointCounter.points + ";" + SkillBar.mineSpeed.getLevel()
				+ ";" + SkillBar.moveSpeed.getLevel() + ";"
				+ SkillBar.damage.getLevel() + ";" + SkillBar.health.getLevel()
				+ ";");
		for (Achievement a : AchievementManager.achievements)
		{
			if (a.achieved)
			{
				acont.append("1;");
			} else
			{
				acont.append("0;");
			}
		}
		ReadWrite.write(block1, bcont.toString());
		ReadWrite.write(wall1, wcont.toString());
		ReadWrite.write(light1, lcont.toString());
		ReadWrite.write(inv1, SlotStorage.condenseInventory().getString());
		ReadWrite.write(misc1, mcont.toString());
		ReadWrite.write(chest1, ccont.toString());
		ReadWrite.write(ach1, acont.toString());
	}

	public static void load()
	{
		TextBox box = new TextBox("Loading from file...");
		MCWorld.theWorld.repaint();
		String block1 = MCWorld.filePath + "saves\\slot1";
		String wall1 = MCWorld.filePath + "saves\\slot1wall";
		String light1 = MCWorld.filePath + "saves\\slot1light";
		String inv1 = MCWorld.filePath + "saves\\slot1inv";
		String misc1 = MCWorld.filePath + "saves\\slot1misc";
		String chest1 = MCWorld.filePath + "saves\\slot1chest";
		String ach1 = MCWorld.filePath + "saves\\slot1ach";

		String blocks = ReadWrite.read(block1);
		String walls = ReadWrite.read(wall1);
		String Lights = ReadWrite.read(light1);
		String inv = ReadWrite.read(inv1);
		String misc = ReadWrite.read(misc1);
		String chest = ReadWrite.read(chest1);
		String ach = ReadWrite.read(ach1);
		int x = 0;
		int y = 0;
		int pos = 0;
		box.edit("Loading lighting...");
		MCWorld.theWorld.repaint();
		StringBuilder string = new StringBuilder("");
		int[] lights = loadNumberArray(Lights);
		for (int t = 0; t < 1024; t++)
		{
			for (int g = 0; g < 1024; g++)
			{
				Block.light[t][g] = lights[pos];
				pos++;
			}

		}
		pos = 0;
		box.edit("Loading blocks...");
		MCWorld.theWorld.repaint();
		StringNumberArray Blocks = new StringNumberArray(blocks);
		for (int t = 0; t < 1024; t++)
		{
			for (int g = 0; g < 1024; g++)
			{
				if (Blocks.getMain(pos) > 0)
				{
					System.out.println(Blocks.getMain(pos));
					if(Blocks.getMain(pos)==27&&g>0)
					{
						
						//if(Block.getBlockAtArray(t, g-1)!= null&&Block.getBlockAtArray(t, g-1).id == 27)
						{
							Block toPlace = Block.getBlockOf(Blocks.getMain(pos));
							Block.placeAtArrayDry(t, g, toPlace);
							toPlace.onPlace(t, g);
							toPlace.setDamage(Blocks.getAux(pos));
						}
					}
					else
					{
						Block toPlace = Block.getBlockOf(Blocks.getMain(pos));
						Block.placeAtArrayDry(t, g, toPlace);
						toPlace.setDamage(Blocks.getAux(pos));
					}
					

				}
				pos++;
			}

		}
		string = new StringBuilder("");
		box.edit("Loading chests...");
		for (int t = 0; t < chest.length(); t++)
		{
			if (chest.charAt(t) == '[')
			{
				string = new StringBuilder("");
			} else if (chest.charAt(t) == ']')
			{
				Chest.SetChestContentsFromString(string.toString());
			} else
			{
				string.append(chest.charAt(t));
			}

		}

		x = 0;
		y = 0;
		pos = 0;
		string = new StringBuilder("");
		box.edit("Loading walls...");
		MCWorld.theWorld.repaint();
		StringNumberArray Walls = new StringNumberArray(walls);
		for (x = 0; x < 1024; x++)
		{
			for (y = 0; y < 1024; y++)
			{
				if (Walls.getMain(pos) > 0)
				{
					Block toPlace = Block.getBlockOf(Walls.getMain(pos));
					Block.placeWallArrayDry(x, y, (Wall) toPlace);
					toPlace.setDamage(Walls.getAux(pos));
				}

				pos++;
			}
		}
		pos = 0;
		int invpos = 0;
		boolean size = false;
		string = new StringBuilder("");
		box.edit("Loading inventory data...");
		MCWorld.theWorld.repaint();
		StringNumberArray Inv = new StringNumberArray(inv);
		for (int i = 0; i < Inv.getLength(); i++)
		{
			if(i>=32)
			{
				Inventory.redSlot.setBlockType(Inv.getValue(0, i));
				Inventory.redSlot.setStackSize(Inv.getValue(1, i));
				Inventory.redSlot.setBlockDamage(Inv.getValue(2, i));
				
			}
			else
			{
				Inventory.slots[i].setBlockType(Inv.getValue(0, i));
				Inventory.slots[i].setStackSize(Inv.getValue(1, i));
				Inventory.slots[i].setBlockDamage(Inv.getValue(2, i));
			}
		}
		int[] miscArray = loadNumberArray(misc);
		Block.offSetX = miscArray[0];
		Block.offSetY = miscArray[1];
		if (miscArray.length == 3)
		{
			ModeManager.creative = miscArray[2] == 1;
		}
		if (miscArray.length > 3)
		{
			PointCounter.points = miscArray[3];
			SkillBar.mineSpeed.setLevel(miscArray[4], true);
			SkillBar.moveSpeed.setLevel(miscArray[5], true);
			SkillBar.damage.setLevel(miscArray[6], true);
			SkillBar.health.setLevel(miscArray[7], true);
		}
		int[] achArray = loadNumberArray(ach);
		for (int i = 0; i < achArray.length; i++)
		{
			AchievementManager.achievements.get(i).achieved = (achArray[i] == 1);
		}

		box.hide();
	}

	public static int[] loadNumberArray(String str)
	{
		int pos = 0;

		int[] numArray;
		int length = 0;
		while (pos < str.length())
		{
			char p = str.charAt(pos);
			if (p == ';')
			{
				length++;
			}
			pos++;
		}
		pos = 0;
		int numpos = 0;
		numArray = new int[length];
		StringBuilder string = new StringBuilder("");
		while (pos < str.length())
		{
			char p = str.charAt(pos);
			if (p == ';')
			{
				numArray[numpos] = Integer.parseInt(string.toString());
				string = new StringBuilder("");
				numpos++;
			} else
			{
				string.append(p);
			}
			pos++;
		}
		return numArray;
	}

	private static int[] writeNumberArray(int[] numArray)
	{
		String string = "";
		for (int i = 0; i < numArray.length; i++)
		{
			string += ((char) numArray[i]);
		}
		return numArray;
	}

	public static int getWorldHeight()
	{
		String settings = ReadWrite.read("resources\\Settings");

		String h = settings.substring(settings.indexOf(',') + 1,
				settings.length() - 1);
		h.trim();
		System.out.println(h);
		return Integer.parseInt(h.trim());
	}

	public static int getWorldWidth()
	{
		String settings = ReadWrite.read("resources\\Settings");
		System.out.println(settings);
		String w = settings.substring(0, settings.indexOf(','));
		w.trim();

		return Integer.parseInt(w.trim());
	}

}
