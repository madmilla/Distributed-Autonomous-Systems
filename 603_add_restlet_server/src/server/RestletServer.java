package server;

import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.routing.VirtualHost;
//import org.restlet.service.LogService;
import org.restlet.util.ServerList;

import server.AddResource;

public class RestletServer 
{  
	Component c = new Component();
	
	public void config()
	{
	    ServerList sl = c.getServers();
	    sl.add(Protocol.HTTP, 9001);
	    VirtualHost vh = c.getDefaultHost();  
        vh.attach("/add", AddResource.class);
//      LogService ls = c.getLogService();
//	    ls.setEnabled(false);
    }
	
	public void start()
	{
	    try 
	    {
			c.start();
		} 
	    catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void main(String[] args)  
	{
		RestletServer rs = new RestletServer();
		rs.config();
		rs.start();
	}
}  