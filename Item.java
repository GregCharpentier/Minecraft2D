import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item extends Block
{
    /**
     * Act - do whatever the Item wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    @Override
    public boolean isPlaceable()
    {
        return false;
    }
    public void use()
    {
        
    }
    
    public void onHeld()
    {
        
    }
    /**
     * Returns true if the players arm aims at the cursor when held
     */
    public boolean movesArm()
    {
        return false;
    }
    public void adjustArm()
    {
        if(movesArm())
        {
            getAimVector();
        }
    }
    public static Vec2 getAimVector()
    {
        return new Vec2(MCWorld.mx-Player.rightarm.getX(),MCWorld.my-Player.rightarm.getY());
    }
}
