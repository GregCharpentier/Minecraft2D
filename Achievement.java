import greenfoot.*;
import java.awt.Color;
import java.awt.Font;
/**
 * An achievment.
 */
public class Achievement extends Actor  
{
    private GreenfootImage icon;
    public String name;
    private String desc;
    private String descLine2;
    public boolean achieved;
    public Vec2 loc;
    public Achievement parent;
    private AchievementLine xLine;
    private AchievementLine yLine;
    private boolean firstDim;//the line that goes first
    public ImageObj descRect;
    public int skillReward;
    public Achievement(String name, String desc,String desc2, Vec2 loc, boolean firstDim,Achievement parent, int reward)
    {
        this.name = name;
        this.desc = desc;
        this.descLine2 = desc2;
        //   \/ center location \/
        Vec2 initLoc = (new Vec2(AchievementScreen.BACK_X*48,AchievementScreen.BACK_Y*48)).divide(2.0);
        if(parent!= null)
        {
            initLoc = parent.loc;
        }
        this.loc = loc.add(initLoc);
        this.icon = new GreenfootImage("images\\achievements\\"+getImageSafeName(name)+".png");
        this.firstDim = firstDim;
        this.parent = parent;
        this.skillReward = reward;
    }

    public void draw()
    {       
        String color = "";
        GreenfootImage screen = new GreenfootImage("images\\black.png");
        screen.scale(36,36);
        if(parent == null || parent.achieved)
        {
            if(achieved)
            {
                color = "white";
                screen.setTransparency(0);
            }
            else
            {
                color = "gray";
                screen.setTransparency(0);
            }
        }
        else
        {
            color = "dark";
            screen.setTransparency(210);
        }
        GreenfootImage im = new GreenfootImage("images\\achievements\\back"+color+".png");
        im.drawImage(icon,4,4);
        im.drawImage(screen,4,4);
        setImage(im);
        Vec2 loc2 = loc.subtract(ModeManager.theAchievementScreen.offset);
        MCWorld.addObject(this,loc2);
        if(parent!=null)
        {
            drawRect();
        }
    }

    public void clear()
    {
        clearDesc();
        MCWorld.theWorld.removeObject(this);
        MCWorld.theWorld.removeObject(xLine);
        xLine = null;
        MCWorld.theWorld.removeObject(yLine);
        yLine = null;
    }

    public void act()
    {
        Vec2 loc2 = loc.subtract(ModeManager.theAchievementScreen.offset);
        setLocation(loc2.X,loc2.Y);
        System.out.println(loc2.X+":::"+loc2.Y);
        if(checkHover())
            drawDesc();
        else
            clearDesc();
    }

    public boolean checkHover()
    {
        if((Math.abs(MCWorld.mx-getX())<22)&&(Math.abs(MCWorld.my-getY())<22))
        {
            return true;   
        }
        return false;
    }

    public void drawDesc()
    {
        if(descRect == null)
        {
            GreenfootImage image = new GreenfootImage("images\\achievements\\descback.png");
            if(name.length()>25)
                image.scale((int)(image.getWidth()*1.5),image.getHeight());
            Font font = MCWorld.mcFont;
            font = font.deriveFont(16.0f);
            image.setFont(font);
            image.setColor(new Color(255, 255, 255, 255));
            image.drawString(name,5,20);
            if(parent == null || (parent.achieved || achieved))
            {
                image.setColor(new Color(160, 160, 160, 255));
                image.drawString(desc,5,45);
                image.drawString(descLine2,5,65);
            }
            else
            {
                image.setColor(new Color(100, 80, 80, 255));
                image.drawString("Requires achievement:",5,45);
                image.drawString(parent.name,5,65);
            }

            font = font.deriveFont(8.0f);
            image.setFont(font);
            if(achieved)
            {
                image.setColor(new Color(0, 160, 0, 255));
                image.drawImage(new GreenfootImage("images\\achievements\\check.png"),5,75);
            }
            else
            {

                image.setColor(new Color(160, 0, 0, 255));
                image.drawImage(new GreenfootImage("images\\achievements\\box.png"),5,75);
            }
            image.drawString("Reward: "+skillReward+" skill points",25,87);
            descRect = new ImageObj();
            descRect.setImage(image);
            MCWorld.theWorld.addObject(descRect,MCWorld.mx+descRect.getImage().getWidth()/2,MCWorld.my+48); 
        }
        else
        {
            descRect.setLocation(MCWorld.mx+descRect.getImage().getWidth()/2,MCWorld.my+48);
        }
        if(MCWorld.DEVMODE && MouseHelper.buttonsDown == 1)
        {
            this.achieve();
        }
    }

    public void clearDesc()
    {
        if(descRect != null)
        {
            MCWorld.theWorld.removeObject(descRect);
            descRect = null;
        }
    }

    public void drawRect()
    {
        int width = Math.abs(parent.loc.X-this.loc.X);
        int height = Math.abs(parent.loc.Y-this.loc.Y);
        int x = (parent.loc.X-this.loc.X)/2+this.loc.X;
        int y = (parent.loc.Y-this.loc.Y)/2+this.loc.Y;
        String color = "";
        if(parent.achieved)
        {
            if(achieved)
            {
                color = "gray";
            }
            else
            {
                color = "blue";
            }
        }
        else
        {
            color = "dark";
        }
        if(firstDim)
        {
            if(width>2)
                xLine = new AchievementLine(width,2,color,x,loc.Y);
            if(height>2)
                yLine = new AchievementLine(2,height,color,parent.loc.X,y);  
        }
        else
        {
            if(width>2)
                xLine = new AchievementLine(width,2,color,x,parent.loc.Y);
            if(height>2)
                yLine = new AchievementLine(2,height,color,loc.X,y);  
        }
        
    }

    public boolean achieve()
    {
        if(achieved||(parent!= null &&!parent.achieved)||ModeManager.creative)
        {
            return false;
        }
        PointCounter.points++;
        achieved = true;
        new AchievementPopup(name,icon,skillReward);
        return true;
    }
    public static String getImageSafeName(String name)
    {
        return name.replace('?', ' ').replace('\"',' ').replace(':', ' ');
    }
}
