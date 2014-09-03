import greenfoot.Actor;
import greenfoot.Greenfoot;

public class ExplosiveArrow extends Arrow
{
	public CollisionReaction collisionReaction()
    {
        return CollisionReaction.SPECIAL;
    }
	
	@Override
	public boolean specialCollide(Actor a)
    {
		ExplosionProjectile.startExplosion(getX(), getY(),10,true);
		
		this.remove();
		return false;
    }
	
}
