package JSONDemo;

import org.json.*;

class Json_demo2
{
	public void run()
	{
		try
		{
			String s = 
			"<?xml version=\"1.0\" standalone=\"no\"?>\n" +
			"	<root>\n" +
			"    	<person id=\"1\">\n" +
			"         	<name>Alan</name>\n" + 
			"         	<url>http://www.google.com</url>\n" +
			"    	</person>\n" + 
			"    	<person id=\"2\">\n" +
			"         	<name>Louis</name>\n" + 
			"         	<url>http://www.yahoo.com</url>\n" +
			"    	</person>\n" +
			"</root>";
			
     		// XML -> JSON
			JSONObject jo = XML.toJSONObject(s);
			System.out.println(jo);
			
			// JSON -> XML
			String xml_header = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
			String s2 = xml_header + XML.toString(jo);
			System.out.println(s2);
     	} 
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] arg)
	{
		Json_demo2 jd = new Json_demo2();
		jd.run();
	}
}