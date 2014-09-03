import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Arrays;
import java.util.ArrayList;
/**
 * Write a description of class Block here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Block extends Actor
{
    /**
     * Act - do whatever the Block wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public static Block[][] blocks = new Block[1024][1024];
    public static Wall[][] walls = new Wall[1024][1024];
    public static int[][] light = new int[1024][1024];
    private int damage;
    public Class curBlock = Dirt.class;
    public static int offSetX=250;//250
    public static int offSetY=0;//0
    protected int x;
    protected int y;
    protected int lit;
    public static double playerSpeed;
    public static int creativeMoveSpeed = 9;
    public static double playerMaxSpeed=10;
    public static int totalRelights;
    public static int verticalForce = 0;
    private static int jumpTimer = 0;
    public static int stepSoundCounter = 0;
    public static int hitSoundCounter = 0;
    protected boolean isWall = false;
    protected boolean isUnbreakable = false;
    protected boolean isPlatform = false;
    private static int armLiftCooldown = 0;
    protected int id;
    public static Class[]classIDs = new Class[160];
    public static final GreenfootImage black = new GreenfootImage("images\\black.png");
    public static int SUNLIGHT = 17; 
    private static final int bl = 4; //light blockage by solids
    private static final int BREAKSPEED = 20;
    protected BlockBorder border;
    protected boolean main = false;
    public static int drugSpeedBoost = 0;
    public static int drugSpeedBoostDegenSpeed = 40;
    public static int drugSpeedBoostDegenCounter = drugSpeedBoostDegenSpeed;

    public Block()
    {
        main = true;
        classIDs[1] = Stone.class;
        classIDs[2] = Cobblestone.class;
        classIDs[3] = Bricks.class;
        classIDs[4] = Dirt.class;
        classIDs[5] = Planks.class;
        classIDs[6] = Platform.class;
        classIDs[7] = Wood.class;
        classIDs[8] = Grass.class;
        classIDs[9] = Sand.class;
        classIDs[10] = StoneBricks.class;
        classIDs[11] = StoneWall.class;
        classIDs[12] = PlankWall.class;
        classIDs[13] = DirtWall.class;
        classIDs[14] = BrickWall.class;
        classIDs[15] = WoodWall.class;
        classIDs[16] = Seeds.class;
        classIDs[17] = Coal.class;
        classIDs[18] = Stick.class;
        classIDs[19] = CoalOre.class;
        classIDs[20] = IronOre.class;
        classIDs[21] = GoldOre.class;
        classIDs[22] = DiamondOre.class;
        classIDs[23] = Glass.class;
        classIDs[24] = MossyStoneBricks.class;
        classIDs[25] = MossyCobblestone.class;
        classIDs[26] = CraftingBench.class;
        classIDs[27] = DoorBottom.class;
        classIDs[28] = WoodenPickaxe.class;
        classIDs[29] = WoodenAxe.class;
        classIDs[30] = WoodenShovel.class;
        classIDs[31] = StonePickaxe.class;
        classIDs[32] = StoneAxe.class;
        classIDs[33] = StoneShovel.class;
        classIDs[34] = WoodenHammer.class;
        classIDs[35] = StoneHammer.class;
        classIDs[36] = Furnace.class;
        classIDs[37] = IronIngot.class;
        classIDs[38] = Basalt.class;
        classIDs[39] = BasaltWall.class;
        classIDs[40] = BasaltCobblestone.class;
        classIDs[41] = Leaves.class;
        classIDs[42] = BasaltCoalOre.class;
        classIDs[43] = BasaltSilverOre.class;
        classIDs[44] = SilverIngot.class;
        classIDs[45] = Anvil.class;
        classIDs[46] = IronPickaxe.class;
        classIDs[47] = IronAxe.class;
        classIDs[48] = IronShovel.class;
        classIDs[49] = IronHammer.class;
        classIDs[50] = SteelIngot.class;
        classIDs[51] = Impurities.class;
        classIDs[52] = OpenHearthFurnace.class;
        classIDs[53] = Limestone.class;
        classIDs[54] = LimestoneWall.class;
        classIDs[55] = SteelPickaxe.class;
        classIDs[56] = SteelAxe.class;
        classIDs[57] = SteelShovel.class;
        classIDs[58] = SteelHammer.class;
        classIDs[59] = SilverPickaxe.class;
        classIDs[60] = SilverAxe.class;
        classIDs[61] = SilverShovel.class;
        classIDs[62] = SilverHammer.class;
        classIDs[63] = Torch.class;
        classIDs[64] = Bedrock.class;
        classIDs[65] = WoodenSword.class;
        classIDs[66] = StoneSword.class; 
        classIDs[67] = IronSword.class;
        classIDs[68] = SilverSword.class;
        classIDs[69] = SteelSword.class;
        classIDs[70] = Stim.class;
        classIDs[71] = HealthStim.class;
        classIDs[72] = Cannabis.class;
        classIDs[73] = LowBase.class;
        classIDs[74] = SuperBase.class;
        classIDs[75] = StimLab.class;
        classIDs[76] = Bottle.class;
        classIDs[77] = BottleOfWater.class;
        classIDs[78] = Fountain.class;
        classIDs[79] = Plant1.class;
        classIDs[80] = Plant2.class;
        classIDs[81] = Plant3.class;
        classIDs[82] = StrangeSeeds.class;
        classIDs[83] = Chest.class;
        classIDs[84] = Sugar.class;      
        classIDs[85] = Bow.class;
        classIDs[86] = ArrowItem.class;
        classIDs[87] = Flax.class;
        classIDs[88] = StringItem.class;
        classIDs[89] = Rifle.class;
        classIDs[90] = Flamethrower.class;
        classIDs[91] = Pistol.class;
        classIDs[92] = Gunpowder.class;
        classIDs[93] = BulletItem.class;
        classIDs[94] = Propane.class;
        classIDs[95] = WoodenStock.class;
        classIDs[96] = IronGrip.class;
        classIDs[97] = RifleBarrel.class;
        classIDs[98] = PistolBarrel.class;
        classIDs[99] = RubberHose.class;
        classIDs[100] = IgnitionNozzle.class;
        classIDs[101] = Rubber.class;
    }

    public static int getBlockClass(String s)
    {
        for(int i = 0;i<classIDs.length;i++)
        {
            if(classIDs[i]!=null&&classIDs[i].toString().endsWith(" "+s))
            {
                return i;
            }
        }
        return 0;
    }

    public int getDamage()//max 75
    {
        return damage;
    }

    public char getDamageAsChar()//max {
    {
        return (char)(48+damage);
    }

    public void setDamage(int dam)//max 75
    {
        damage = Math.max(0,Math.min(dam,75));
        refreshImage();
    }
    
    public Vec2 getHeldPosition()
    {
        return new Vec2(38,5);
    }
    
    public double getHeldScale()
    {
        return 1;
    }
    
    public int getHeldAngle()
    {
        return 0;
    }

    public void refreshImage()
    {
        if(isPlaceable())
            setImage(new GreenfootImage("images\\blocks\\"+getBlockImageName()+".png"));
    }

    public String getBlockImageName()
    {
        return getItemName();
    }

    public String getIconImageName()
    {
        return getItemName();
    }

    public String getItemName()
    {
        return "missing";
    }
    public int maxStackSize()
    {
    	return 64;
    }
    public static int maxStackSize(int type)
    {
    	return getBlockOf(type).maxStackSize();
    }
    public void act() 
    {
        if(!main)
            onFrameActions();
    }  

    public int maxDamageValue()//Returns the largest damage value that the block makes use of (used for shading)
    {
        return 0;
    }

    public int getAttackSpeed()
    {
        return 12;
    }

    public int getKnockback()
    {
        return 20;
    }

    public int getAttackDamage()
    {
        return 6;
    }

    public int getWeaponReach()
    {
        return 20;
    }

    public int getSecondaryAttackSpeed()
    {
        return 12;
    }

    public int getSecondaryKnockback()
    {
        return 20;
    }

    public int getSecondaryDamage()
    {
        return 6;
    }

    public String material()
    {
        return "stone";
    }

    public void onRemove()
    {

    }

    public static void swing(boolean primary)
    {
        if(ModeManager.creative)
        {
            return;
        }
        Player.armLift();
        if(primary)
        {
            Block weapon = getHeldItem();
            armLiftCooldown = weapon.getAttackSpeed();
            ModeManager.thePlayer.attackColl.setActiveTimer(5,weapon.getAttackDamage(),weapon.getKnockback(),weapon.getWeaponReach());
        }
        else
        {
            Block weapon = getHeldItem();
            armLiftCooldown = weapon.getSecondaryAttackSpeed();
            ModeManager.thePlayer.attackColl.setActiveTimer(5,weapon.getSecondaryDamage(),weapon.getSecondaryKnockback(),weapon.getWeaponReach());
        }
    }

    public static Block getHeldItem()
    {
        Slot slot = Selector.getSelectedSlot();
        if(slot.tiedItem == null)
        {
            Block block = getBlockOf(slot.blockType,slot.blockDamage);
            if(block == null)
            {
                return getBlockOf(16);
            }
            return block;
        }
        else
        {
            return slot.tiedItem;
        }

    }

    public static Block getBlockOf(int id)
    {

        
        return getBlockOf(id,0);
    }

    public static Block getBlockOf(int id,int damage)
    {

        Block block = new Stone();
        if(id <1)
        {
            return null;
        }
        if(classIDs[id] == null)
        {
            return null;
        }
        try {

            block = (Block)classIDs[id].newInstance();
        }
        catch(java.lang.InstantiationException r){
            System.err.println("InstantiationException: " + r.getMessage());

        }
        catch(java.lang.IllegalAccessException r){
            System.err.println("IllegalAccessException: " + r.getMessage());

        }
        block.setDamage(damage);
        return block;
    }

    public static GreenfootImage getImageOf(int id)
    {
        return getBlockOf(id).getImage();
    }

    public static GreenfootImage getImageOf(int id,int dam)
    {
        if(id == 0)
        {
            return new GreenfootImage(28,30);
        }
        return getBlockOf(id,dam).getImage();
    }

    public boolean isTransparent()
    {
        return !isCollidable();
    }

    public GreenfootImage shadeImage()
    {
        return black;
    }

    public static int getLight(int x,int y)
    {
        if(x< 0 || y<0||x>1023||y>1023)
        {
            return 0;
        }
        return light[x][y];
    }

    public int lightLevel()
    {
        return 0;
    }

    private static int getLightWithOpaques(int x,int y, int templight)
    {
        int reduc = 1;
        if(x< 0 || y<0||x>1023||y>1023)
        {
            return templight;
        }
        if(getBlockAtArray(x,y)!= null && !getBlockAtArray(x,y).isTransparent())
        {
            reduc = 3;
        }
        if(getLight(x,y)-reduc>templight)
        {
            templight = getLight(x,y)-reduc;
        }
        return templight;
    }

    private static boolean isExposedToSun(int x, int y)
    {
        while(y>0)
        {
            y--;
            if(getBlockAtArray(x,y)!= null&& !getBlockAtArray(x,y).isTransparent())
            {
                return false;
            }

        }
        return true;
    }

    public static void refreshLight(int x,int y,boolean autospread,boolean canspread)
    {
        if(x< 0 || y<0||x>1023||y>1023)
        {
            return;
        }
        //int[] pp = posToArray(x,y)
        //xb = pp[0];
        //yb = pp[1];
        int templight = 0;
        if(getBlockAtArray(x,y)!= null && getBlockAtArray(x,y).isLight())
        {
            templight = getBlockAtArray(x,y).lightLevel();

        }
        if(isExposedToSun(x,y))
        {
            templight = SUNLIGHT;
        }

        templight = getLightWithOpaques(x+1,y,templight);
        templight = getLightWithOpaques(x-1,y,templight);
        templight = getLightWithOpaques(x,y+1,templight);
        templight = getLightWithOpaques(x,y-1,templight);

        if(templight < 0)
        {
            light[x][y] = 0;
        }
        if((templight != light[x][y]||autospread))
        {

            {
                light[x][y] = templight;
            }
            setLightImage(x,y);
            if(canspread)
            {
                try {
                    refreshLight(x+1,y,false,true);refreshLight(x-1,y,false,true);refreshLight(x,y+1,false,true);refreshLight(x,y-1,false,true);
                } catch(StackOverflowError t) {
                    // more general: catch(Error t)
                    // anything: catch(Throwable t)
                    //t.printStackTrace();
                }

            }
        }

    }

    public boolean isStandardBox()
    {
        int[] standard = {0,64,0,64};
        if(Arrays.equals(boundingBox(),standard))
        {
            return true;
        }
        return false;
    }

    public int gridX()
    {
        return (int)(x/64+1);
    }

    public int gridY()
    {
        return (int)(y/64+1);
    }

    public GreenfootImage getLightImage(int light)
    {
        return LightingImageBank.mainImageBank[id][damage][light];
    }

    public static void setLightImage(int x, int y)
    {
        Block block = blocks[x][y];
        Wall wall = walls[x][y];
        if(block != null)
        {
            if(true)
            {
                //                 GreenfootImage bLack = new GreenfootImage(black);
                //                 GreenfootImage base = getImageOf(block.id);
                //                 bLack.setTransparency(getLightTrans(light[x][y]));
                //                 for(int a = 0;a<100;a++)
                //                 {
                //                // base.drawImage(bLack,0,0);
                //                 }
                block.setImage(block.getLightImage(light[x][y]));

                if(block.isStandardBox())
                {
                    return;
                }
            }
        }
        if(wall != null)
        {
            if(true)
            {
                //                 GreenfootImage bLack = new GreenfootImage(black);
                //                 GreenfootImage base = getImageOf(wall.id);
                //                 bLack.setTransparency(getLightTrans(light[x][y]));
                //                 base.drawImage(bLack,0,0);
                //wall.setImage(base);
                wall.setImage(LightingImageBank.mainImageBank[wall.id][0][light[x][y]]);
            }
        }
        else
        {
            return;
        }
    }

    private static int getLightTrans(int light)
    {
        return 230- (light*10);
    }

    public boolean isLight()
    {
        return false;
    }

    public boolean isCollidable()
    {
        return true;
    }

    public static int getResistanceOf(int id)
    {
    	System.out.println("id "+id);
        return getBlockOf(id).getResistance();
    }

    private int getDestroyTime(String type, int strength, int id,int tooltier, int blocktier)
    {

        if(blocktier > tooltier)
        {
            return 10000;
        }
        if(type == "null")
        {
            return calculateDestroyTime(100,id);
        }
        if(type == "pickaxe")
        {
            for(int t = 0;t<pickaxeTypes.length;t++)
            {
                if(pickaxeTypes[t] == id)
                {
                    return calculateDestroyTime(strength,id);
                }

            }
            return calculateDestroyTime(100,id);
        }
        if(type == "axe")
        {
            for(int t = 0;t<axeTypes.length;t++)
            {
                if(axeTypes[t] == id)
                {
                    return calculateDestroyTime(strength,id);
                }

            }
            return calculateDestroyTime(100,id);
        }
        if(type == "shovel")
        {
            for(int t = 0;t<shovelTypes.length;t++)
            {
                if(shovelTypes[t] == id)
                {
                    return calculateDestroyTime(strength,id);
                }

            }
            return calculateDestroyTime(100,id);
        }
        return calculateDestroyTime(100,id);
    }

    private int calculateDestroyTime(int strength,int id)
    {
        int speedMultiplier = 260+65*SkillBar.mineSpeed.getLevel();
        return ((((10000/strength)*getResistanceOf(id))/BREAKSPEED)*100)/speedMultiplier;
    }

    public void blockOnFrame() 
    {
        if(!MCWorld.paused)
        {
            onFrame();
        }

    }  

    public boolean isPlaceable()
    {
        return true;
    }

    public boolean isTool()
    {
        return false;
    }

    protected int getResistance()
    {
        return 10;
    }

    public int toolTier()
    {

        return 0;
    }

    public int blockTier()
    {
        return -1;
    }

    private void onFrame()
    {
        onFall();
        if(MCWorld.interactEnabled)
        {
            detectMouseDown();
            keyRead();
        }

        if(jumpTimer < 20)
        {
            jumpTimer++;
        }
    }

    private void onFall()
    {
        if(ModeManager.creative)
        {
            return;
        }
        if(!Player.onGround())
        {
            verticalForce -= 1;
            stepSoundCounter=25;
        }
        else
        {
            verticalForce = 0;
            stepSoundCounter++;
            if(stepSoundCounter>25&&(Greenfoot.isKeyDown("D")||Greenfoot.isKeyDown("A")))
            {
                if(getBlockAt(ModeManager.center,ModeManager.centerY+50)!=null)
                    MCWorld.sm.playRandStep(getBlockAt(ModeManager.center,ModeManager.centerY+50).material());
                stepSoundCounter=0;
            }
            return;
        }

        offSetY += verticalForce;
        if(Player.onGround())
        {
            while(Player.onGround())
            {
                offSetY++;

            }
            verticalForce= 0;
            offSetY--;
        }
        if(Player.onCeiling()!=0)
        {
            Player.ajustTop(Player.onCeiling(),Player.headHeight);
            verticalForce = -2; 

        }
    }

    protected void onFrameActions()
    {

        setLocation(x+offSetX,y+offSetY);
        if(border != null)
        {
            border.setLocation(x+offSetX,y+offSetY);
        }

    }

    protected int itemOnDestroy()
    {
        return id;
    }

    protected int itemOnDestroyAmount()
    {
        return 1;
    }

    protected int[] itemOnDestroyMultiple()
    {

        int[] items = {id};
        return items;
    }

    protected int[] itemOnDestroyMultipleAmount()
    {
        int[] amounts = {1};
        return amounts;
    }

    protected void onRightClick(int id)
    {

    }

    protected boolean onPlace(int x, int y)
    {
        return false;
    }

    private void destroy(boolean creative,Block thatblock)
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        int[] coord = posToArray(mouse.getX(),mouse.getY());

        if(!(Selector.wall&&ModeManager.creative))
        {

            if(thatblock == null)
            {
                return;
            }
            if( thatblock.isUnbreakable == true)
            {
                return;
            }   
            if(offSetY<-500-64*15)
            {
                AchievementManager.getAchievement("Sub-Terranian").achieve();
            }
            if(thatblock.id == 53)
            {
                AchievementManager.getAchievement("Why can't I hold all these rocks?").achieve();
            }
            if(thatblock.id == 15)
            {
                AchievementManager.getAchievement("Getting Wood").achieve();
            }

            if(thatblock.itemOnDestroy() != 0)
            {
                int item = thatblock.itemOnDestroy();
                if(item == -2)
                {
                    int[] items = thatblock.itemOnDestroyMultiple();
                    int[] amounts = thatblock.itemOnDestroyMultipleAmount();
                    for(int t=0;t<items.length;t++)
                    {
                        Slot.addItems(items[t],amounts[t]);
                    }
                }
                else
                {
                    Slot.addItems(item,thatblock.itemOnDestroyAmount());
                }
            }
            remove(mouse.getX(),mouse.getY(),thatblock);
        }
        else
        {
            removeWallAt(mouse.getX(),mouse.getY());
        }
        refreshLight(coord[0],coord[1],true,true);
        //setLightImage(coord[0],coord[1]);
    }

    protected void detectMouseDown()
    {

        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse == null)
        {
            return;
        }
        {
            Block held = getHeldItem();
            if(!held.isPlaceable())
            {
                if(((Item)held).movesArm())
                {
                    Player.armAim = true;
                }
                ((Item)held).onHeld();
            }
        }

        
        
        if(MouseHelper.buttonsDown == 3)
        {
            Block newblock = getHeldItem();
            if(newblock.isTool())
            {
                if(((Tool)newblock).toolType().equals("sword"))
                {
                    if(armLiftCooldown == 0)
                    {

                        swing(false);

                    }
                    else
                    {
                        armLiftCooldown--;
                    }
                }
            }
        }
        if(MCWorld.mouseDown.equals("rp"))
        {
            Block block = getBlockAt(mouse.getX(),mouse.getY());
            if(block != null)
            {
                block.onRightClick(ModeManager.theSelector.getBlockType());
            }
            Block item = getHeldItem();
            if((item!=null)&&!item.isPlaceable())
            {
                Item item2 = (Item)item;
                item2.use();
            }
        }
        if(mouse.getButton() == 3&&(verticalForce < 5 && verticalForce > -5))
        {
            Object newobj = new Stone();
            boolean nullz = false;
            if(ModeManager.theSelector.getBlockType() <1)
            {
                return;
            }
            else{
                try {

                    newobj =classIDs[ModeManager.theSelector.getBlockType()].newInstance();
                }
                catch(java.lang.InstantiationException r){
                    System.err.println("InstantiationException: " + r.getMessage());

                }
                catch(java.lang.IllegalAccessException r){
                    System.err.println("IllegalAccessException: " + r.getMessage());

                }
            }

            Block newblock = (Block)newobj;
            if(!newblock.isPlaceable())
            {

                return;
            }
            if(newblock.isWall == false)
            {
                handPlace(mouse.getX(),mouse.getY(),newblock.id);
            }
            else
            {
                if(placeWall(mouse.getX(),mouse.getY(), (Wall)newblock)&&!ModeManager.creative)
                {
                    Slot.consumeCurrentItem();     
                }
            }
        }

        if(MouseHelper.buttonsDown == 1)
        {
        	if(!ModeManager.creative&&(new Vec2(Player.rightarm.getX()-MCWorld.mx,Player.rightarm.getY()-MCWorld.my).getMagnitude()>64*4))
            	return;
            Break.breaking = true;
            
            Block block = getBlockOf(ModeManager.theSelector.getBlockType());
            Block blockToBreak = getBlockAt(MCWorld.mx,MCWorld.my);
            if(armLiftCooldown == 0)
            {
                swing(true);

            }
            else
            {
                armLiftCooldown--;
            }
            if(ModeManager.creative)
            {
                destroy(true,getBlockAt(MCWorld.mx,MCWorld.my));
                return;
            }
            if(blockToBreak!=null)
            {                
                if(hitSoundCounter>12)
                {
                    MCWorld.sm.playRandHit(blockToBreak.material());
                    hitSoundCounter=0;
                } 
                else
                {
                    hitSoundCounter++;
                }
            }

            if(block != null && block.isTool())
            {
                Tool tool = (Tool)block;
                if(getBlockAt(MCWorld.mx,MCWorld.my)==null)
                {
                    checkWallBreak(tool, false);
                    return;
                }

                if(Break.hit(posToArray(MCWorld.mx,MCWorld.my)[0],posToArray(MCWorld.mx,MCWorld.my)[1],getDestroyTime(tool.toolType(),tool.toolStrength()
                    ,getBlockAt(MCWorld.mx,MCWorld.my).id,tool.toolTier(),getBlockAt(MCWorld.mx,MCWorld.my).blockTier()),false))
                {
                    MCWorld.sm.play(blockToBreak.material()+"place");
                    destroy(false,getBlockAt(MCWorld.mx,MCWorld.my));
                }
            }
            else
            {

                if(getBlockAt(MCWorld.mx,MCWorld.my)==null)
                {
                    checkWallBreak(null, true);
                    return;
                }
                if(Break.hit(posToArray(MCWorld.mx,MCWorld.my)[0],
                    posToArray(MCWorld.mx,MCWorld.my)[1],getDestroyTime("null",100,getBlockAt(MCWorld.mx,MCWorld.my).id,0,getBlockAt(MCWorld.mx,MCWorld.my).blockTier()),false))
                {   

                    MCWorld.sm.play(blockToBreak.material()+"place");
                    destroy(false,getBlockAt(MCWorld.mx,MCWorld.my));
                }
            }

        }
        else
        {
            hitSoundCounter=12;
            if(Break.breaking)
            {
                Break.breaking = false;
                Break.removeBreak();
            }
            if(armLiftCooldown > 0)
            {
                armLiftCooldown -= 1;
            }
        }

    }

    public static boolean handPlace(int x, int y, int id)
    {

        Block newblock = getBlockOf(id);
        if(!ModeManager.creative&&(new Vec2(Player.rightarm.getX()-MCWorld.mx,Player.rightarm.getY()-MCWorld.my)).getMagnitude()>4*64)
        {
            return false;
        }
        if(!place(x,y, newblock))
        {
            return false;
        }
        
        int[] coord = posToArray(x,y);
        int[] box = getBlockBoundingBox(coord[0],coord[1]);
        boolean infringe = false;
        boolean xinf = false;
        boolean yinf = false;
        int centerx = box[0]+32;
        int centery = box[2]+32;
        if((!ModeManager.creative&&newblock.isCollidable())&&!newblock.isPlatform)
        {
            if(box[0]<Player.rightSide+5&&box[0]>Player.leftSide-5)
                xinf = true;
            if(box[1]<Player.rightSide+5&&box[1]>Player.leftSide-5)
                xinf = true;
            if(box[2]>Player.headHeight-5&&box[2]<Player.footHeight-1)
                yinf = true;
            if(box[3]>Player.headHeight-5&&box[3]<Player.footHeight-1)
                yinf = true;
            if(centerx<Player.rightSide+5&&centerx>Player.leftSide-5)
                xinf = true;
            if(centery>Player.headHeight+5&&centery<Player.footHeight-5)
                yinf = true;
        }

        if(xinf&&yinf)
        {
            remove(x,y,newblock);
            refreshLight(coord[0],coord[1],true,true);
            setLightImage(coord[0],coord[1]);
            return false;
        }
        else
        {

            if(newblock.onPlace(coord[0],coord[1]))
            {
                remove(x,y,newblock);
                refreshLight(coord[0],coord[1],true,true);
                setLightImage(coord[0],coord[1]);
                return false;
            }
            else if(!ModeManager.creative)
            {
                Slot.consumeCurrentItem();     

            }

        }
        MCWorld.sm.play(newblock.material()+"place");
        refreshLight(coord[0],coord[1],true,true);
        setLightImage(coord[0],coord[1]);
        return true;
    }

    private void checkWallBreak(Tool tool,boolean bare)
    {
        Wall wall = getWallAt(MCWorld.mx,MCWorld.my);
        if(wall == null)
        {
            return;
        }
        if(bare)
        {
            if(wall.id == WOODWALL)
            {

                if(Break.hit(posToArray(MCWorld.mx,MCWorld.my)[0],posToArray(MCWorld.mx,MCWorld.my)[1],calculateDestroyTime(100,WOODWALL),true))
                {
                    MCWorld.sm.play(wall.material()+"place");
                    destroy(false,wall);
                }
            } 
        }
        else if(tool.toolType() == "axe")
        {
            if(wall.id == WOODWALL)
            {
                if(Break.hit(posToArray(MCWorld.mx,MCWorld.my)[0],posToArray(MCWorld.mx,MCWorld.my)[1],calculateDestroyTime(tool.toolStrength(),WOODWALL),true))
                {
                    MCWorld.sm.play(wall.material()+"place");
                    destroy(false,wall);
                }
            }
        }
        else if(tool.toolType() == "hammer") 
        {
            if(Break.hit(posToArray(MCWorld.mx,MCWorld.my)[0],posToArray(MCWorld.mx,MCWorld.my)[1],calculateDestroyTime(tool.toolStrength(),wall.id),true))
            {
                MCWorld.sm.play(wall.material()+"place");
                destroy(false,wall);
            }
        }
        else
        {
            return;
        }

        if(hitSoundCounter>12)
        {
            MCWorld.sm.playRandHit(wall.material());
            hitSoundCounter=0;
        } 
        else
        {
            hitSoundCounter++;
        }

    }

    protected void keyRead()
    {
        if(drugSpeedBoost>0)
        {
            if(drugSpeedBoostDegenCounter>0)
            {
                drugSpeedBoostDegenCounter--;
            }
            else
            {
                drugSpeedBoost--;
                drugSpeedBoostDegenCounter = drugSpeedBoostDegenSpeed;
            }
        }
        if(ModeManager.creative)
        {
            if(Greenfoot.isKeyDown("A"))
            {
                offSetX+=creativeMoveSpeed;
            }
            if(Greenfoot.isKeyDown("D"))
            {
                offSetX-=creativeMoveSpeed;       
            }
            if(Greenfoot.isKeyDown("S"))
            {
                offSetY-=creativeMoveSpeed; 
            }
            if(Greenfoot.isKeyDown("W"))
            {
                offSetY+=creativeMoveSpeed; 
            }
            return;
        }
        boolean moving = false;
        if(Greenfoot.isKeyDown("A")&&!(Greenfoot.isKeyDown("D")))
        {
            if(playerSpeed <playerMaxSpeed+SkillBar.getSkillLevel(1))
            {
                playerSpeed+=2; 
            }

            moving = true;   

            Player.stage++;
            Player.timer3=0; 

            if(Player.stage >= 24)
            {
                Player.stage = 0;
                Player.toggleMode();
            }
        }
        if(Greenfoot.isKeyDown("D")&&!(Greenfoot.isKeyDown("A")))
        {
            if(playerSpeed >-playerMaxSpeed-SkillBar.getSkillLevel(1))
            {
                playerSpeed-=2; 
            }

            moving = true;

            Player.stage++;
            Player.timer3=0; 

            if(Player.stage >= 24)
            {
                Player.stage = 0;
                Player.toggleMode();
            }
        }
        if(Greenfoot.isKeyDown("space")&&Player.onGround())
        {

            if(verticalForce < 2&&jumpTimer ==20)
            {
                offSetY +=19;
                verticalForce += Player.jumpHeight;
                jumpTimer = 0;
            }
        }
        if(Greenfoot.isKeyDown("S"))
        {
            if(getBlockAt(ModeManager.center,Player.footHeight+1) != null)
            {
                if(getBlockAt(ModeManager.center,Player.footHeight+1).isPlatform)
                    offSetY-=7;
                verticalForce -= 1;
            }
        }
        if(playerSpeed>0)
        {
            playerSpeed--; 
        }
        else if(playerSpeed<0)
        {
            playerSpeed++;
        }
        if(Player.imageNum == 12)
        {
            Player.imageNum = 0;
        }
        offSetX+=playerSpeed/2;
        int y = Player.blockCollideSide(Player.rightSide);
        if(y != 0)
        {

            Player.ajustSide(Player.rightSide,y,true);
        }
        y = Player.blockCollideSide(Player.leftSide);
        if(y != 0)
        {

            Player.ajustSide(Player.leftSide,y,false);
        }
        if(!moving)
        {
            if(Player.stage != 0)
            {
                if(Player.stage>12)
                {
                    Player.stage+=3;
                    if(Player.stage>24)
                    {
                        Player.stage = 0;
                    }
                }
                else
                {
                    Player.stage-=3;
                    if(Player.stage<0)
                    {
                        Player.stage = 0;
                    }
                }
            }

        }
    }

    private static void basePlace(int xb,int yb, Block block ,boolean dry)
    {
        block.setBlockCoord(xb*64-32,yb*64-32);
        block.border = new BlockBorder();
        if(!dry)
        {
            MCWorld.theWorld.addObject(block,block.x+offSetX,block.y+offSetY);
            if(!block.isWall)
            {
                //block.border = new BlockBorder();
                if(block.isStandardBox())
                    MCWorld.theWorld.addObject(block.border,block.x+offSetX,block.y+offSetY);
            }
        }
        if(block.isWall)
        {
            walls[xb][yb]=  (Wall)block;

        }
        else
        {
            blocks[xb][yb]= block;
        }
        block.refreshImage();
    }

    public static boolean place(int x, int y, Block block)
    {
        int[] intArray = posToArray(x,y);
        int xb = intArray[0];
        int yb = intArray[1];
        if(( blocks[xb][yb] == null))
        {
            basePlace(xb,yb,block,false);
            return true;
        }
        else
            return false;
    }

    public static boolean placeDry(int x, int y, Block block)
    {
        World wrld = MCWorld.theWorld;
        int[] intArray = posToArray(x,y);
        int xb = intArray[0];
        int yb = intArray[1];
        if(( blocks[xb][yb] == null))
        {
            basePlace(xb,yb,block,true);
            return true;
        }
        else
            return false;
    }

    public static boolean placeWithWall(int xb, int yb, Block block, Wall wall)
    {
        World wrld = MCWorld.theWorld;
        if(( blocks[xb][yb] == null))
        {
            if(walls[xb][yb] == null)
            {
                basePlace(xb,yb,wall,false);
            }
            basePlace(xb,yb,block,false);
            return true;
        }
        else
            return false;
    }

    public static boolean placeWithWallDry(int xb, int yb, Block block, Wall wall)
    {
        World wrld = MCWorld.theWorld;
        if(( blocks[xb][yb] == null))
        {
            if(walls[xb][yb] == null)
            {
                basePlace(xb,yb,wall,true);
            }
            basePlace(xb,yb,block,true);
            return true;
        }
        else
            return false;
    }

    public static boolean placeWallArray(int xb, int yb, Wall wall)
    {
        World wrld = MCWorld.theWorld;
        if(walls[xb][yb] == null)
        {
            basePlace(xb,yb,wall,false);
            return true;
        }
        else
            return false;
    }

    public static boolean placeWallArrayDry(int xb, int yb, Wall wall)
    {
        World wrld = MCWorld.theWorld;
        if(walls[xb][yb] == null)
        {
            basePlace(xb,yb,wall,true);
            return true;
        }
        else
            return false;
    }

    public static boolean placeWall(int x, int y, Wall wall)
    {
        World wrld = MCWorld.theWorld;
        int[] intArray = posToArray(x,y);
        int xb = intArray[0];
        int yb = intArray[1];

        if(walls[xb][yb] == null)
        {
            basePlace(xb,yb,wall,false);
            return true;
        }
        else
            return false;
    }

    public static boolean placeWallDry(int x, int y, Wall wall)
    {
        World wrld = MCWorld.theWorld;
        int[] intArray = posToArray(x,y);
        int xb = intArray[0];
        int yb = intArray[1];

        if(walls[xb][yb] == null)
        {
            basePlace(xb,yb,wall,true);
            return true;
        }
        else
            return false;
    }

    public static boolean placeAtArray(int xb, int yb, Block block)
    {
        World wrld = MCWorld.theWorld;
        if(( blocks[xb][yb] == null))
        {
            basePlace(xb,yb,block,false);
            return true;
        }
        else
            return false;
    }

    public static boolean placeAtArrayDry(int xb, int yb, Block block,boolean ignore)
    {
        if(( blocks[xb][yb] != null))
        {
            removeArray(xb,yb);
        }
        basePlace(xb,yb,block,true);
        return true;
    }

    public static boolean placeAtArrayDry(int xb, int yb, Block block)
    {
        if(xb<0||yb<0||xb>1023||yb>1023)
        {
            return false;
        }
        if(( blocks[xb][yb] == null))
        {
            basePlace(xb,yb,block,true);
            return true;
        }
        else
            return false;
    }

    protected static void removeBlockAt(int x, int y)
    {
        Block thisblock = getBlockAt(x,y);
        if( thisblock !=null&&!thisblock.isUnbreakable)
        {
            MCWorld.theWorld.removeObject(thisblock);
            MCWorld.theWorld.removeObject(thisblock.border);
            blocks[posToArray(x,y)[0]][posToArray(x,y)[1]]= null;

        }

    }

    protected static void removeWallAt(int x, int y)
    {
        Wall thisblock = getWallAt(x,y);
        if( thisblock !=null)
        {
            thisblock.getWorld().removeObject(thisblock);
            walls[posToArray(x,y)[0]][posToArray(x,y)[1]]= null;
        }

    }

    protected static void remove(int x, int y,Block thisblock)
    {
        if(thisblock == null||thisblock.isUnbreakable)
        {
            return;
        }
        thisblock.onRemove();
        if( thisblock.isWall)
        {
            thisblock.getWorld().removeObject(thisblock);
            walls[posToArray(x,y)[0]][posToArray(x,y)[1]]= null;
        }
        else
        {
            MCWorld.theWorld.removeObject(thisblock);
            MCWorld.theWorld.removeObject(thisblock.border);
            blocks[posToArray(x,y)[0]][posToArray(x,y)[1]]= null;
        }

    }

    protected static void removeArray(int x, int y)
    {
        if(!(1023>x&& x>0))
        {
            return;
        }
        if(!(1023>y&& y>0))
        {
            return;
        }
        Block thisblock = blocks[x][y];

        if( thisblock !=null&&!thisblock.isUnbreakable)
        {
            MCWorld.theWorld.removeObject(thisblock);
            MCWorld.theWorld.removeObject(thisblock.border);
            blocks[x][y]= null;
        }

    }

    protected static void removeWallArray(int x, int y)
    {
        World wrld = MCWorld.theWorld;
        Wall thisblock = walls[x][y];
        if( thisblock !=null)
        {
            thisblock.getWorld().removeObject(thisblock);
            walls[x][y] = null;
        }

    }

    public static Block getBlockAt(int x, int y)
    {
        int[] points = posToArray(x,y);
        return blocks[points[0]][points[1]];
    }

    public static Block getBlockAtArray(int x, int y)
    {
        return blocks[x][y];
    }

    public static Block getWallAtArray(int x, int y)
    {
        return walls[x][y];
    }

    public static Wall getWallAt(int x, int y)
    {
        int[] points = posToArray(x,y);
        return walls[points[0]][points[1]];
    }

    public static int[] posToArray(int x, int y)
    {
        x-=offSetX;//-64;
        y-=offSetY;//+192;
        int xb = -1;
        int yb = -1;
        while(x>0)
        {
            x=x-64;
            xb++;
        }
        while(y>0)
        {
            y=y-64;
            yb++;
        }
        xb++;
        yb++;
        int[] points = {xb,yb};
        return points;
    }

    public static int[] getBlockStartPoint(int x, int y)
    {

        int midx = blocks[x][y].x+offSetX;
        int midy = blocks[x][y].y+offSetY;
        midx-=32;
        midy-=32;
        int[] point = {midx,midy};
        return point;
    }

    public static int[] getBlockBoundingBox(int x, int y)
    {
        int[] startingpoint = getBlockStartPoint(x,y);
        return blocks[x][y].boundingBox(startingpoint);
    }

    public static int[] getBlockBoundingBoxCoord(int x, int y)
    {
        int[] array = posToArray(x,y);
        int xb = array[0];
        int yb = array[1];
        int[] startingpoint = getBlockStartPoint(xb,yb);
        return blocks[xb][yb].boundingBox(startingpoint);
    }

    public int[] boundingBox(int[] startingpoint)
    {
        int[] box = new int[4];
        box[0] = startingpoint[0];
        box[1] = startingpoint[0]+64;
        box[2] = startingpoint[1];
        box[3] = startingpoint[1]+64;
        return box;
    }

    public int[] boundingBox()
    {
        int[] zeros = {0,0,0,0};
        return boundingBox(zeros);
    }

    protected void setBlockCoord(int xx, int yy)
    {
        x = xx;   
        y = yy;  
    }

    public static int getVerticalForce()
    {
        return verticalForce;
    }
    static{

    }
    static final int NULL = 0, STONE = 1, COBBLESTONE = 2,BRICKS = 3, DIRT = 4, PLANKS = 5, PLATFORM = 6,WOOD = 7, GRASS = 8, SAND = 9, STONEBRICKS = 10, STONEWALL = 11, PLANKWALL = 12, DIRTWALL = 13,
    BRICKWALL = 14, WOODWALL = 15, SEEDS = 16, COAL = 17, STICK = 18, COALORE = 19, IRONORE = 20, GOLDORE = 21, DIAMONDORE = 22, GLASS = 23, MOSSYSTONEBRICKS = 24, MOSSYCOBBLESTONE = 25,
    CRAFTINGBENCH = 26, DOOR = 27, WOODENPICKAXE = 28,WOODENAXE = 29,WOODENSHOVEL = 30,STONEPICKAXE = 31,STONEAXE = 32,STONESHOVEL = 33,WOODENHAMMER = 34, STONEHAMMER = 35,FURNACE = 36,IRONINGOT = 37, 
    BASALT = 38, BASALTWALL = 39, BASALTCOBBLESTONE = 40,LEAVES = 41,BASALTCOALORE = 42,BASALTSILVERORE = 43,SILVERINGOT = 44,STEELINGOT = 50,IMPURITIES = 51,LIMESTONE = 53;

    static final int[] pickaxeTypes = {STONE,COBBLESTONE,BRICKS,COALORE,IRONORE,BASALT,BASALTCOBBLESTONE,BASALTCOALORE,BASALTSILVERORE,LIMESTONE,MOSSYSTONEBRICKS,MOSSYCOBBLESTONE};
    static final int[] shovelTypes = {DIRT,GRASS,SAND};
    static final int[] axeTypes = {PLANKS,WOOD,CRAFTINGBENCH,LEAVES};
    static final int[] axeTypesWall = {WOODWALL};
}
