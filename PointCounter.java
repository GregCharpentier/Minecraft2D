import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
/**
 * Write a description of class PointCounter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PointCounter extends advTextBox
{
    public static int points = 0;
    public void act() 
    {
        // Add your action code here.
    }  

    public PointCounter(int x, int y,int parheight, int parwidth)
    {
        super(points+" Points Remaining",x,y,parheight,parwidth,8.0f,false,new Color(60, 60, 139, 255)); 
        System.out.println(parwidth+"::"+parheight);
    }
    public void setPoints(int point)
    {
        points = point;
        text = points+" Points Remaining";
        getTextImage(text);
    }
    public void addPoints()
    {
        points += 1;
        text = points+" Points Remaining";
        getTextImage(text);
    }
    public void subtractPoints()
    {
        points -= 1;
        text = points+" Points Remaining";
        getTextImage(text);
    }

    }
