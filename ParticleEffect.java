import greenfoot.GreenfootImage;


public class ParticleEffect extends Projectile
{
	public Particles particle;
	public static boolean particlesOn = true;
	enum Particles{
		SMOKE,FLARE,FLARE2
	}
	
	public ParticleEffect(Particles p)
	{
		particle = p;
		setImage("images\\projectiles\\"+getImageName()+".png");
		lifeTime = lifeTime();
	}
	
	@Override
	public Vec2 collisionPoint()
	{
		return new Vec2(0,0);
	}

	@Override
	public String getImageName()
	{
		String image = "";
		if(particle == null)
			return "particleeffects\\smoke";
		switch(particle)
		{
		case SMOKE:
			image = "smoke";
			break;
		case FLARE:
			image = "flare"; 
			break;
		case FLARE2:
			image = "flare2";
			break;
			default:
				image = "smoke";  
		}
		return "particleeffects\\"+image;
	}

	@Override
	public double gravity()
	{
		if(particle == Particles.SMOKE)
			return -.015;
		return 0;
	}

	@Override
	public CollisionReaction collisionReaction()
	{
		return CollisionReaction.NO_COLLIDE;
	}
	
	@Override
	protected int lifeTime()
	{
		if(particle == null)
			return 80;
		switch(particle)
		{
		case SMOKE:
			return 80;  
		case FLARE:
			return 25; 
		case FLARE2:
			return 15; 
			default:
				return 40;  
		}
	}
	
	/**
	 * Returns the number of frames before the particle begins to fade. Should be less than or equal to lifeTime().
	 */
	protected int timeBeforeFade()
	{
		switch(particle)
		{
		case SMOKE:
			return 40;  
		case FLARE:
			return 10; 
		case FLARE2:
			return 0; 
			default:
				return 80;  
		}
	}
	/**
	 * Calculates the current
	 * @return
	 */
	public int getTransparencyAtFrame()
	{
		int timeFade = lifeTime()-lifeTime-timeBeforeFade(); 
		if(timeFade<=0)
			return 255;
		int maxTimeFade = lifeTime()-timeBeforeFade();
		return (int)(255*(1-(timeFade/(float)maxTimeFade)));
				
	}
	@Override
	public boolean rotates()
	{
		return false;
	}
	public void act()
	{
		super.act();
		GreenfootImage image = new GreenfootImage(getImage());
		int trans = getTransparencyAtFrame();
		image.setTransparency(trans);
		//System.out.println(trans);
		setImage(image);
	}
	
	

}
