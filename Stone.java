import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bricks here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stone extends Block
{
    /**
     * Act - do whatever the Bricks wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        onFrameActions();
    }    
    public String getItemName()
    {
        return "Stone";
    }
    public Stone()
    {
        id = 1;
    }
    @Override
    public int itemOnDestroy()
    {
        id = Selector.getBlockType();
        Block block = getBlockOf(id);
        
        if(block == null || !block.isTool())
        {
            return 0;
        }
        Tool tool = (Tool)block;
        if(tool.toolTier() < 1 ||tool.toolType() != "pickaxe")
        {
            return 0;
        }
        return 2;
    }
    protected int getResistance()
    {
        return 8;
    }
    
}