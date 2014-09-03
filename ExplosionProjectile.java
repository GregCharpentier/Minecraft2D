import greenfoot.Actor;
import greenfoot.Greenfoot;


public class ExplosionProjectile extends Projectile
{
	
	@Override
	public Vec2 collisionPoint()
	{
		
		return new Vec2(0,0);
	}
	protected int lifeTime()
	{
		return 15;
	}
	@Override
	public String getImageName()
	{
		return "explosionprojectile4";
	}

	@Override
	public double gravity()
	{
		return 0;
	}

	@Override
	public CollisionReaction collisionReaction()
	{
		return Projectile.CollisionReaction.SPECIAL;
	}
	
	@Override
	public boolean rotates()
	{
		return true;
	}

	
	@Override
	public boolean specialCollide(Actor a)
    {
    	if(a instanceof Mob||a instanceof Arm)
    	{
    		return true;
    	}
    	Block block = (Block)a;
    	Block.removeArray(block.gridX(), block.gridY());
    	Block.refreshLight(block.gridX(),block.gridY(),true,true);
    	return true;
    	
    }
	
	public static void startExplosion(int x,int y,float power,boolean friendly)
	{
		int numOfProjectiles = 25+Greenfoot.getRandomNumber(20)+(int)power*2;
		
		for(int i = 0;i<numOfProjectiles;i++)
		{
			double powerModifier = .75+Math.random()/2;
			ExplosionProjectile proj = new ExplosionProjectile();
			proj.fire(x, y, Greenfoot.getRandomNumber(360),power*(float)powerModifier);
			proj.friendly = friendly;
			proj.damageFromGun = 15;
		}
		
	}
	
	public void act()
	{
		super.act();
		if(getWorld()==null)
			return;
		if(ParticleEffect.particlesOn)
		{
			if(Math.random()<.5)
				(new ParticleEffect(ParticleEffect.Particles.SMOKE)).fire(getX(), getY(), Greenfoot.getRandomNumber(360), .2f);
			(new ParticleEffect(ParticleEffect.Particles.FLARE2)).fire(getX(), getY(), this.getRotation(), .8f);
			if(Math.random()<.2)
				(new ParticleEffect(ParticleEffect.Particles.FLARE)).fire(getX(), getY(), this.getRotation(), 0);
		}
		
		
	}
	

}
