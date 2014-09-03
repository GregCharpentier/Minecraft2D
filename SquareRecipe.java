import java.util.ArrayList;
import java.util.Arrays;
/**
 * A recipe for a square grid.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SquareRecipe  
{
    public Slot product;
    public Slot[][] recipe;
    public int[] extras;

    public SquareRecipe(String argProduct,int productNum,String... recipe)
    {
    	int damage = 0;
    	if(argProduct.contains("#"))
    	{
    		damage = Integer.parseInt(argProduct.substring(argProduct.indexOf("#")+1));
    		argProduct = argProduct.substring(0,argProduct.indexOf("#"));
    	}
    	product = new Slot();
    	
    	product.setItem(Block.getBlockClass(argProduct),productNum, damage);
        this.recipe = shiftRecipe(convertRecipe(recipe));
    }

    public SquareRecipe setExtras(int... extra)
    {
        extras = extra;
        return this;
    }
    /**
     * Converts a recipe from the String... form in the constructor into a int[][]
     * @param recipe
     * @return
     */
    private static Slot[][] convertRecipe(String[] recipe)//":XBottle" or ":RStone"
    {
        ArrayList<Character> keys = new <Character>ArrayList();
        ArrayList<Integer> values = new <Integer>ArrayList();
        ArrayList<Integer> damages = new <Integer>ArrayList();
        int split =0;
        for(int i =0;i<recipe.length;i++)
        {
            if(recipe[i].charAt(0)!=':')
            {
                split = i;   
                break;
            }
        }
        int i = 0;
        
        //System.out.println(split);
        Slot[][] endRecipe = new Slot[recipe.length-split][recipe[split].length()];
        //System.out.println(endRecipe.length+":"+endRecipe[0].length);
        for(;recipe[i].charAt(0)==':';i++)
        {
            keys.add(recipe[i].charAt(1));
            if(recipe[i].contains("#"))
        	{
        		damages.add(Integer.parseInt(recipe[i].substring(recipe[i].indexOf("#")+1)));
        		recipe[i] = recipe[i].substring(0,recipe[i].indexOf("#"));
        	}
            else
            {
            	damages.add(0);
            }
            values.add(Block.getBlockClass(recipe[i].substring(2)));
            
        }
        for(;i<recipe.length;i++)
        {
            for(int j = 0;j<endRecipe[0].length;j++)
            {
                char c = recipe[i].charAt(j);
                if(c=='0')
                {
                    endRecipe[i-split][j]=null;
                    continue;
                }
                int index = keys.indexOf(c);
                endRecipe[i-split][j] = new Slot();
                endRecipe[i-split][j].setItem(values.get(index),1,damages.get(index));
            }
        }
        return endRecipe;
    }
    /**
     * Adjusts a recipe array so that it is in the bottom left corner
     * @param 
     * @return adjusted recipe
     */
    public static Slot[][] shiftRecipe(Slot[][] recipe)//
    {
        if(Arrays.deepEquals(recipe,new Slot[recipe.length][recipe[0].length]))
        {
            return recipe;
        }
        while(true)//leftside
        {
            boolean emptyside = true;
            for(int i=0;i<recipe.length;i++)
            {
                if(recipe[i][0]!= null)
                {
                    emptyside = false;
                }

            }
            if(emptyside)
            {
                for(int i=0;i<recipe.length;i++)
                {
                    for(int j=1;j<recipe[i].length;j++)
                    {
                        recipe[i][j-1]=recipe[i][j];
                        recipe[i][j]=null;
                    }
                }
            }
            else
            {
                break;
            }
        }
        while(true)//bottom
        {
            boolean emptyside = true;
            for(int i=0;i<recipe[recipe.length-1].length;i++)
            {
                if(recipe[recipe.length-1][i]!= null)
                {
                    emptyside = false;
                }
            }
            if(emptyside)
            {
                for(int i=recipe.length-2;i>=0;i--)
                {
                    recipe[i+1]=recipe[i];
                    recipe[i] = new Slot[recipe[i].length];
                }
            }
            else
            {
                break;
            }
        }
        return recipe;
    }

    public static Slot getProduct(Slot[][] entered,ArrayList<SquareRecipe> cookbook)
    {
        entered = shiftRecipe(entered);
        for(int i=0;i<cookbook.size();i++)
        {
            if(RecipeEquals(cookbook.get(i).recipe,entered))
            {
                return cookbook.get(i).product;
            }
        }
        return null;
    }

    public static Slot getProductWithExtras(Slot[][] entered,int[] extra,ArrayList<SquareRecipe> cookbook)
    {
        entered = shiftRecipe(entered);
        for(int i=0;i<cookbook.size();i++)
        {
            if(RecipeEquals(cookbook.get(i).recipe,entered))
            {
                if(Arrays.equals(extra,cookbook.get(i).extras))
                {
                    return cookbook.get(i).product;
                }
            }
        }
        return null;
    }

    private static boolean RecipeEquals(Slot[][] recipe1, Slot[][] recipe2)
	{
		for(int i = 0;i<recipe1.length;i++)
		{
			for(int j = 0;j<recipe1[0].length;j++)
			{
				Slot rec1 = recipe1[i][j];
				Slot rec2 = recipe2[i][j];
				if((rec1==null)!=(rec2==null))
				{
					return false;
				}
				if(rec1==null)
					continue;
				if(rec1.blockType!=rec2.blockType||rec1.blockDamage!=rec2.blockDamage)
					return false;
			}
		}
		return true;
	}

	public String toString()
    {
        String out = product.blockType+":"+product.stackSize;
        for(int i =0;i<recipe.length;i++)
        {
            String print=":";
            for(int j=0;j<recipe[i].length;j++)
            {
                print+=recipe[i][j]+":";
            }
            out+="\n"+print;

        }
        return out;
    }
}
