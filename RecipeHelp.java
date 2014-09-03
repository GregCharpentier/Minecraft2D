import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;
import java.util.ArrayList;
/**
 * Write a description of class CraftingHelp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecipeHelp extends Blurb
{
    /**
     * Act - do whatever the CraftingHelp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public String type;
    public OverButton next;
    public OverButton previous;
    public OverButton exit;
    //public static int[][] recipes;
    public static ArrayList<SquareRecipe> recipes = new ArrayList<SquareRecipe>();
    public Slot[] helpSlots;
    private int[] x1=new int[2],y1 = new int[2];
    public int page = 0;
    public RecipeHelp(String type,int x,int y)
    {
        
        this.type = type;
        setImage("images\\inf"+type+"display.png");
        int cornerX = x-(getImage().getWidth()/2)+18;
        int cornerY = y-(getImage().getHeight()/2)+18;
        if(type == "crafting")
        {
            recipes = CraftingManager.recipes;
            x1[0] = 54+cornerX;  x1[1] = 312+cornerX;
            y1[0] = 84+cornerY;  y1[1] = 231+cornerY;
        }
        else if(type == "furnace")
        {
            recipes = FurnaceManager.recipes;
            x1[0] = 54+cornerX;  x1[1] = 312+cornerX;
            y1[0] = 84+cornerY;  y1[1] = 231+cornerY;
        }else if(type == "anvil")
        {
            recipes = AnvilManager.recipes;
            x1[0] = 54+cornerX;  x1[1] = 312+cornerX;
            y1[0] = 84+cornerY;  y1[1] = 231+cornerY;
        }else if(type == "openhearth")
        {
            recipes = FurnaceManager.OHrecipes;
            x1[0] = 76+cornerX;  x1[1] = 310+cornerX;
            y1[0] = 99+cornerY;  y1[1] = 246+cornerY;
        }
        MCWorld.theWorld.addObject(this,x,y);
        setup();    
    }

    public int[] fuseArrays(int[] A, int[] B)
    {
        int[] C = new int[A.length+B.length];
        System.arraycopy(A, 0, C, 0, A.length);
        System.arraycopy(B, 0, C, A.length, B.length);
        return C;
    }

    public int[][] fuseCrafting(int[][] four,int[][] nine,int recSize,boolean adjust)
    {
        int[][]newfour = new int[four.length][four[0].length];
        if(adjust)
        {
            for(int t = 0;t<four.length;t++)
            {
                newfour[t] = Arrays.copyOf(four[t], recSize);
                newfour[t][7] =newfour[t][3];
                newfour[t][6] =newfour[t][2];
                newfour[t][4] =newfour[t][1];
                newfour[t][3] =newfour[t][0];
                newfour[t][2] = 0;
                newfour[t][1] = 0;
                newfour[t][0] = 0;
            }
        }
        else
        {
            newfour = four;
        }

        int[][] C = new int[newfour.length+nine.length][recSize];
        System.arraycopy(newfour, 0, C, 0, newfour.length);
        System.arraycopy(nine, 0, C, newfour.length, nine.length);
        return C;
    }

    public void nextPage()
    {
        closePage();
        page++;
        buildPage(page);
    }

    public void previousPage()
    {
        closePage();
        if(page <= 0)
        {
            return;
        }
        page--;
        buildPage(page);
    }

    public void close()
    {
        closePage();
        MCWorld.theWorld.removeObject(this);
        ModeManager.clearRecipeHelp();
    }

    public void closePage()
    {
        for(int t = 0;t<helpSlots.length;t++)
        {
            if(helpSlots[t] != null)
            {
                helpSlots[t].removeItem();
                MCWorld.theWorld.removeObject(helpSlots[t]);
                helpSlots[t] =null;
            }
        }
        if(next != null)
        {
            MCWorld.theWorld.removeObject(next);
        }
        if(previous != null)
        {
            MCWorld.theWorld.removeObject(previous);
        }
        if(exit != null)
        {
            MCWorld.theWorld.removeObject(exit);
        }
        next = null;
        previous = null;
        exit = null;
    }

    public void setup()
    {
        buildPage(0);

    }

    private int getAnvilItem(int num)
    {
        int loc1 = num/5;
        int loc2 = num%5;
        return AnvilManager.getItem(loc1,loc2);
    }

    public void buildPage(int page)
    {
        boolean first = page == 0;
        boolean last = false;

        helpSlots = new Slot[11*4];
        if(type == "crafting")
        {
            for(int g = 0;g<4;g++)
            {

                int index = page*4+g;
                if(index>recipes.size()-1)
                {
                    last = true;
                    continue;
                }
                int shift = g*10;
                int dif = 36;
                int xSlot = 0;
                int ySlot = 0;
                int xx;
                int yy;
                SquareRecipe rec = recipes.get(index);
                if(g == 0||g==1)
                {
                    xx = x1[0];
                }
                else
                {
                    xx = x1[1];
                }
                if(g == 0||g==2)
                {
                    yy = y1[0];
                }
                else
                {
                    yy = y1[1];
                }
                int u=0;
                for(int x = 0;x<rec.recipe.length;x++)
                {
                    for(int y = 0;y<rec.recipe[x].length;y++)
                    {
                    	if(rec.recipe[y][x]!= null)
                        helpSlots[u+shift] = new Slot2(xx+x*dif,yy+y*dif).setAsDisplay().setItem(rec.recipe[y][x].blockType,1).show();
                        u++;
                    }
                    
                }
                xSlot = 4;
                ySlot = 1;
                helpSlots[9+shift] = new Slot2(xx+xSlot*dif+9,yy+ySlot*dif).setAsDisplay().setItem(rec.product.blockType,rec.product.stackSize).show(); 
                if(index>recipes.size()-2)
                {
                    last = true;
                }
            }
            
        }
        if(type == "furnace")
        {
            
            for(int g = 0;g<4;g++)
            {

                int index = page*4+g;
                if(index>recipes.size()-1)
                {
                    last = true;
                    continue;
                }
                int shift = g*10;
                int dif = 36;
                int xSlot = 0;
                int ySlot = 0;
                int xx;
                int yy;
                SquareRecipe rec = recipes.get(index);
                if(g == 0||g==1)
                {
                    xx = x1[0];
                }
                else
                {
                    xx = x1[1];
                }
                if(g == 0||g==2)
                {
                    yy = y1[0];
                }
                else
                {
                    yy = y1[1];
                }
                int u=0;
                for(int x = 0;x<rec.recipe[0].length;x++)
                {
                		if(rec.recipe[y][x]!= null)
                			helpSlots[u+shift] = new Slot2(xx+x*dif,yy+y*dif).setAsDisplay().setItem(rec.recipe[y][x].blockType,1).show();
                        u++;
                    
                }
                xSlot = 4;
                ySlot = 1;
                helpSlots[9+shift] = new Slot2(xx+xSlot*dif+1,yy+ySlot*dif).setAsDisplay().setItem(rec.product.blockType,rec.product.stackSize).show(); 
                if(index>recipes.size()-2)
                {
                    last = true;
                }
            }
            
            
            
        }
        if(type == "anvil")
        {
            for(int g = 0;g<4;g++)
            {

                int index = page*4+g;
                if(index>recipes.size()-1)
                {
                    last = true;
                    continue;
                }
                int shift = g*11;
                int dif = 36;
                int xSlot = 0;
                int ySlot = 0;
                int xx;
                int yy;
                SquareRecipe rec = recipes.get(index);
                if(g == 0||g==1)
                {
                    xx = x1[0];
                }
                else
                {
                    xx = x1[1];
                }
                if(g == 0||g==2)
                {
                    yy = y1[0];
                }
                else
                {
                    yy = y1[1];
                }
                int u=0;
                for(int x = 0;x<rec.recipe.length;x++)
                {
                    for(int y = 0;y<rec.recipe[x].length;y++)
                    {
                    	if(rec.recipe[y][x]!= null)
                            helpSlots[u+shift] = new Slot2(xx+x*dif,yy+y*dif).setAsDisplay().setItem(rec.recipe[y][x].blockType,1).show();
                        u++;
                    }
                    
                }
                xSlot = 4;
                ySlot = 1;
                helpSlots[10+shift] = new Slot2(xx+xSlot*dif+9,yy+ySlot*dif).setAsDisplay().setItem(rec.product.blockType,rec.product.stackSize).show(); 
                xSlot = -1;
                helpSlots[9+shift] = new Slot2(xx+xSlot*dif,yy+ySlot*dif).setAsDisplay().setItem(rec.extras[0],1).show(); 
                if(index>recipes.size()-2)
                {
                    last = true;
                }
            }
            
        }
        if(type == "openhearth")
        {
            
            for(int g = 0;g<4;g++)
            {

                int index = page*4+g;
                if(index>recipes.size()-1)
                {
                    last = true;
                    continue;
                }
                int shift = g*10;
                int dif = 36;
                int xSlot = 0;
                int ySlot = 0;
                int xx;
                int yy;
                SquareRecipe rec = recipes.get(index);
                if(g == 0||g==1)
                {
                    xx = x1[0];
                } 
                else
                {
                    xx = x1[1];
                }
                if(g == 0||g==2)
                {
                    yy = y1[0];
                }
                else
                {
                    yy = y1[1];
                }
                int u=0;
                for(int x = 0;x<rec.recipe[0].length;x++)
                {
                		if(rec.recipe[0][x]!= null)
                			helpSlots[u+shift] = new Slot2(xx+x*dif,yy+0*dif).setAsDisplay().setItem(rec.recipe[0][x].blockType,1).show();
                        u++;
                    
                }
                xSlot = 4;
                ySlot = 1;
                helpSlots[9+shift] = new Slot2(xx+xSlot*dif-15,yy+ySlot*dif-16).setAsDisplay().setItem(Block.getBlockClass("Impurities"),1).show(); 
                ySlot = 2;
                helpSlots[8+shift] = new Slot2(xx+xSlot*dif-15,yy+ySlot*dif-16).setAsDisplay().setItem(rec.product.blockType,rec.product.stackSize).show();
                if(index>recipes.size()-2)
                {
                    last = true;
                }
            }
            
            
            
        }
        if(!first)
        {
            previous = new OverButton("Previous",4,"images\\Previous.png");
            MCWorld.theWorld.addObject(previous,getX()-120,getY()+163);
        }
        if(!last)
        {
            next = new OverButton("Next",5,"images\\Next.png");
            MCWorld.theWorld.addObject(next,getX()+120,getY()+163);
        }
        {
            exit = new OverButton("Exit",6,"images\\Exit.png");
            MCWorld.theWorld.addObject(exit,getX(),getY()+163);
        }
        
    }

    public void remove()
    {

    }

}
