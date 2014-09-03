/**
 * Write a description of class Time here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Time  
{
    // instance variables - replace the example below with your own
    public static int time;
    public static final int MAXTIME=54000;
    public static void addTime()
    {
        time++;
        if(time == MAXTIME)
        {
            time = 0;
        }
        MCWorld.theSun.setToTime(time);
        MCWorld.theMoon.setToTime(getMoonTime(time,MAXTIME));
    }
    public static void setTime(int parTime)
    {
        time=parTime;
        if(time == MAXTIME)
        {
            time = 0;
        }
        MCWorld.theSun.setToTime(time);
        MCWorld.theMoon.setToTime(getMoonTime(time,MAXTIME));
    }
    public static int getMoonTime(int time,int maxtime)
    {
        time -= maxtime/4;
        if(time<0)
        {
            time+=maxtime;
        }
        return time;
    }
    public static int getRegTime()
    {
        int nutime = time;
        if(nutime>MAXTIME/2)
        {
            nutime-=MAXTIME/2;
        }
        return nutime*2;
    }
    public static void setRegTime(int parTime)
    {        
        setTime((parTime/2)*(MAXTIME/2400));
    }
    public static int getDaylight()//out of 500
    {
        
        return (-Math.abs(getRegTime()-MAXTIME/2)+MAXTIME/2)/(MAXTIME/1000);
        
    }
    
}
