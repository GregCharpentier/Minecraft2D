import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DoorBottom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DoorBottom extends Block
{
    protected boolean open = true;
    protected boolean removing = false;
    private DoorBottom aux;
    public boolean top;
    private static GreenfootImage[][] shading= new GreenfootImage[4][LightingImageBank.MAXLIGHTLEVEL];
    public void act() 
    {
        onFrameActions();
    }    
    public String getItemName()
    {
        return "Door";
    }

    public DoorBottom()
    {
        id = 27;
        setImage(getLightImage(Block.light[gridX()][gridY()]));
        
    }

    public DoorBottom(DoorBottom bottom)
    {
        id = 27;
        aux = bottom;
        top = true;
        setImage(getLightImage(Block.light[gridX()][gridY()]));
    }
    
    public int[] boundingBox(int[] startingpoint)
    {
        int[] box = new int[5];
        box[0] = startingpoint[0];
        box[1] = startingpoint[0]+64;
        box[2] = startingpoint[1];
        box[3] = startingpoint[1]+64;
        box[4] = 5;
        return box;
    }
    
    public static void initializeShading()
    {
        shading[0] = LightingImageBank.genImages(new GreenfootImage("images\\blocks\\doortop.png"),new GreenfootImage("images\\doortopblack.png"));
        shading[1] = LightingImageBank.genImages(new GreenfootImage("images\\blocks\\doorbottom.png"),new GreenfootImage((LightingImageBank.black)));
        shading[2] = LightingImageBank.genImages(new GreenfootImage("images\\blocks\\doortopside.png"),new GreenfootImage("images\\doorsideblack.png"));
        shading[3] = LightingImageBank.genImages(new GreenfootImage("images\\blocks\\doorbottomside.png"),new GreenfootImage("images\\doorsideblack.png"));
    }

    @Override
    protected void onRightClick(int id)
    {
        toggle();
        aux.toggle();       
    }  

    protected void toggle()
    {
        if(open)
        {
            open = false;     
            setImage(getLightImage(Block.light[gridX()][gridY()]));
        }
        else
        {
            open = true;
            setImage(getLightImage(Block.light[gridX()][gridY()]));
        }
    }
    
    public GreenfootImage getLightImage(int light)
    {
        if(top)
        {
            if(open)
            {
                return shading[0][light];
            }
            else
            {
                return shading[2][light];
            }
        }
        else
        {
            if(open)
            {
                return shading[1][light];
            }
            else
            {
                return shading[3][light];
            }
        }
    }

    protected boolean onPlace(int x, int y)
    {
        if(!top)
        {
            aux = new DoorBottom(this);
            return !placeAtArray(x,y-1,aux);
        }
        else
        {
             return false;
        }

    }
    
    public void onRemove()
    {
        if(aux != null&&aux.removing == false)
        {
            removing = true;
            removeArray(aux.gridX(),aux.gridY()); 
        }
    }

    public boolean isCollidable()
    {
        if(open)
        {
            return false;
        }
        else
        {

            return true;
        }
    }
}
