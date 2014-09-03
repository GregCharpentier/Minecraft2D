import java.util.ArrayList;
/**
 * Write a description of class ChunkManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ChunkManager  
{
    public static Chunk[][] chunks = new Chunk[64][64];
    private static ArrayList<Chunk> activeChunks = new ArrayList<Chunk>();
    /**
     * Constructor for objects of class ChunkManager
     */
    public ChunkManager()
    {
    }

    public static void generateChunks()
    {
        for(int t = 0; t< 64; t++)
        {
            for(int r = 0; r< 64; r++)
            {
                chunks[t][r] = new Chunk(t,r);
            }

        }
    }

    public static int[] getChunkCoordAtBlockArray(int xx, int yy)
    {
        int xb = 0;
        int yb = 0;
        while(xx > 16)
        {
            xx -= 16;
            xb++;
        }
        while(yy > 16)
        {
            yy -= 16;
            yb++;
        }
        int[] coord = {xb,yb};
        return coord;
    }

    public static Chunk getChunkAtBlockArray(int xx, int yy)
    {
        int xb = 0;
        int yb = 0;
        while(xx > 16)
        {
            xx -= 16;
            xb++;
        }
        while(yy > 16)
        {
            yy -= 16;
            yb++;
        }

        return chunks[xb][yb];
    }

    public static void refreshChunks()
    {
        ArrayList<Chunk> newActive = new ArrayList<Chunk>();
        for(int i = -32;i<ModeManager.sizeX+100;i+=400)
        {
            for(int j = -32;j<ModeManager.sizeY+160;j+=400)
            {
                int[] pos = Block.posToArray(i, j);
                Chunk chunk = getChunkAtBlockArray(pos[0], pos[1]);
                if(!newActive.contains(chunk))
                {
                    newActive.add(chunk);
                }
            } 
            // Bottom edge
            int[] pos = Block.posToArray(i, ModeManager.sizeY+160);
            Chunk chunk = getChunkAtBlockArray(pos[0], pos[1]);
            if(!newActive.contains(chunk))
            {
                newActive.add(chunk);
            }
        }
        for(int j = -32;j<ModeManager.sizeY+160;j+=400)
        {
            //Right edge
            int[] pos = Block.posToArray( ModeManager.sizeX+100,j);
            Chunk chunk = getChunkAtBlockArray(pos[0], pos[1]);
            if(!newActive.contains(chunk))
            {
                newActive.add(chunk);
            }
        }
        //Bottom right corner
        int[] pos = Block.posToArray( ModeManager.sizeX+100,ModeManager.sizeY+160);
        Chunk chunk = getChunkAtBlockArray(pos[0], pos[1]);
        if(!newActive.contains(chunk))
        {
            newActive.add(chunk);
        }
        
        //int[] array1 = Block.posToArray(-32, -32);
        //int[] array2 = Block.posToArray(850, -32);
        //int[] array3 = Block.posToArray(-32, 800);
        //int[] array4 = Block.posToArray(850, 800);
        setInactiveChunks(activeChunks);
        setActiveChunks(newActive);
        deactivateChunks();
        activeChunks = newActive;
        activateChunks();
    }

    private static void setActiveChunks( ArrayList<Chunk> parChunks)
    {
        for(int t = 0; t<parChunks.size();t++)
        {   if(parChunks.get(t) != null)
            {
                parChunks.get(t).setAsActive();
            }
        }
    }

    private static void setInactiveChunks( ArrayList<Chunk> parChunks)
    {
        for(int t = 0; t<parChunks.size();t++)
        {
            if(parChunks.get(t) != null)
            {
                parChunks.get(t).setAsInactive();
            }
        }
    }

    private static void deactivateChunks()
    {
        for(int t = 0; t<activeChunks.size();t++)
        {
            if(activeChunks.get(t) != null)
            {
                activeChunks.get(t).deactivateChunk();
            }

        }
    }

    private static void activateChunks()
    {
        for(int t = 0; t<activeChunks.size();t++)
        {
            if(activeChunks.get(t) != null)
            {

                activeChunks.get(t).activateChunk();
            }

        }
    }

    public static void removeAll()
    {
        setInactiveChunks(activeChunks);
        deactivateChunks();
    }
}
