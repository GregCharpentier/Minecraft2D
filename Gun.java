import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 */
public abstract class Gun extends SpecWeapon
{
    /**
     * Act - do whatever the Gun wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void fire() 
    {
    	if(!ModeManager.creative)
    		consumeAmmo();
        for(int i = 0;i<numberOfProjectiles();i++)
        {
            Projectile bullet = getFiredProjectile();
            Vec2 mousePos = Item.getAimVector();
            //Vec2 norm = mousePos.normalize();
            Vec2 origin = new Vec2(Player.rightarm.getX(),Player.rightarm.getY());
            //adjust length
            Vec2 firePos = origin.add(new Vec2((double)(Math.toDegrees(mousePos.getAngle())),barrelLength()));
            //adjust height
            int height = barrelHeight();
            int deviation = 0;
            if(deviation()!=0)
                deviation = Greenfoot.getRandomNumber(deviation())-deviation()/2;
            if(Head.lastDirection)    
                firePos = firePos.add(new Vec2((double)(Math.toDegrees(mousePos.getAngle()))-90,height));
            else
                firePos = firePos.subtract(new Vec2((double)(Math.toDegrees(mousePos.getAngle()))-90,height));
            float firepower = getFirepower();
            if(numberOfProjectiles()>1)
            {
                firepower*=(1+(float)Math.random()-.5f);
            }
            bullet.damageFromGun = getProjectileDamage();
            bullet.fire(firePos.X,firePos.Y,(int)Math.toDegrees(mousePos.getAngle())+deviation,firepower); 
        }

    }   

    /**
     * Returns the distance from the player that the projectile will be released
     */
    public abstract int barrelLength();

    public  int barrelHeight()
    {
        return 0;
    }

    public abstract Projectile getFiredProjectile();

    @Override
    public boolean movesArm()
    {
        return true;
    } 

    public abstract float getFirepower();

    

    public int getHeldAngle()
    {
        return -45;
    }

    public abstract int deviation();

    public int numberOfProjectiles()
    {
        return 1;
    }
    
    public float getProjectileDamage()
    {
        return 0;
    }
    
    
}
