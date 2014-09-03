/**
 * Write a description of class Chunk here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Chunk  
{
    public int x;
    public int y;
    public boolean active;
    public boolean pre;
    /**
     * Constructor for objects of class Chunk
     */
    public Chunk(int chunkGridX, int chunkGridY)
    {
          x= chunkGridX;
          y = chunkGridY;
    }
    
    

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int[] getBlockArrayCoord()
    {
        int[] coords = {x*16,y*16};
        return coords;
    }

    public static void activateChunk(int chunkArrayX,int chunkArrayY)//depracated
    {
        if(ChunkManager.chunks[chunkArrayX][chunkArrayY].active)
        {
            return;
        }
        System.out.println("Activating " +  chunkArrayX + ", " + chunkArrayY);
        ChunkManager.chunks[chunkArrayX][chunkArrayY].active = true;
        for(int t = 0; t< 16; t++)
        {
            for(int r = 0; r< 16; r++)
            {
                Block theblock = Block.blocks[t+chunkArrayX*16][r+chunkArrayY*16];
                MCWorld.theWorld.addObject(theblock,theblock.x+Block.offSetX,theblock.y+Block.offSetY);
                Block.refreshLight(t+chunkArrayX,r+chunkArrayY,false,false);
            }

        }

    }

    public static void deactivateChunk(int chunkArrayX,int chunkArrayY)//depracated
    {
        if(!ChunkManager.chunks[chunkArrayX][chunkArrayY].active)
        {
            return;
        }
        ChunkManager.chunks[chunkArrayX][chunkArrayY].active = false;
        for(int t = 0; t< 16; t++)
        {
            for(int r = 0; r< 16; r++)
            {
                Block theblock = Block.blocks[t+chunkArrayX*16][r+chunkArrayY*16];
                MCWorld.theWorld.removeObject(theblock);
            }

        }

    }
    
    public void setAsActive()
    {
        pre = true;
    }
    
    public void setAsInactive()
    {
        
        pre = false;
    }

    public void activateChunk()
    {

        if(active)
        {
            return;
        }
        
        active = true;
        
        for(int t = 0; t< 16; t++)
        {
            for(int r = 0; r< 16; r++)
            {
                Block theblock = Block.blocks[t+x*16][r+y*16];
                Wall thewall = Block.walls[t+x*16][r+y*16];
                Block.refreshLight(t+x*16,r+y*16,true,false);
                if(theblock != null)
                {
                    MCWorld.theWorld.addObject(theblock,theblock.x+Block.offSetX,theblock.y+Block.offSetY);
                    if(theblock.isStandardBox())
                    MCWorld.theWorld.addObject(theblock.border,theblock.x+Block.offSetX,theblock.y+Block.offSetY);
                }
                if(thewall != null)
                {
                    MCWorld.theWorld.addObject(thewall,thewall.x+Block.offSetX,thewall.y+Block.offSetY);
                }
            }

        }

    }

    public void deactivateChunk()
    {
        if(pre)
        {
            return;
        }
        if(!active)
        {
            return;
        }
        active = false;
        for(int t = 0; t< 16; t++)
        {
            for(int r = 0; r< 16; r++)
            {
                Block theblock = Block.blocks[t+x*16][r+y*16];
                Wall thewall = Block.walls[t+x*16][r+y*16];
                if(theblock != null)
                {
                    MCWorld.theWorld.removeObject(theblock);
                    if(theblock.isStandardBox())
                    MCWorld.theWorld.removeObject(theblock.border);
                }
                if(thewall != null)
                {
                    MCWorld.theWorld.removeObject(thewall);
                }
            }

        }

    }

}
