import greenfoot.*;
/**
 * Write a description of class GenerationManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GenerationManager  
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class GenerationManager
     */
    public GenerationManager()
    {
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }

    public static void plantTree(int x, int y)
    {
        int rand = Greenfoot.getRandomNumber(3);
        int yy = y;
        while(y>(rand -5 +yy))
        {
            Block.placeWallArrayDry(x,y,new WoodWall());
            y--;
        }
        Block.placeAtArrayDry(x-2,y,new Leaves());
        Block.placeAtArrayDry(x-1,y,new Leaves());
        Block.placeAtArrayDry(x,y,new Leaves());
        Block.placeAtArrayDry(x+1,y,new Leaves());
        Block.placeAtArrayDry(x+2,y,new Leaves());
        y--;
        Block.placeAtArrayDry(x-1,y,new Leaves());
        Block.placeAtArrayDry(x,y,new Leaves());
        Block.placeAtArrayDry(x+1,y,new Leaves());
        y--;
        Block.placeAtArrayDry(x,y,new Leaves());
    }

    public static boolean chance(int chance)
    {

        return (Greenfoot.getRandomNumber(chance)==0);
    }

    public static int rand(int range)
    {

        return (Greenfoot.getRandomNumber(range*2+1)-range);
    }

    public static void clearBubble(int size, int x, int y)
    {
        int width = size;
        //int height = size;

        for(int t = -width;t<width;t++)
        {
            Block.removeArray(x+t,y);
        }
        width--;
        int height = 0;
        while(width >0)
        {
            height++;

            for(int t = -width;t<0;t++)
            {
                Block.removeArray(x+t,y-height);
                Block.removeArray(x-t,y-height);
                Block.removeArray(x+t,y+height);
                Block.removeArray(x-t,y+height);
                //System.out.println(t);
            } 
            Block.removeArray(x,y-height);
            Block.removeArray(x,y+height);
            width--;
        }

    }

    public static void oreDeposit(int size,int id, int x, int y)
    {

        int width = size;

        for(int t = -width;t<width;t++)
        {
            if(!chance(2))
                Block.placeAtArrayDry(x+t,y,Block.getBlockOf(id),true);
        }
        width--;
        int height = 0;
        while(width >0)
        {
            height++;

            for(int t = -width;t<0;t++)
            {
                if(!chance(3))
                    Block.placeAtArrayDry(x+t,y-height,Block.getBlockOf(id),true);
                if(!chance(3))
                    Block.placeAtArrayDry(x-t,y-height,Block.getBlockOf(id),true);
                if(!chance(3))
                    Block.placeAtArrayDry(x+t,y+height,Block.getBlockOf(id),true);
                if(!chance(3))
                    Block.placeAtArrayDry(x-t,y+height,Block.getBlockOf(id),true);
                //System.out.println(t);
            } 
            if(!chance(2))
                Block.placeAtArrayDry(x,y-height,Block.getBlockOf(id),true);
            if(!chance(2))
                Block.placeAtArrayDry(x,y+height,Block.getBlockOf(id),true);
            width--;
        }

    }

    public static void generateCave(int x,int y, int size)
    {
        while(!chance(size))
        {
            clearBubble(2, x,y);
            if(chance(80))
            {
                 Block.placeAtArrayDry(x-1,y+1,new Bricks());
                 Block.placeAtArrayDry(x,y+1,new Bricks());
                 Block.placeAtArrayDry(x+1,y+1,new Bricks());
                 Block.placeAtArrayDry(x,y,new Fountain());
            }
            x+=rand(3);
            y+=rand(3);
            
        }
    }

    public static void generateInitialLandscape()
    {
        TextBox box = new TextBox("Generating terrain...");
        MCWorld.theWorld.repaint();
        for(int x=6;x<1015;x++)//inital land gen
        {
            for(int y=15;y<306;y++)
            {
                if(y == 15)
                {
                    Block.placeWithWallDry(x,y,new Grass(),new DirtWall());
                }
                else if(y < 19)
                {
                    Block.placeWithWallDry(x,y,new Dirt(),new DirtWall());
                }
                else if(y < 21)
                {
                    if(Greenfoot.getRandomNumber(2)==1)
                    {
                        Block.placeWithWallDry(x,y,new Dirt(),new DirtWall());
                    }
                    else
                    {
                        Block.placeWithWallDry(x,y,new Stone(),new StoneWall());   
                    }
                }
                else if(y < 49)
                {

                    Block.placeWithWallDry(x,y,new Stone(),new StoneWall());

                }
                else if(y < 53)
                {
                    if(Greenfoot.getRandomNumber(2)==1)
                    {
                        Block.placeWithWallDry(x,y,new Stone(),new StoneWall());
                    }
                    else
                    {
                        Block.placeWithWallDry(x,y,new Basalt(), new BasaltWall());   
                    }

                }
                else if(y<180)
                {
                    Block.placeWithWallDry(x,y,new Basalt(), new BasaltWall());
                }
                else if(y < 184)
                {
                    if(Greenfoot.getRandomNumber(2)==1)
                    {
                        Block.placeWithWallDry(x,y,new Limestone(),new LimestoneWall());
                    }
                    else
                    {
                        Block.placeWithWallDry(x,y,new Basalt(), new BasaltWall());   
                    }

                }
                else if(y < 300)
                {
                    Block.placeWithWallDry(x,y,new Limestone(), new LimestoneWall());   
                }
                else if(y < 306)
                {
                    Block.placeAtArrayDry(x,y,new Bedrock());
                }
            }
            Block.refreshLight(x,15,true,false);
            if(Greenfoot.getRandomNumber(6) == 3)
                plantTree(x,14);
            if(Greenfoot.getRandomNumber(6) == 3) 
            {
            	Block.placeAtArrayDry(x,14,new Flax());
            }
        }
        for(int x=6;x<1015;x++)// ore gen
        {
            for(int y=15;y<206;y++)
            {
                if(y == 15)
                {
                    // Block.placeWithWallDry(x,y,new Grass(),new DirtWall());
                }
                else if(y < 19)
                {
                    //Block.placeWithWallDry(x,y,new Dirt(),new DirtWall());
                }
                else if(y < 21)
                {
                    if(Greenfoot.getRandomNumber(2)==1)
                    {
                        //Block.placeWithWallDry(x,y,new Dirt(),new DirtWall());
                    }
                    else
                    {
                        //Block.placeWithWallDry(x,y,new Stone(),new StoneWall());   
                    }
                }else if(y < 48)
                {
                    int g = Greenfoot.getRandomNumber(380);
                    if(g==1 || g==2)
                    {
                        oreDeposit(2, Block.COALORE,x,y);
                    }
                    else if(g==3)
                    {
                        oreDeposit(2, Block.IRONORE,x,y);
                    }
                    else if(g==4)
                    {
                        oreDeposit(2, Block.SAND,x,y);
                    }
                }
                else if(y<180)
                {
                    int g = Greenfoot.getRandomNumber(270);
                    if(g==1&&y<100)
                    {
                        oreDeposit(2, 42,x,y); 
                    }
                    if((g==2||g==3)&&y>65)
                    {
                        oreDeposit(1, 43,x,y);
                    }
                    if(g>285 && y>160)
                    {
                        oreDeposit(Greenfoot.getRandomNumber(2)+1, 53,x,y);
                    }

                }
                else if(y < 300)
                {

                }

            }
        }
        for(int x=6;x<1015;x++)//cave gen
        {
            for(int y=15;y<206;y++)
            {
                if(y == 15)
                {
                    // Block.placeWithWallDry(x,y,new Grass(),new DirtWall());
                }
                else if(y < 19)
                {
                    //Block.placeWithWallDry(x,y,new Dirt(),new DirtWall());
                }
                else if(y < 21)
                {
                    if(Greenfoot.getRandomNumber(2)==1)
                    {
                        //Block.placeWithWallDry(x,y,new Dirt(),new DirtWall());
                    }
                    else
                    {
                        //Block.placeWithWallDry(x,y,new Stone(),new StoneWall());   
                    }
                }
                else if(y <135)
                {
                    int g = Greenfoot.getRandomNumber(500);
                    if(g==1 || g==2)
                    {
                        //Block.placeWithWallDry(x,y,new CoalOre(),new StoneWall());
                    }
                    else if(g==3)
                    {
                        generateCave(x,y,22);
                    }
                    else
                    {
                        //Block.placeWithWallDry(x,y,new Stone(),new StoneWall());
                    }
                    if(g==4||g==5)
                    {
                        //generateCave(x,y,6);
                    }

                }
                else if(y < 206)
                {
                    //Block.placeAtArrayDry(x,y,new Bedrock());
                }
            }
        }
        box.edit("Simulating lighting..");
        MCWorld.theWorld.repaint();
        for(int ii = 0;ii<0;ii++)
        {
            for(int x=6;x<1015;x++)
            {
                for(int y=15;y<306;y++)
                {
                    Block.refreshLight(x,y,false,false);
                }
            }
            for(int y=306;y>15;y--)
            {
                for(int x=1015;x>6;x--)
                {

                    Block.refreshLight(x,y,false,false);
                }
            }
        }
        for(int x=1015;x>6;x--)
                {

                    Block.refreshLight(x,15,true,true);
                }
                box.hide();
    }
}

