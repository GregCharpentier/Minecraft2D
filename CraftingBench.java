import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CraftingBench here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CraftingBench extends Block
{
    
    /**
     * Act - do whatever the dirt wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        onFrameActions();
    }    
    public String getItemName()
    {
        return "Crafting Table";
    }
    public CraftingBench()
    {
        id = 26;
    }
    @Override
    protected void onRightClick(int id)
    {
        ModeManager.createInventoryMenu("craftingbench");       
    }  
    public String material()
    {
        return "wood";
    }

    
}
