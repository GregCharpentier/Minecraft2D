import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Health here.
 * 
 * @author (your name) 
 * @version (a version healthIndexer or a date)
 */
public class Health extends Actor
{
    public int state = -1;
    public boolean boost = false;
    public static GreenfootImage back = new GreenfootImage("images\\icons\\heartback.png");
    public static GreenfootImage full = new GreenfootImage("images\\icons\\heartfull.png");  
    public static GreenfootImage half = new GreenfootImage("images\\icons\\hearthalf.png");
    public static GreenfootImage backBoost = new GreenfootImage("images\\icons\\heartbackboost.png");
    public static GreenfootImage fullBoost = new GreenfootImage("images\\icons\\heartfullboost.png");  
    public static GreenfootImage halfBoost = new GreenfootImage("images\\icons\\hearthalfboost.png");

    public static Health[] healthBar = new Health[15];
    public static int maxHealth = 20;
    public static int maxBoostedHealth = 10;
    public static int health;   
    public static int boostedHealth = 0;

    public static int regenTimer = 0;    
    public static int REGENSPEED = 80;
     public static int boostDegenTimer = 0;    
    public static int boostDEGENSPEED = 110;
    public static double regenmultiplier = 1;
    public static int regenmultipliertimer = 0;
    
    public static int damageBufferDelay = 0;
    public static final int maxDamageBufferDelay = 15;

    public void resetImage()
    {
        GreenfootImage base;
        if(boost)
            base = new GreenfootImage(backBoost);
        else
            base = new GreenfootImage(back);
        if(state==1)
        {
            base.drawImage(half,0,0);
        }
        else if(state==2)
        {
            base.drawImage(full,0,0);
        }
        else if(state==3)
        {
            base.drawImage(halfBoost,0,0);
        }
        else if(state==4)
        {
            base.drawImage(fullBoost,0,0);
        }
        setImage(base);
    }
    public static int getRegenSpeed()
    {
        return (int)Math.round(REGENSPEED*regenmultiplier);
    }

    public static void onFrame()
    {
        regenTimer++;
        boostDegenTimer++;
        if(damageBufferDelay>0)
        damageBufferDelay--;
        if(regenTimer == getRegenSpeed())
        {
            regenTimer = 0;
            if(!isAtFullHealth())
            {
                heal(1);
            }
            
        }
        if(boostDegenTimer == boostDEGENSPEED)
        {
            boostDegenTimer = 0;
            if(boostedHealth>0)
            {
                damage(1);
            }                        
        }
        if(regenTimer > getRegenSpeed())
        {
            regenTimer = 0;
        }
        if(regenmultipliertimer<=0)
        {
            regenmultiplier=1;
        }
        else
        {
            regenmultipliertimer--;
        }

    }

    public static void setRegenMultiplier(int mult,int time)
    {
        regenmultipliertimer = time;
        regenmultiplier = mult;
    }

    public Health(boolean parBoost)
    {
        boost = parBoost;
    }

    public static boolean isAtFullHealth()
    {
        return health == maxHealth;
    }

    public static void adjustMaxHealth(int maxhealth)
    {
        maxHealth = maxhealth;        
        removeHealthBar();
        healthBar = new Health[(maxHealth+maxBoostedHealth)/2];
        health = Math.min(maxhealth,health);
        iniHealthBar(health);
    }

    public static void iniHealthBar(int parHealth)
    {
        int t = 0;
        for(;t<maxHealth/2;t++)
        {
            healthBar[t]=new Health(false);
        }
        for(;t<(maxHealth+maxBoostedHealth)/2;t++)
        {
            healthBar[t]=new Health(true);
        }

        health=parHealth;
        boostedHealth = 0;
        resetImageAll();
        int x = ModeManager.center-169;
        int y = ModeManager.sizeY-59;
        for(int g=0;g<healthBar.length;g++)
        {
            MCWorld.theWorld.addObject(healthBar[g],x+g*18,y);
        }
        regenTimer = 0;

    }

    public static void removeHealthBar()
    {
        for(int t=0;t<healthBar.length;t++)
        {
            MCWorld.theWorld.removeObject(healthBar[t]);
        }
    }
    public static void showHealthBar()
    {
        int x = ModeManager.center-169;
        int y = ModeManager.sizeY-59;
        for(int g=0;g<healthBar.length;g++)
        {
            MCWorld.theWorld.addObject(healthBar[g],x+g*18,y);
        }
    }

    public static void damage(int dam)
    {
    	if(damageBufferDelay>0)
    		return;
    	
        boostedHealth -=dam;
        if(boostedHealth<=0)
        {
            dam = -boostedHealth;
            boostedHealth=0;
        }
        else
        {
            dam=0;
        }
        health -=dam;
        Player.coll.setNonCollide(50);
        if(health >0)
        {
            resetImageAll();
        }
        else
        {
            health=0;
            resetImageAll();
            gameOver();
        }
        damageBufferDelay = maxDamageBufferDelay;
    }

    public static void gameOver()
    {
        ModeManager.createDeathMenu();
    }

    public static void boost(int boost)
    {
        boostedHealth += boost;
        if(boostedHealth <10+maxHealth-health)
        {
            resetImageAll();
        }
        else
        {
            boostedHealth=10+maxHealth-health;
            resetImageAll();
        }
        System.out.println("Boosted health: "+boost);
    }

    public static void heal(int heal)
    {
        health +=heal;
        if(health <maxHealth)
        {
            resetImageAll();
        }
        else
        {
            health=maxHealth;
            resetImageAll();
        }
    }

    public static void resetImageAll()
    {
        boolean boosted=false;
        int healthEnd = 0;
        int boostBuffer = 0;
        boolean setBack = false;
        for(int t=0;t<healthBar.length;t++)
        {
            int healthIndex = ((t+1)*2)-1;
            int thisstate = 0;

            if(!boosted)
            {
                if(healthBar[t].boost)
                {
                    if(health+boostedHealth+boostBuffer>healthIndex)
                    {
                        thisstate = 4;
                    }
                    else if(health+boostedHealth+boostBuffer<healthIndex)
                    {
                        thisstate = 0;
                    }
                    else if(health+boostedHealth+boostBuffer==healthIndex)
                    {
                        thisstate = 3;
                    }
                } 
                else
                {
                    if(health>healthIndex)
                    {
                        thisstate = 2;
                    }
                    if(health<healthIndex)
                    {
                        thisstate = 0;
                        boosted=true;
                        setBack = true;
                    }
                    if(health==healthIndex)
                    {
                        thisstate = 1;
                        boosted=true;
                        boostBuffer = 1;
                    }
                }
            }
            else
            {

                if(health+boostedHealth+boostBuffer>healthIndex)
                {
                    thisstate = 4;
                }
                else if(health+boostedHealth+boostBuffer<healthIndex)
                {
                    thisstate = 0;
                }
                else if(health+boostedHealth+boostBuffer==healthIndex)
                {
                    thisstate = 3;
                }

            }

            if(thisstate == healthBar[t].state)
            {

            }
            else
            {
                healthBar[t].state = thisstate;
                healthBar[t].resetImage();
            }
            if(setBack)
            {
                setBack = false;
                t--;
            }

        }
    }

}
