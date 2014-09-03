import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MCreative here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MCreative extends Module
{
    int pos = 0;
    int max;
    int lastPos = 0;
    public MCreative()
    {
        calcMax();
        showBlocks();
    }

    public void act() 
    {
        pos+=MCWorld.Scroll;
        pos=Math.max(pos,0);
        pos=Math.min(pos,max);
        if(pos!=lastPos)
        {
            updateBlocks();
        }
        lastPos = pos;
    }    

    public void calcMax()
    {
        double blocks = 0;
        for(int i = 0;i<Block.classIDs.length;i++)
        {         
            if(Block.classIDs[i]!= null)
            {
                blocks++;  
            }
                          
        }
        blocks/=9;
        max = (int)(blocks-2);
        System.out.println(max);
    }
    public void updateBlocks()
    {
        int j = 0;
        for(int i = pos*9;i<9*(3+pos);i++)
        {
            if(i<Block.classIDs.length&&Block.classIDs[i]!= null)
            {
                moduleSlots[j].setItem(i,1);
            }
            else
            {
                moduleSlots[j].setItem(0,0);
            }
            j++;   
        }
    }
    
        
        

    public void showBlocks()
    {
        int j = 0;
        moduleSlots = new Slot[9*3];
        for(int i = pos*9;i<9*(3+pos);i++)
        {
            
            int y = j/9;
            int x = j-y*9;
            if(i<Block.classIDs.length)
            {
                moduleSlots[j] = new Slot(x*36+260,y*36+225).setItem(i,1).setAsInfinite();
            }
            else
            {
                moduleSlots[j] = new Slot(x*36+260,y*36+225).setItem(0,0).setAsInfinite();
            }
            j++;
        }
    }
}
