import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Item1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item1 extends Player
{
    public void act()
    {
        if(ModeManager.creative)
            return;
        Block item = Block.getBlockOf(Selector.getBlockType());
        if(item == null)
            return;
        Vec2 offset = item.getHeldPosition();
        Arm arm;
        int angleoffset = 0;
        if(isItem1())
        {
            arm = Player.rightarm;
            angleoffset = -45-item.getHeldAngle();
        }
        else
        {
            arm =Player.leftarm;
            angleoffset = 45+item.getHeldAngle();
        }
            
        setRotation(arm.getRotation()+90+angleoffset);
        Vec2 distance = new Vec2((double)arm.getRotation()+90,offset.X);
        distance = distance.add(new Vec2((double)arm.getRotation()+90+angleoffset*2,offset.Y));
        Vec2 origin = new Vec2(arm.getX(),arm.getY());
        Vec2 pos = distance.add(origin);
        this.setLocation(pos.X,pos.Y);
    }

    public boolean isItem1()
    {
        return true;
    }
}
