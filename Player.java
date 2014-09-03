import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    public static final int headHeight = ModeManager.centerY -90;//23
    public static final int rightSide=ModeManager.center +15;
    public static final int leftSide = ModeManager.center-16;
    public static final int footHeight = ModeManager.centerY +19;
    public static final int jumpHeight = 17;
    public static final GreenfootImage black = new GreenfootImage("images\\black1.png");
    public static final GreenfootImage arm = new GreenfootImage("images\\playerarm.png");
    public static final GreenfootImage leg = new GreenfootImage("images\\playerlegs.png");
    public static final GreenfootImage body = new GreenfootImage("images\\playerbody.png");
    public static int armLift = 0;
    /**
     * Block sets this variable to true if the held item make the arm aim at the cursor
     */
    public static boolean armAim = false;
    public static final Color clear = arm.getColorAt(0,0);//new Color(0,0,0,0) ;

    public static int imageNum = 0;
    public static int stage = 0;
    public static int mode = 1;
    public static int timer3 = 0;
    public static Arm rightarm;
    public static Arm2 leftarm;
    public static Leg rightleg;
    public static Leg2 leftleg;
    public static Head rightItem;
    public static Item1 item1 = new Item1();
    public static Item2 item2 = new Item2();
    public static PlayerCollide coll;
    public static MeleeCollide attackColl;
    public Player()
    {

    }

    public  void initializeLimbs()
    {
        MCWorld.theWorld.addObject(this, ModeManager.center, ModeManager.centerY -36);
        rightarm = new Arm();
        MCWorld.theWorld.addObject(rightarm,ModeManager.center,ModeManager.centerY -53);
        rightleg = new Leg();
        MCWorld.theWorld.addObject(rightleg,ModeManager.center-1,ModeManager.centerY -13);
        leftarm = new Arm2();
        MCWorld.theWorld.addObject(leftarm,ModeManager.center,ModeManager.centerY -53);
        leftleg = new Leg2();
        MCWorld.theWorld.addObject(leftleg,ModeManager.center-1,ModeManager.centerY -13);
        coll = new PlayerCollide();       
        MCWorld.theWorld.addObject(coll,getX(),ModeManager.centerY -33);
        attackColl = new MeleeCollide();
        MCWorld.theWorld.addObject(attackColl,getX(),ModeManager.centerY -33);
        MCWorld.theWorld.addObject(item1,rightarm.getX(),rightarm.getY());
        MCWorld.theWorld.addObject(item2,rightarm.getX(),rightarm.getY());
        Selector.paintItem();
    }
    
    
    public static void armLift()
    {
        armLift = 18;   
    }

    @Override
    protected void addedToWorld(World wrld)
    {

    }

    public void removePlayer()
    {
        stage = 0;
        MCWorld.theWorld.removeObject(rightarm);
        MCWorld.theWorld.removeObject(rightleg);
        MCWorld.theWorld.removeObject(leftarm);
        MCWorld.theWorld.removeObject(leftleg);
        MCWorld.theWorld.removeObject(item1);
        MCWorld.theWorld.removeObject(item2);
        MCWorld.theWorld.removeObject(attackColl);
        MCWorld.theWorld.removeObject(coll);
    }

    public void act() 
    {
        if(ModeManager.creative)
        {
            return;
        }
        if(rightarm == null)
        {
            return;
        }
        if(armLift >0)
        {
            armLift--;
        }
        setPlayerImage();

        //setPlayerBrightness();
    }  

    private void setPlayerBrightness()//  doesn't work/laggy
    {

        int brightness = Block.light[Block.posToArray(getX()+20,getY()+50)[0]][Block.posToArray(getX()+20,getY()+50)[1]];
        GreenfootImage shader = new GreenfootImage(black);
        shader.setTransparency(LightingImageBank.MAXDARKNESS-( brightness*(LightingImageBank.MAXDARKNESS/LightingImageBank.MAXLIGHTLEVEL)));

        GreenfootImage Arm = preciseDarken(arm,shader);
        GreenfootImage Leg = preciseDarken(leg,shader);
        GreenfootImage Body = preciseDarken(body,shader);
        rightarm.setImage(Arm);
        rightleg.setImage(Leg);
        leftarm.setImage(Arm);
        leftleg.setImage(Leg);
        setImage(Body);
    }

    private static GreenfootImage preciseDarken(GreenfootImage parImage,GreenfootImage shader)//  doesn't work/laggy
    {
        GreenfootImage image = new GreenfootImage(parImage);
        for(int x = 0;x<image.getWidth();x++)
        {
            for(int y = 0;y<image.getHeight();y++)
            {
                if(clear.equals(image.getColorAt(x,y)))
                {
                    image.drawImage(shader,x,y);
                }
            }
        }
        return image;
    }

    public static void toggleMode()
    {
        if(mode == 1)
        {
            mode = -1;
        }
        else
        {
            mode = 1;
        }
    }

    public void setPlayerImage()
    {
        int ang = (-3*Math.abs(stage-12))+36;
        rightarm.setRotation(ang*mode);
        leftarm.setRotation((ang*-1)*mode);
        rightleg.setRotation(ang*mode);
        leftleg.setRotation((ang*-1)*mode);
        if(armAim)
        {
            int liftAng = (int)(Math.toDegrees(Item.getAimVector().getAngle()));
			liftAng+=90;
			liftAng=liftAng%360;
			if (liftAng < 0)
			{
				liftAng += 360;
			}
			if(liftAng>=180)
				liftAng = 360-liftAng;
				
            if(!Head.checkMouse())
            {
                leftarm.setRotation(-(liftAng+180));
            }
            else
            {
                rightarm.setRotation(liftAng+180);
            }
            armAim = false;
        }
        else if(armLift != 0)
        {
            int liftAng = -(7*armLift);
            if(!Head.checkMouse())
            {
                leftarm.setRotation(-liftAng);
            }
            else
            {
                rightarm.setRotation(liftAng);
            }
        }
    }

    public static boolean onGround()
    {
        if(Block.getVerticalForce()>2)
        {
            return false;
        }
        Block theblock = Block.getBlockAt(rightSide-1,footHeight);
        if(theblock != null&&theblock.isCollidable())
        {
            if(theblock.isPlatform)
            {
                if( Block.getVerticalForce()>0)
                {
                    return false;
                } 
                else if((footHeight-Block.getBlockBoundingBoxCoord(rightSide-1,footHeight)[2])>8-Block.getVerticalForce())
                {
                    return false;
                }
            }
            return true;
        }

        theblock = Block.getBlockAt(leftSide+1,footHeight);
        if(theblock != null&&theblock.isCollidable())
        {
            if(theblock.isPlatform && Block.getVerticalForce()>0)
            {   
                if( Block.getVerticalForce()>0)
                {
                    return false;
                } 
                else if((footHeight-Block.getBlockBoundingBoxCoord(leftSide+1,footHeight)[2])>8-Block.getVerticalForce())
                {
                    return false;
                }                
            }

            return true;
        }
        return false;
    }

    public static int onCeiling()
    {

        if(Block.getBlockAt(ModeManager.center+13,headHeight) != null&&Block.getBlockAt(ModeManager.center+13,headHeight).isCollidable())
        {

            if(Block.getBlockAt(ModeManager.center+13,headHeight).isPlatform == true)
            {
                return 0;
            }
            return ModeManager.center+13;
        }
        if(Block.getBlockAt(ModeManager.center-15,headHeight) != null&&Block.getBlockAt(ModeManager.center-15,headHeight).isCollidable())
        {

            if(Block.getBlockAt(ModeManager.center-15,headHeight).isPlatform == true)
            {
                return 0;
            }
            return ModeManager.center-15;
        }
        return 0;

    }

    public boolean blockCollide(int x,int y)
    {
        return (Block.getBlockAt(getX()+x,getY()-y) != null&&Block.getBlockAt(getX()+x,getY()-y).isCollidable());
    }

    public static void ajustSide(int x,int y, boolean direction)
    {
        if(x==0)
        {
            return;
        }

        int[] box = Block.getBlockBoundingBox(Block.posToArray(x,y)[0],Block.posToArray(x,y)[1]);
        if(direction)
        {
            int offset = box[0]-(x);
            Block.offSetX -= offset;
        }
        else
        {
            int offset = box[1]-(x);
            Block.offSetX -= offset;
        }

    }

    public static void ajustTop(int x,int y)
    {
        int[] box = Block.getBlockBoundingBox(Block.posToArray(x,y)[0],Block.posToArray(x,y)[1]);
        int offset = box[3]-(y);
        Block.offSetY -= offset;
    }

    public static int blockCollideSide(int x)
    {

        Block theblock = Block.getBlockAt(x,footHeight-1);
        if(theblock!= null&&theblock.isCollidable())
        {
            if(theblock.isPlatform == true)
            {
                return 0;
            }
            return footHeight-1;
        }
        theblock = Block.getBlockAt(x,footHeight+(headHeight-footHeight)/2);
        if(theblock!= null&&theblock.isCollidable())
        {
            if(theblock.isPlatform == true)
            {
                return 0;
            }
            return footHeight+(headHeight-footHeight)/2;
        }
        theblock = Block.getBlockAt(x,headHeight+1);
        if(theblock!= null&&theblock.isCollidable())
        {
            if(theblock.isPlatform == true)
            {
                return 0;
            }
            return headHeight+1;
        }
        return 0;
    }

}
