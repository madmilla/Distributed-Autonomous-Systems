package server;

import org.restlet.data.Form;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class AddResource extends ServerResource {  
	
	@Get
	public String represent() 
	{
		Form queryParams = getReference().getQueryAsForm();
		String arg1 = queryParams.getFirstValue("arg1");
		String arg2 = queryParams.getFirstValue("arg2");
		int i1 = Integer.parseInt(arg1);
		int i2 = Integer.parseInt(arg2);   
		int sum = i1+i2;
		System.out.println(sum);
		return new String("" + sum);
  }
}  