import java.util.ArrayList;
/**
 * Manages achievements
 * 
 * @author (your name) 
 * @version (a version number or a date)
 * 
 */
public class AchievementManager  
{
    // instance variables - replace the example below with your own
    public static ArrayList<Achievement> achievements = new ArrayList<Achievement>();

    public static void initialize()
    {
        achievements = new ArrayList<Achievement>();
        achievements.add(new Achievement("Getting Wood", 
        "Attack a tree until a","block of wood pops out",
        new Vec2(0,0),true,null,1));
        
        achievements.add(new Achievement("Benchmarking", 
        "Craft a workbench with","four blocks of planks",
        new Vec2(80,0),true,getAchievement("Getting Wood"),1));
        
        achievements.add(new Achievement("Time to Mine!", 
        "Use planks and sticks","to make a pickaxe",
        new Vec2(-80,120),true,getAchievement("Benchmarking"),1));
        
        achievements.add(new Achievement("Hot Topic", 
        "Construct a furnace","out of eight stone blocks",
        new Vec2(-80,0),true,getAchievement("Time to Mine!"),1));
        
        achievements.add(new Achievement("Time to Strike!", 
        "Use planks and sticks","to make a sword",
        new Vec2(0,-120),true,getAchievement("Benchmarking"),1));
        
        achievements.add(new Achievement("Zombie Slayer", 
        "Wait until night and","kill a zombie",
        new Vec2(80,0),true,getAchievement("Time to Strike!"),1));
        
        achievements.add(new Achievement("Sub-Terranian", 
        "Mine 15 blocks","underground",
        new Vec2(0,80),true,getAchievement("Time to Mine!"),1));
        
        achievements.add(new Achievement("Hammer Time!", 
        "Craft an anvil with","5 iron ingots",
        new Vec2(-40,-120),false,getAchievement("Hot Topic"),1));
        
        achievements.add(new Achievement("Why can't I hold all these rocks?", 
        "Mine into the limestone","layer",
        new Vec2(-80,0),false,getAchievement("Hammer Time!"),1));
        
        achievements.add(new Achievement("We Need More Heat", 
        "Craft an open-hearth","furnace to make steel",
        new Vec2(0,-80),false,getAchievement("Hammer Time!"),1));
        
        achievements.add(new Achievement("Time to Hunt!", 
                "Craft a bow with ","sticks and flax",
                new Vec2(0,-80),false,getAchievement("Time to Strike!"),1));
        
        achievements.add(new Achievement("Time to Shoot!", 
                "Craft a rifle with ","a stock and barrel",
                new Vec2(0,-80),false,getAchievement("Time to Hunt!"),1));
        
        achievements.add(new Achievement("Mmph mph-mph mmmmph!", 
                "\"Mmph mmmph mph-mph","m-mmph mmmmph!\"",
                new Vec2(0,-80),false,getAchievement("Time to Shoot!"),1));
    }
    public static Achievement getAchievement(String ach)
    {
        for(Achievement a : achievements)
        {
            if(a.name.equals(ach))
                return a;
        }
        System.out.println("!No such achievement!");
        return null;
    }
    public static boolean achieve(String achievement)
    {
        return getAchievement(achievement).achieve();
    }
}
