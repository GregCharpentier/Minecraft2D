import greenfoot.*;
/**
 * The game lags for a split second if lighting is applied in-game, so instead this class creates a bank of images for each ID for each lighting level.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LightingImageBank  
{
    // instance variables - replace the example below with your own
    public static GreenfootImage[][][] mainImageBank;
    public static GreenfootImage[][] sideImageBank;

    public static final int MAXLIGHTLEVEL = 18;//higher light level means light travels farther
    public static final int MAXDARKNESS = 230;//out of 255
    public static final GreenfootImage black = new GreenfootImage("images\\black.png");
    /**
     * Constructor for objects of class ImageBank
     */
    public static void initializeBank()
    {
        mainImageBank = new GreenfootImage[Block.classIDs.length][75][MAXLIGHTLEVEL];
        DoorBottom.initializeShading();
        for(int i = 1;i<Block.classIDs.length;i++)
        {
            genImages(i);
        }
    }

    public static GreenfootImage[] genImages(GreenfootImage image, GreenfootImage bLack)
    {
        bLack.scale(64,64);
        GreenfootImage[] images = new GreenfootImage[MAXLIGHTLEVEL];
        for(int i = 0;i<MAXLIGHTLEVEL;i++)
        {
            bLack.setTransparency(MAXDARKNESS-( i*(MAXDARKNESS/MAXLIGHTLEVEL)));
            GreenfootImage image2 = new GreenfootImage(image);
            image2.drawImage(bLack,0,0);
            images[i] = image2;

        }
        return images;
    }

    public static void genImages(int id)
    {
        Block block = Block.getBlockOf(id);
        if(block == null)
        {
            return;
        }
        if(!block.isPlaceable())
        {
            return;
        }
        int maxDam = block.maxDamageValue();
        for(int da = 0;da<=maxDam;da++)
        {
            block.setDamage(da);
            GreenfootImage bLack = block.shadeImage();
            GreenfootImage image = block.getImage();
            int[] box = block.boundingBox();
            bLack.scale(box[1]-box[0],box[3]-box[2]);
            for(int i = 0;i<MAXLIGHTLEVEL;i++)
            {
                bLack.setTransparency(MAXDARKNESS-( i*(MAXDARKNESS/MAXLIGHTLEVEL)));
                GreenfootImage image2 = new GreenfootImage(image);
                image2.drawImage(bLack,box[0],box[2]);
                mainImageBank[id][0][i] = image2;

            }
        }
        

        
    }

    /*public static void genImages(int id)//deprecated
    {
        Block block = Block.getBlockOf(id);
        if(block == null)
        {
            return;
        }
        if(!block.isPlaceable())
        {
            return;
        }
        GreenfootImage image = block.getImage();
        GreenfootImage bLack = block.shadeImage();
        int[] box = block.boundingBox();
        bLack.scale(box[1]-box[0],box[3]-box[2]);
        for(int i = 0;i<MAXLIGHTLEVEL;i++)
        {
            bLack.setTransparency(MAXDARKNESS-( i*(MAXDARKNESS/MAXLIGHTLEVEL)));
            GreenfootImage image2 = new GreenfootImage(image);
            image2.drawImage(bLack,box[0],box[2]);
            imageBank[id][0][i] = image2;

        }

    }*/
}
