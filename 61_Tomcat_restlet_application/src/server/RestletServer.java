package server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;
//import org.restlet.service.LogService;

import server.ValuesResource;

public class RestletServer extends Application {  

	@Override  
    public synchronized Restlet createInboundRoot()
	{
	    Router router = new Router(getContext());  
	    router.attach("/values", ValuesResource.class);
	    router.attach("/values/{index}", ValuesResource.class);
	    router.attach("/values/{index}/{value}", ValuesResource.class);
        return router;  
    }  
}  

