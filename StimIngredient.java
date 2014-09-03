import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StimIngredient here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StimIngredient extends Item
{
    public String getType()
    {
        return "health";
    }
    public static int getTypeOfID(int id)
    {
        StimIngredient base = (StimIngredient)Block.getBlockOf(id);
        String type = base.getType();
        if(type == "health")
        {
             return 0;
        }
        if(type == "attack")
        {
            return 1;
        }
        if(type == "defense")
        {
            return 2;
        }
        if(type == "speed")
        {
            return 3;
        }
        if(type == "black")
        {
            return 4;
        }
        return 0;
    }
        
    
}
