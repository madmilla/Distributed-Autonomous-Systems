package client;

//import java.util.logging.Level;

import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

public class RestletClient {  
  
    public static void main(String[] args) throws Exception
    {  
    	// Create the client resource  
    	ClientResource resource = new ClientResource("http://localhost:9001/add?arg1=23&arg2=54");  
    	
//    	resource.getLogger().setLevel(Level.OFF);

     	Representation r = resource.get(MediaType.TEXT_HTML);
    	System.out.println(r.getText());
       
    }  
}  