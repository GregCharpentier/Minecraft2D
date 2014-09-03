import greenfoot.*;
public class KeyboardHandler  
{
    private static boolean[] lastKeys = new boolean[10];
    private static boolean[] theseKeys = new boolean[10];
    
    public static void act()
    {
        lastKeys = theseKeys;
        theseKeys = new boolean[10];
        for(int i = 0;i<10;i++)
        {
            if(Greenfoot.isKeyDown(""+i))
            {
                theseKeys[i] = true;
            }
        }
    }
    
    public static boolean isKeyPressed(int key)
    {
        return theseKeys[key] && !lastKeys[key];
    }
    
    public static int getPressed()
    {
        for(int i = 0;i<10;i++)
        {
            if(isKeyPressed(i))
                return i;
        }
        return -1;
    }
   
}
