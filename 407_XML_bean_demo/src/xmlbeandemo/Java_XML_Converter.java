package xmlbeandemo;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Java_XML_Converter {
	
	String XMLEncode(Object obj)
	{
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    XMLEncoder xmle = new XMLEncoder(baos);
	    xmle.writeObject(obj);
	    xmle.close();
	    byte[] bar = baos.toByteArray();
	    try 
	    {
			return new String(bar, "UTF-8");
		} 
	    catch (UnsupportedEncodingException uee) 
	    {
	    	return "error";
		}

	}
	
	Object XMLDecode(String str) 
	{
		Object obj = null;
		
		try
		{
			byte[] bar = str.getBytes("UTF-8");
		
			ByteArrayInputStream in = new ByteArrayInputStream(bar);
		
			XMLDecoder xmld = new XMLDecoder(in);
			obj = xmld.readObject();
			xmld.close();
		}
		catch(UnsupportedEncodingException uee){}
	    return obj;
	}
	
	public static void main(String args[])
	{
        Java_XML_Converter jxc = new Java_XML_Converter();
        
        System.out.println(jxc.XMLEncode(3));
        System.out.println(jxc.XMLEncode(3.0));
        System.out.println(jxc.XMLEncode(true));
        System.out.println("====================================");

        int[] ia = new int[]{1,2,3};
        System.out.println(Arrays.toString(ia)); 
        String s = jxc.XMLEncode(ia);
        System.out.println(s);
        int[] ia2 = (int[])jxc.XMLDecode(s);
        System.out.println(Arrays.toString(ia2));
        System.out.println("====================================");

        ArrayList<Integer> ai = new ArrayList<Integer>();
        ai.add(4);
        ai.add(5);        
        ai.add(6); 
        ai.add(5);
        System.out.println(ai);
        System.out.println(jxc.XMLEncode(ai));
        System.out.println("====================================");
        
        LinkedList<Integer> ai2 = new LinkedList<Integer>(ai);
        System.out.println(ai2);
        s = jxc.XMLEncode(ai2);
        System.out.println(s);
        @SuppressWarnings("unchecked")
		LinkedList<Integer> ai3 = (LinkedList<Integer>)jxc.XMLDecode(s);
        System.out.println(ai3);
        System.out.println("====================================");
        
        HashSet<Integer> hs = new HashSet<Integer>(ai);
        System.out.println(hs);
        s = jxc.XMLEncode(hs);
        System.out.println(s);
        @SuppressWarnings("unchecked")
		HashSet<Integer> hs2 = (HashSet<Integer>)jxc.XMLDecode(s);
        System.out.println(hs2);
        System.out.println("====================================");        
        
        HashMap<String,Integer> hm = new HashMap<String,Integer>();
        hm.put("Jan",7);
        hm.put("Kees",4);
        hm.put("Henk",6);
        hm.put("Kees",9);        
        System.out.println(hm);  
        s = jxc.XMLEncode(hm);
        System.out.println(s);
        @SuppressWarnings("unchecked")
		HashMap<String,Integer> hm2 = (HashMap<String,Integer>)jxc.XMLDecode(s);
        System.out.println(hm2);
        System.out.println("====================================");          
        
        CycleNode cn1 = new CycleNode(33,null);
        CycleNode cn2 = new CycleNode(44,null);
        CycleNode cn3 = new CycleNode(55,cn1);
        cn1.setNext(cn2);
        cn2.setNext(cn3);
        s = jxc.XMLEncode(cn1);
    	System.out.println(s);
    	CycleNode cn4 = (CycleNode)jxc.XMLDecode(s);
        System.out.println(cn4);  
        System.out.println("===================================="); 

        BST<Integer> b = new BST<Integer>(new Integer[]{1,2,3,4,5});
    	System.out.println(b);
        s = jxc.XMLEncode(b);
    	System.out.println(s);
    	@SuppressWarnings("unchecked")
		BST<Integer> b2 = (BST<Integer>)jxc.XMLDecode(s);
        System.out.println(b2);  
        System.out.println("===================================="); 
   }
}
