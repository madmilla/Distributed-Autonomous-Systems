package beans;

public class QueryBean 
{
	String query; 
	QueryHandler qh = null;
	
	public String getQuery()
    {
    	return query;
    }
    
	public void setQuery(String query)
    {
    	this.query=query;
    }
	
	public String[][] getResultTable()
	{
		String[][] t = null;
		
		try
		{
			if (qh == null)
			{
				qh = new QueryHandler(query);
			}
		    t = qh.getResultTable();
		}
		catch(Exception e) {}
			
		return t;
	}

	public String[] getLabels()
	{
		String[] t = null;
		
		try
		{
			if (qh == null)
			{
				qh = new QueryHandler(query);
			}
		    t = qh.getLabels();
		    
		}
		catch(Exception e) {t = new String[]{e.getMessage()};}
			
		return t;
	}
	
}
	
	
	

