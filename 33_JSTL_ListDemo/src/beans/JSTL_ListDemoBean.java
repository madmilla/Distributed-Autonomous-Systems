package beans;
import java.util.*;

public class JSTL_ListDemoBean 
{
	ArrayList<String> al = new ArrayList<String>();
	
	public ArrayList<String> getList()
    {
    	return al;
    }
    
	public int[] getList2()
    {
    	return new int[]{100,200,300};
    }
   
	public void setList(ArrayList<String> al)
    {
    	this.al=al;
    }
 
	public int getLen()
    {
    	return al.size();
    }
}
	
	
	

