/**
 * A virtual block is an object that contains all the necessary data of a block, but cannot act like
 * a block.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VirtualBlock  
{
    private int id;
    private int damage;
    private int quantity;
    public int getID()
    {
        return id;
    }
    
    public int getDamage()
    {
        return damage;
    }
    
    public int getQuantity()
    {
        return quantity;
    }
    
    public void setID(int value)
    {
        id = value;
    }
    public VirtualBlock setDamage(int value)
    {
        damage = value;
        return this;
    }
    public VirtualBlock setQuantity(int value)
    {
        quantity = value;
        return this;
    }
    
    public VirtualBlock(int id, int quantity, int damage)
    {
        this.id = id;
        this.quantity = quantity;
        this.damage = damage;
    }
    public VirtualBlock(int id, int quantity)
    {
        this(id,quantity,0);
    }
    public VirtualBlock(String id, int quantity, int damage)
    {
        this(Block.getBlockClass(id),quantity,damage);
    }
    public VirtualBlock(String id, int quantity)
    {
        this(Block.getBlockClass(id),quantity);
    }
    
    
    public VirtualBlock(Block block)
    {
        this.id = block.id;
        this.quantity = 1;
        this.damage = block.getDamage();
    }

}
