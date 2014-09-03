import greenfoot.*;
import java.util.List;

public class MobSpawner  
{
    private static final int spawnX = -ModeManager.center-75;
    private static final int spawnX2 = ModeManager.center+75;
    private static final int maxMonsterSpawnLight = 8;
    private static final int minFriendlySpawnLight = 10;
    private static final int maxMobs = 10;
    public static boolean spawnMobs = true;
    public static int numOfMobs = 0;
    private static int spawnTimer = 0;
    public static int[] stepOrder = {0,1,-1,2,-2,3,-3,4,-4,5,-5,6};
    /**
     * Places mob at side of screen if not blocked
     */
    public static Entity generateMob(boolean side,PhysicalMob mob)
    {
        int x;
        if(side)
        {
            x = spawnX + ModeManager.center;
        }
        else
        {
            x = spawnX2 + ModeManager.center;
        }

        for(int t =0;t<12;t++)//checks for places to put new mob
        {
            int y = ModeManager.centerY+(stepOrder[t]*64)-40;
            MCWorld.theWorld.addObject(mob,x,y);

            List actor = (mob.getCollision(Block.class));
            boolean blocked = false;
            for(int r = 0;r<actor.size();r++)
            {
                Block block = (Block)actor.get(r);
                if(block != null && block.isWall == false)
                {
                    blocked = true;
                }
            }
            if(blocked)
            {
                MCWorld.theWorld.removeObject(mob);
            }
            else
            {
                MCWorld.entities.add(mob);
                return mob;
                
            }

        }
        return null;
    }

    public static void spawnMonster()
    {
        if(numOfMobs<maxMobs)
        {
        	switch(Greenfoot.getRandomNumber(3))
        	{
        	case 0:
        		generateMob(Greenfoot.getRandomNumber(2)==0,new Zombie());
        		break;
        	case 1:
        		generateMob(Greenfoot.getRandomNumber(2)==0,new Skeleton());
        		break;
        	case 2:
        		generateMob(Greenfoot.getRandomNumber(2)==0,new Creeper());
        		break;
        	}
        		
        	
        		
        }
    }

    public static void spawnFriendly()
    {
        if(numOfMobs<maxMobs)
        {
            Entity mob =generateMob(Greenfoot.getRandomNumber(2)==0,new Nurse());
            if(mob == null)
            {
                return;
            }
            MCWorld.entities.add(mob);
            return;
        }
    }

    public static void act()//called from MCWorld, not Greenfoot
    {
        if(ModeManager.creative||!spawnMobs)
        {
            return;
        }
        spawnTimer++;
        if(spawnTimer!= 450)
        {
            return;
        }
        else if(!(Greenfoot.getRandomNumber(2)==0))
        {
            spawnTimer = 0;
            return;
        }
        System.out.println("!!!");

        spawnTimer = 0;
        int cy = ModeManager.centerY;
        int x1 = Block.posToArray(278,cy)[0];int y1 = Block.posToArray(278,cy)[1];
        int x2 = Block.posToArray(724,cy)[0];int y2 = Block.posToArray(724,cy)[1];
        if((Block.light[x1][y1] < maxMonsterSpawnLight)&&(Block.light[x2][y2] < maxMonsterSpawnLight))
        {
            spawnMonster();
        }
        if((Block.light[x1][y1] > minFriendlySpawnLight)&&(Block.light[x2][y2] > minFriendlySpawnLight)&&Greenfoot.getRandomNumber(3)==0)
        {
            spawnFriendly();
        }
    }

}