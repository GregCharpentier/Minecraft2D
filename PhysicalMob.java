import greenfoot.*;
import java.util.List;
/**
 * Write a description of class PhysicalEntity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhysicalMob extends Mob
{
    protected int MAXSPEED;
    protected int hitInvuln = 0;
    public PhysicalMob()
    {

    }

    protected boolean checkForPlayer()
    {
        PlayerCollide box = (PlayerCollide)getOneIntersectingObject(PlayerCollide.class);
        if(box != null&&box.active)
        {
            return true;
        }
        return false;

    }

    protected void knockback(int amount)
    {
        float newspeed = -xspe;
        if(newspeed>0)
        {
            xspe=amount; 
        }
        else
        {
            xspe=-amount;
        }
        yspe  -= 8+Math.abs(amount)/4;
    }

    protected MeleeCollide checkIfHit()
    {
        if(hitInvuln>0)
        {
            hitInvuln--;
            return null;
        }
        MeleeCollide box = (MeleeCollide)getOneIntersectingObject(MeleeCollide.class);
        if(box != null&&box.active)
        {
            return box;
        }
        return null;

    }

    protected boolean  checkHit()
    {
        MeleeCollide box = checkIfHit();
        if(box == null)
        {
            return false;
        }
        hitInvuln=getHitInvuln();
        knockback(box.knockback);
        return damage(box.damage);

    }

    protected int getHitInvuln()
    {
        return 15;
    }

    protected void moveLeft()
    {
        if(xspe>-MAXSPEED)
        {
            xspe--;

        }
    }

    protected void moveRight()
    {
        if(xspe<MAXSPEED)
        {
            xspe++;

        }
    }

    public void act()
    {
        if(gravity()&&yspe<25)
        {
            yspe++;
        }
        if(moveHorizontal((int)(xspe/2)))
        {
            xspe = 0;
            onBlocked(checkGrounded());
        }
        if(moveVertical((int)(yspe/2)))
        {
            yspe = 0;
        }
        super.act();
    }

    protected void onBlocked(boolean grounded)
    {

    }

    protected boolean gravity()
    {
        return true;
    }

    public boolean moveHorizontal(int amount)
    {
        if(amount == 0)
        {
            return false; 
        }
        x+=amount;
        boolean right = amount>0;
        int x1;
        if(right)
        {
            x1 = box()[1]+(int)(x+Block.offSetX);
        }
        else
        {
            x1 = box()[0]+(int)(x+Block.offSetX);
        }

        int y1 = (int)(box()[2]+y+Block.offSetY);
        int y2 = (int)(((box()[3]-box()[2])/2)+box()[2]+y+Block.offSetY);
        int y3 = (int)(box()[3]+y+Block.offSetY);
        if(checkBlockCollision(x1,y1))
        {
            ajustSide(x1,y1,right);
            return true;
        }
        else if(checkBlockCollision(x1,y2))
        {
            ajustSide(x1,y2,right);
            return true;
        }
        else if(checkBlockCollision(x1,y3))
        {
            ajustSide(x1,y3,right);
            return true;
        }
        return false;
    }

    public boolean checkGrounded()
    {
        int y1 = (int)(box()[3]+y+Block.offSetY+1);
        int x1 = (int)(((box()[1]-box()[0])/2)+box()[0]+x+Block.offSetX);
        if(checkBlockCollision(x1,y1))
        {
            return true;
        }
        return false;
    }

    public boolean moveVertical(int amount)
    {
        if(amount == 0)
        {
            return false; 
        }
        y+=amount;
        boolean down = amount>0;
        int y1;
        if(down)
        {
            y1 = (int)(box()[3]+y+Block.offSetY);
        }
        else
        {
            y1 = (int)(box()[2]+y+Block.offSetY);
        }

        int x1 = (int)(box()[0]+x+Block.offSetX);
        int x2 = (int)(((box()[1]-box()[0])/2)+box()[0]+x+Block.offSetX);
        int x3 = (int)(box()[1]+x+Block.offSetX);

        if(checkBlockCollision(x1,y1))
        {
            ajustVertical(x1,y1,down);
            return true;
        }
        else if(checkBlockCollision(x2,y1))
        {
            ajustVertical(x2,y1,down);
            return true;
        }
        else if(checkBlockCollision(x3,y1))
        {
            ajustVertical(x3,y1,down);
            return true;
        }
        return false;
    }

    public static boolean checkBlockCollision(int xx, int yy)
    {
        Block theblock = Block.getBlockAt(xx,yy);
        if(theblock!= null&&theblock.isCollidable())
        {
            if(theblock.isPlatform == true)
            {
                return false;
            }
            return true;
        }
        return false;
    }

    public void ajustSide(int xx, int yy,boolean direction)
    {

        int[] box = Block.getBlockBoundingBox(Block.posToArray(xx,yy)[0],Block.posToArray(xx,yy)[1]);
        int offset;
        if(direction)
        {
            offset = box[0]-(xx);
            x += offset;
        }
        else
        {
            offset = box[1]-(xx)+1;
            x += offset;
        }
    }

    public List getCollision(Class cls)
    {
        return getIntersectingObjects(cls);
    }

    public void ajustVertical(int xx, int yy,boolean direction)
    {

        int[] box = Block.getBlockBoundingBox(Block.posToArray(xx,yy)[0],Block.posToArray(xx,yy)[1]);
        if(direction)
        {
            int offset = box[2]-(yy);
            //System.out.println(offset);
            y += offset;
        }
        else
        {
            int offset = box[3]-(yy);
            //System.out.println(offset);
            y += offset;
        }

    }

}
