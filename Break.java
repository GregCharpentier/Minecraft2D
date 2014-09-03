import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Break here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Break extends Actor
{
    public int blockX;
    public int blockY;
    public int xx;
    public int yy;
    public static Break theBreak;
    public boolean visible;
    public static boolean breaking;
    public final boolean wall;
    private final int time;
    private int stage;
    private int subStage;
    /**
     * Act - do whatever the Break wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setLocation(xx+Block.offSetX,yy+Block.offSetY);
    }   
    public Break(int parTime,int x, int y,boolean parWall)
    {
        wall = parWall;
        time = parTime;
        blockX = x;
        blockY = y;
        xx = x*64-32;
        yy = y*64-32;
        MCWorld.theWorld.addObject(this,xx+Block.offSetX,yy+Block.offSetY);
        setImage("images\\breaks/break1.png");
        stage = 1;
        subStage = 1;
    }
    public static boolean hit(int x, int y, int parTime,boolean parWall)
    {   
        
        if(theBreak == null)
        {
            if(!parWall)
            {
                theBreak = new Break(parTime,x,y,parWall);
            }
            else
            {
                theBreak = new WallBreak(parTime,x,y,parWall);
            }
            return false;
        }
        if(parWall != theBreak.wall)
        {
            removeBreak();
            return false;
        }
        else if(x == theBreak.blockX && y == theBreak.blockY)
        {
            if(theBreak.subStage==theBreak.time)
            {
                theBreak.stage++;
                if(theBreak.stage ==11)
                {
                    removeBreak();
                    return true;
                }
                Integer inTstage = theBreak.stage;
                theBreak.subStage = 1;
                theBreak.setImage("images\\breaks/break"+inTstage.toString()+".png");
                
            }
            else
            {
                theBreak.subStage++;
                
            }
        }
        else
        {
            removeBreak();
            
            
            
        }
        return false;
    }
    
    public static void removeBreak()
    {
        MCWorld.theWorld.removeObject(theBreak);
        theBreak = null;
    }
    
}
