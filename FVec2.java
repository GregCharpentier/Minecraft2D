/**
 * 2-D Vector with a float X and Y value.
 */
public class FVec2  
{
    public float X=0;
    public float Y=0;
    /**
     * Constructor for objects of class Vector2
     */
    public FVec2()
    {
        this.X = 0;
        this.Y = 0;
    }
    public FVec2(float X)
    {
        this.X = X;
        this.Y = X;
    }
    public FVec2(float X, float Y)
    {
        this.X = X;
        this.Y = Y;
    }
    public FVec2(double angle, double magnitude)
    {
        this.X = 0;
        this.Y = (float)magnitude;
        setAngle((int)angle);
    }
    public boolean equals(FVec2 v)
    {
        return this.X==v.X && this.Y==v.Y;
    }
    
    public FVec2 add(FVec2 v)
    {
        return new FVec2(this.X+v.X,this.Y+v.Y);
    }
    public FVec2 subtract(FVec2 v)
    {
        return new FVec2(this.X-v.X,this.Y-v.Y);
    }
    public FVec2 multiply(FVec2 v)
    {
        return new FVec2(this.X*v.X,this.Y*v.Y);
    }
    public FVec2 multiply(Double v)
    {
        return new FVec2((float)(this.X*v),(float)(this.Y*v));
    }
    public FVec2 divide(FVec2 v)
    {
        return new FVec2(this.X/v.X,this.Y/v.Y);
    }
    public FVec2 divide(Double v)
    {
        return new FVec2((float)(this.X/v),(float)(this.Y/v));
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
    public FVec2 normalize()
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
        X = (float)x;
        Y = (float)y;
    }
    public String toString()
    {
        return "X:"+X+", Y:"+Y;
    }
}
