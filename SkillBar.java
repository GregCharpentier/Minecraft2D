import greenfoot.*;
import java.awt.Color;
import java.awt.Font;
/**
 * Write a description of class SkillBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SkillBar extends Actor
{
    public final String skill;
    public final int ID;
    private int level = 0;
    private SkillIncreaseButton button;
    private advTextBox text;
    private int offset; 
    public static SkillBar[] skillBars=new SkillBar[4];
    public static final GreenfootImage skillPoint = new GreenfootImage("images\\skillpoint.png");
    public static final GreenfootImage skillBase = new GreenfootImage("images\\skillbase.png");
    public static final SkillBar mineSpeed = new SkillBar("Mining Speed",0,8),
    moveSpeed = new SkillBar("Movement Speed",1,18),
    damage = new SkillBar("Damage",2,-22),
    health = new SkillBar("Health",3,-22);
    public void act() 
    {
        // Add your action code here.
    } 
    public SkillBar(String parSkill,int parID, int parOffset)
    {
        offset = parOffset;
        skill = parSkill;
        ID = parID;
        skillBars[ID] = this; 
        button = new SkillIncreaseButton(ID);
        text = new advTextBox(parSkill,0,0,40,250,8.0f,false,new Color(10, 10, 10, 255));
        text.turn(90);
        //text.show();
        text.hide();
    }
    public void addedToWorld(World wrld)
    {
        System.out.println("WRld");
        wrld.addObject(button,getX(),getY()+39);
        text.show(getX()+15,getY()-50+offset);
        
    }
    public void addPoint()
    {
        if(level>4||PointCounter.points<1)
        {
            return;
        }
        if(MSkills.pointcounter!=null)
        MSkills.pointcounter.subtractPoints();
        
        level++;
        refreshImage();
        refresh();
    }
    public void refresh()
    {
        Health.adjustMaxHealth(20+health.level*2);
    }
    public void setLevel(int lvl,boolean readd)
    {
        if(false)
        {
            for(int i = 0; i <lvl;i++)
            {
                addPoint();
            }
        }
        else
        {
            level = lvl;
        }
        
        refreshImage();
        refresh();
    }
    public int getLevel()
    {
        return level;
    }
    public void refreshImage()
    {
        GreenfootImage base = new GreenfootImage(skillBase);
        GreenfootImage point = new GreenfootImage(skillPoint);
        for(int i = 0;i<level;i++)
        {
            base.drawImage(point,1,45-i*11);
        }
        setImage(base);
    }
    public static int getSkillLevel(int id)
    {
        return skillBars[id].level;
    }
    public void remove()
    {
        MCWorld.theWorld.removeObject(button);
        MCWorld.theWorld.removeObject(this);
        text.hide();
    }
    
}
