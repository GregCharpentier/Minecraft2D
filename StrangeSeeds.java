import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StrangeSeeds here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StrangeSeeds extends Item
{
    public void use()
    {
       Block b = Block.getBlockAt(MCWorld.mx,MCWorld.my+64);
       if(b!=null&&(b.id == 4||b.id==8))
            Block.handPlace(MCWorld.mx,MCWorld.my,getBlockClass("Plant1"));
    }
    public String getItemName()
    {
        return "Strange Seeds";
    }
    
}
