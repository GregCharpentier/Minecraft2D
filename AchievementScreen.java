import java.util.ArrayList;
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Manages achievment screen.
 */
public class AchievementScreen  
{
    private ArrayList<ArrayList<AchievementBackgroundSquare>> background = new ArrayList<ArrayList<AchievementBackgroundSquare>>();
    public static final int BACK_X = (int)(ModeManager.sizeX*1.4/32);
    public static final int BACK_Y = (int)(ModeManager.sizeY*1.4/32);
    public Vec2 offset = new Vec2(BACK_X/2+ModeManager.sizeX/2,BACK_Y/2+ModeManager.sizeY/2);
    public Vec2 lastMouse = new Vec2(-1,-1);
    public ExitButton exit = new ExitButton();
    /**
     * Constructor for objects of class AchievementScreen
     */
    public AchievementScreen()
    {

    }

    public void draw()
    {
        generateBackground();
        for(Achievement a : AchievementManager.achievements)
        {
            a.draw();
        }
        MCWorld.theWorld.addObject(exit,ModeManager.sizeX-exit.getImage().getWidth()/2,
        exit.getImage().getHeight()/2);
        
    }

    public void generateBackground()
    {
        for(int i = 0;i<BACK_X;i++)
        {
            background.add(new ArrayList<AchievementBackgroundSquare>());
            for(int j = 0;j<BACK_Y;j++)
            {
                String f = null;
                if(j == 0)
                {
                    f = "Grass";
                }
                else if(j<BACK_Y/10)
                {

                    f = "Dirt";
                }
                else if(j<BACK_Y/8)
                {
                    switch(Greenfoot.getRandomNumber(3))
                    {
                        case 0:f = "Dirt";break;
                        case 1:f = "Dirt";break;
                        case 2:f = "Stone";break;
                    }
                }
                else if(j<BACK_Y/6)
                {
                    switch(Greenfoot.getRandomNumber(3))
                    {
                        case 0:f = "Dirt";break;
                        case 1:f = "Stone";break;
                        case 2:f = "Stone";break;
                    }
                }
                else if(j<BACK_Y/2)
                {
                    switch(Greenfoot.getRandomNumber(20))
                    {
                        case 0:f = "Coal Ore";break;
                        case 2:f = "Iron Ore";break;
                        default:f = "Stone";break;
                    }
                }
                else if(j<BACK_Y/1.8)
                {
                    switch(Greenfoot.getRandomNumber(3))
                    {
                        case 0:f = "Stone";break;
                        case 1:f = "Stone";break;
                        case 2:f = "Basalt";break;
                    }
                }
                else if(j<BACK_Y/1.6)
                {
                    switch(Greenfoot.getRandomNumber(3))
                    {
                        case 0:f = "Stone";break;
                        case 1:f = "Basalt";break;
                        case 2:f = "Basalt";break;
                    }
                }
                else if(j<BACK_Y/1.2)
                {
                    switch(Greenfoot.getRandomNumber(20))
                    {
                        case 0:f = "Basalt Coal Ore";break;
                        case 2:f = "Basalt Silver Ore";break;
                        default:f = "Basalt";break;
                    }
                }
                else if(j<BACK_Y/1.1)
                {
                    switch(Greenfoot.getRandomNumber(3))
                    {
                        case 0:f = "Bedrock";break;
                        case 1:f = "Basalt";break;
                        case 2:f = "Basalt";break;
                    }
                }
                else if(j<BACK_Y)
                {
                    f = "Bedrock";
                }
                AchievementBackgroundSquare sq;
                Vec2 pos = new Vec2(i,j);
                if(f == null)
                    sq = (new AchievementBackgroundSquare(pos));
                else
                    sq = (new AchievementBackgroundSquare(f,pos));
                MCWorld.addObject(sq,pos.multiply(48.0).subtract(offset).add(new Vec2(24)));
                background.get(i).add(sq);
            }
        }
    }

    public void act()
    {
        if(Greenfoot.getMouseInfo()!= null && Greenfoot.getMouseInfo().getButton()== 1)
        {
            Vec2 temp = lastMouse;
            lastMouse = new Vec2(MCWorld.mx,MCWorld.my);
            if(!temp.equals(new Vec2(-1,-1)))
            {
                //offset.add(lastMouse.subtract(temp));
                System.out.println(lastMouse.subtract(temp).Y+"::"+lastMouse.subtract(temp).Y);
                offset.X = Math.max(Math.min(offset.X-lastMouse.X+temp.X,BACK_X*48-ModeManager.sizeX-24),0);
                offset.Y = Math.max(Math.min(offset.Y-lastMouse.Y+temp.Y,BACK_Y*48-ModeManager.sizeY-24),0);
                System.out.println(offset.X+"::"+offset.Y);
            }
        }
        else
        {
            lastMouse = new Vec2(-1,-1);
        }

    }

    public void clear()
    {
        for(int i = 0;i<BACK_X;i++)
        {
            for(int j = 0;j<BACK_Y;j++)
            {
                MCWorld.theWorld.removeObject(background.get(i).get(j));
            }
        }
        for(Achievement a : AchievementManager.achievements)
        {
            a.clear();
        }
        MCWorld.theWorld.removeObject(exit);
    }
}
