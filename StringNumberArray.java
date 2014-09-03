import java.util.ArrayList;


public class StringNumberArray 
{
	private ArrayList<int[]> arrays = new ArrayList<int[]>();
	public StringNumberArray(String string)
	{
		loadNumberArray(string);
	}
	public int getMain(int index)
	{
		return getValue(0,index);
	}
	public int getAux(int index)
	{
		return getValue(1,index);
	}
	public int getValue(int list, int index)
	{
		if(getNumberOfLists()-1<list)
			return 0;
		return arrays.get(list)[index];
	}
	public void loadNumberArray(String str)
    {
        int pos = 0;

        int[] numArray;
        int length = 0;
        while(pos< str.length())
        {
            char p = str.charAt(pos);
            if(p == ';')
            {
                length++;
            }
            pos++;
        }
        arrays.add(new int[length]);
        pos = 0;
        int numpos = 0;
        StringBuilder string = new StringBuilder("");
        int list = 0;
        while(pos< str.length())
        {
            char p = str.charAt(pos);
            if(p == ',')
            {
            	arrays.get(list)[numpos] = Integer.parseInt(string.toString());
            	list++;
            	if(getNumberOfLists()-1<list)
            		arrays.add(new int[length]);
            	string = new StringBuilder("");
            }
            else if(p == ';')
            {
            	arrays.get(list)[numpos] = Integer.parseInt(string.toString());
                string = new StringBuilder("");
                numpos++;
                list = 0;
            }
            else
            {
                string.append(p);
            }
            pos++;
        }
    }  
	public int getLength()
	{
		return arrays.get(0).length;
	}
	public int getNumberOfLists()
	{
		return arrays.size();
	}
	
}
