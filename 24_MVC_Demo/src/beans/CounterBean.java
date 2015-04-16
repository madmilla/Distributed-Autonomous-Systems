package beans;

public class CounterBean 
{
    int count = 0;

    public void setValue(int i)
    {
    	count = i;
    }
    
    public synchronized int getCurrentValue() 
    {
     	return count; 
    }
    
    public synchronized int getNextValue() 
    {
    	count++;
    	return count; 
    }
}
