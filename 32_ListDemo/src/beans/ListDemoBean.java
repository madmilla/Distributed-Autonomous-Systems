package beans;
import java.util.*;

public class ListDemoBean 
{
	ArrayList<String> al = new ArrayList<String>();
	
	public ArrayList<String> getList()
    {
    	return al;
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
