import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Creative here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BooleanButton extends Button
{
	Field variable; 
	String text;
	Method onMethod;
	Method offMethod;
    public BooleanButton(Field var,String t,Method m1,Method m2)
    {
    	variable = var;
        if(ModeManager.blockButtons == true)
        {
            return;
        }
        onMethod = m1;
        offMethod = m2;
        text = t;
        try
		{
			if(variable.getBoolean(null))
			{
			    string = text+": On";
			    getButtonImage();
			}
			else
			{
			    string = text+": Off";
			    getButtonImage();
			}
		} catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}

    }

      

    protected void onClickAction()
    {
        if(ModeManager.blockButtons)
        {
            return;
        }

        try
		{
			if(variable.getBoolean(null))
			{
			    variable.setBoolean(null,false);
			    string = text+": Off";
			    getButtonImage();
			    if(offMethod!=null)
			    try
				{
					offMethod.invoke(null);
				} catch (InvocationTargetException e)
				{
					e.printStackTrace();
				}
			    
			    
			}
			else
			{
				variable.setBoolean(null,true);
			    string = text+": On";
			    getButtonImage();
			    if(onMethod!=null)
			    try
				{
					onMethod.invoke(null);
				} catch (InvocationTargetException e)
				{
					e.printStackTrace();
				}
			}
		} catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}

    }
}
