import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Plant3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plant3 extends Plant
{
    /**
     * Act - do whatever the Plant3 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Plant3()
    {
        id = 81;
    }
    public String getItemName()
    {
        return "Plant3";
    }
    @Override
    protected int itemOnDestroy()
    {        
        return -2;               
    }
    protected int[] itemOnDestroyMultiple()
    {
        
        int[] items = {Block.getBlockClass("StrangeSeeds"),Block.getBlockClass("Cannabis")};
        return items;
    }
    @Override
    protected int[] itemOnDestroyMultipleAmount()
    {
        int[] amounts = {Greenfoot.getRandomNumber(3),1};
        return amounts;
    }
    public GreenfootImage shadeImage()
    {
        return new GreenfootImage("images\\plant3black.png");
    }
}
