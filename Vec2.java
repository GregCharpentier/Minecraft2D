/**
 * 2-D Vector with an X and Y value.
 */
public class Vec2  
{
    public int X=0;
    public int Y=0;
    /**
     * Constructor for objects of class Vector2
     */
    public Vec2()
    {
        this.X = 0;
        this.Y = 0;
    }
    public Vec2(int X)
    {
        this.X = X;
        this.Y = X;
    }
    public Vec2(int X, int Y)
    {
        this.X = X;
        this.Y = Y;
    }
    public Vec2(double angle, int magnitude)
    {
        this.X = 0;
        this.Y = magnitude;
        setAngle((int)angle);
    }
    public boolean equals(Vec2 v)
    {
        return this.X==v.X && this.Y==v.Y;
    }
    
    public Vec2 add(Vec2 v)
    {
        return new Vec2(this.X+v.X,this.Y+v.Y);
    }
    public Vec2 subtract(Vec2 v)
    {
        return new Vec2(this.X-v.X,this.Y-v.Y);
    }
    public Vec2 multiply(Vec2 v)
    {
        return new Vec2(this.X*v.X,this.Y*v.Y);
    }
    public Vec2 multiply(Double v)
    {
        return new Vec2((int)(this.X*v),(int)(this.Y*v));
    }
    public Vec2 divide(Vec2 v)
    {
        return new Vec2(this.X/v.X,this.Y/v.Y);
    }
    public Vec2 divide(Double v)
    {
        return new Vec2((int)(this.X/v),(int)(this.Y/v));
    }
    /**
     * Returns the angle of this vector in radians.
     */
    public double getAngle()
    {
        return Math.atan2(Y,X);
    }
    /**
     * Returns the length of this vector.
     */
    public double getMagnitude()
    {
        return Math.sqrt(Math.pow(X,2)+Math.pow(Y,2));
    }
    public Vec2 normalize()
    {
        return divide(getMagnitude());
    }
    public double normalizeX()
    {
        return X/getMagnitude();
    }
    
    public double normalizeY()
    {
        return Y/getMagnitude();
    }
    public void setAngle(int degrees)
    {
        double mag = getMagnitude();
        double x = Math.cos(Math.toRadians(degrees))*mag;
        double y = Math.sin(Math.toRadians(degrees))*mag;
        X = (int)Math.round(x);
        Y = (int)Math.round(y);
    }
    public String toString()
    {
        return "X:"+X+", Y:"+Y;
    }
}
