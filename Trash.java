import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Trash here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Trash extends Slot
{
    private static final int tleftside = -36;
    private static final int trightside = 36;
    private static final int tupside = -36;
    private static final int tdownside = 36;
    /**
     * Act - do whatever the Trash wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        hoverCheck();
        clickCheck();
    }   
    public Trash(int i, int j)
    {
        super(i,j);
    }
    @Override
    protected void hoverCheck()
    {
        if(isOver())
        {
            if(hover)
            {
                
                return;
            }
            setImage("images\\thoverslot.png");
            
            hover = true;
            
        }
        else
        {
            if(!hover)
            {
                
                return;
            }
            setImage("images\\emptyslot.png");
                hover = false;
        }
        
    }
    @Override
    protected boolean isOver()
    {
        
        
        if(MCWorld.mx > tleftside + getX()&&MCWorld.mx < trightside + getX())
        {
            if(MCWorld.my > tupside + getY()&&MCWorld.my < tdownside + getY())
            {
                return true;
            }
        }
        return false;
    }
    protected String isClicked()
    {
        if(!isOver())
        {
            return "null";
        }
        
        return MCWorld.mouseDown;
    }
    protected void clickCheck()
    {
        if(isClicked()=="lp")
        {
            if(Inventory.holding)
            {
                getWorld().removeObject(Slot.heldItem);
                Slot.heldItem = null;
                
                Inventory.holding = false;
            }
            
        }
        
    }
}
