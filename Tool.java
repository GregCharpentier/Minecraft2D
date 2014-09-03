import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tool here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tool extends Item
{

    /**
     * Act - do whatever the Tool wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    public Vec2 getHeldPosition()
    {
        return new Vec2(42,15);
    }

    public boolean isTool()
    {
        return true;
    }
    public double getHeldScale()
    {
        return 1.5;
    }
    public String getItemName()
    {
        String mat = toolMaterial();
        String type = toolType();
        char first2 = Character.toUpperCase(type.charAt(0));
        type = first2+type.substring(1,type.length());
        char first = Character.toUpperCase(mat.charAt(0));
        mat = first+mat.substring(1,mat.length());
        return mat+" "+type;
    }
    @Override
    public int getAttackDamage()
    {
        double tierFactor = toolTier();
        double typeFactor = getTypeDamage();
        int damage = (int)((tierFactor+typeFactor*2.5)*(1+SkillBar.damage.getLevel()*.15));
        System.out.println(damage);
        return damage;
        
    }
    public int getWeaponReach()
    {
        if(toolType() == "sword")
        {
            return 26+toolTier();
        }
        return 22;
    }

    public int toolTier()
    {
        if(toolMaterial() == "wooden")
        {
             return 1;
        }
        if(toolMaterial() == "stone")
        {
            return 2;
        }
        if(toolMaterial() == "iron")
        {
            return 3;
        }
        if(toolMaterial() == "silver")
        {
            return 4;
        }
        if(toolMaterial() == "steel")
        {
            return 5;
        }
        return 0;
    }

    public String toolType()
    {
        System.out.println("You forgot to override the TOOLTYPE, silly!");
        return "pickaxe";
    }

    public int toolStrength()
    {
        
        return (toolTier()+1)*100;
    }
    public int getTypeDamage()
    {
        if(toolType() == "pickaxe")
        {
             return 2;
        }
        if(toolType() == "shovel")
        {
            return 2;
        }
        if(toolType() == "axe")
        {
            return 3;
        }
        if(toolType() == "hammer")
        {
            return 3;
        }
        if(toolType() == "sword")
        {
            return 5;
        }
        return 0;
    }

    public String toolMaterial()
    {
        System.out.println("You forgot to override the TOOLMATERIAL, silly!");
        return null;
    }
    @Override
    public int maxStackSize()
    {
    	return 1;
    }
    
}
