package server;

import java.util.ArrayList;

import org.json.JSONArray;

import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ValuesResource extends ServerResource {  
	
	static ArrayList<Integer> values = new ArrayList<Integer>();
	
	@Get("json")
	public Representation getJson(Representation entity) throws Exception 
	{
		JSONArray ja = new JSONArray(values);
		setStatus(Status.SUCCESS_OK);
		return new JsonRepresentation(ja);
	}

	@Get("xml")
	public Representation getXml(Representation entity) throws Exception 
	{
    	DomRepresentation dr = new DomRepresentation(MediaType.APPLICATION_XML);
        Document d;
		d = dr.getDocument();
        Element e_values = d.createElement("values");  
        d.appendChild(e_values);
         
        for(Integer v: values)
        {
        	Element e_value = d.createElement("value");  
            e_values.appendChild(e_value);
            e_value.setTextContent(v.toString());
        }
        setStatus(Status.SUCCESS_OK);
        
        return dr; 
	}
	
	@Post
    public void doIt(String entity) throws Exception 
    {
	    //String arg = entity.getText();
	    values.add(Integer.parseInt(entity));
	    setStatus(Status.SUCCESS_CREATED);
    }
	
    @Delete
    public void deleteValue() {
        int index = Integer.parseInt(((String) getRequest().getAttributes().get("index")));
        if (index < 0 || index >= values.size())
        	setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
        else
        {
        	values.remove(index);
        	setStatus(Status.SUCCESS_NO_CONTENT);
        }
    }
    
    @Put
    public void updateValue() {
        int index = Integer.parseInt(((String) getRequest().getAttributes().get("index")));
        int v = Integer.parseInt(((String) getRequest().getAttributes().get("value")));
        
        if (index < 0 || index >= values.size())
        	setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
        else
        {
        	values.set(index,new Integer(v));
        	setStatus(Status.SUCCESS_NO_CONTENT);
        }
    }
}  