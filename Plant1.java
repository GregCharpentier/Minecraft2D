import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Plant1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plant1 extends Plant
{
  
   
	
public boolean checkIfAdvance()
    {
        if(counter>1100)
        {
            remove(x+Block.offSetX, y+Block.offSetY,this);
            System.out.println(x+":"+y);
            place(x+Block.offSetX,y+Block.offSetY,getBlockOf(Block.getBlockClass("Plant2")));
        }
        return false;
    }
    public String getItemName()
    {
        return "Plant1";
    }
    public Plant1()
    {
        id = 79;
    }
    public GreenfootImage shadeImage()
    {
        return new GreenfootImage("images\\plant1black.png");
    }
    
}
