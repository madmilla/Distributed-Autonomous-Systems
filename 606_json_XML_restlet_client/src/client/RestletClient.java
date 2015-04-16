package client;

import java.util.logging.Level;

import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

public class RestletClient {  
	 
	public void run() throws Exception
	{
    	// Create the client resource  
		
//	    String host_url = "http://localhost:9001";
	    String host_url = "http://localhost/61_Tomcat_restlet_application";	
	    
    	ClientResource resource = new ClientResource(host_url + "/values");  
    	System.out.println(resource.getReference()); 
    	
    	resource.post("12");
    	resource.post("34");
    	resource.post("56");
    	resource.post("78");
    	resource.post("90");
    	resource.post("21");    	
    	
    	resource.getLogger().setLevel(Level.ALL);
    	Representation r = resource.get(MediaType.APPLICATION_JSON);
    	System.out.println(r.getText());    
        System.out.println();
    	r = resource.get(MediaType.APPLICATION_XML);
    	System.out.println(r.getText());    
    	
    	resource = new ClientResource(host_url + "/values/3");
    	resource.delete();
    	
    	resource = new ClientResource(host_url + "/values");
    	
    	r = resource.get(MediaType.APPLICATION_JSON);
    	System.out.println(r.getText());    
//        System.out.println();
//    	r = resource.get(MediaType.APPLICATION_XML);
//    	System.out.println(r.getText());    

    	resource = new ClientResource(host_url + "/values/2/999");
    	resource.put(null);
    	
    	resource = new ClientResource(host_url + "/values");
    	
    	r = resource.get(MediaType.APPLICATION_JSON);
    	System.out.println(r.getText());    
//        System.out.println();
//    	r = resource.get(MediaType.APPLICATION_XML);
//    	System.out.println(r.getText());   
	}
	
    public static void main(String[] args) throws Exception
    {  
    	RestletClient rc = new RestletClient();
    	rc.run();
    }  
}  

