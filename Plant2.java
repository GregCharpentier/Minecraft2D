import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Plant2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plant2 extends Plant
{
    public boolean checkIfAdvance()
    {
        if(counter>1300)
        {
            remove(x+Block.offSetX, y+Block.offSetY,this);
            System.out.println(":"+x+":"+y);
            place(x+Block.offSetX,y+Block.offSetY,getBlockOf(Block.getBlockClass("Plant3")));
        }
        return false;
    }
    public String getItemName()
    {
        return "Plant2";
    }
    public Plant2()
    {
        id = 80;
    }
    public GreenfootImage shadeImage()
    {
        return new GreenfootImage("images\\plant2black.png");
    }
}
