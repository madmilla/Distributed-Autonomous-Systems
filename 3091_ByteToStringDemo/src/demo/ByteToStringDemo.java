package demo;

import java.nio.charset.Charset;


class ByteToStringDemo
{
	public static void main(String[] args)throws Exception
	{
        String s = "abc";
        
        byte[] bar = s.getBytes();
        
        System.out.println("default " + bar.length);
        for (int i=0; i<bar.length; i++)
           System.out.print(" " + bar[i]);
        System.out.println();
        
        bar = s.getBytes("UTF-16");
        
        System.out.println("UTF-16 " + bar.length);
        for (int i=0; i<bar.length; i++)
           System.out.print(" " + bar[i]);
        System.out.println();
           
        bar = s.getBytes("UTF-32");
        
        System.out.println("UTF-32 " + bar.length);
        for (int i=0; i<bar.length; i++)
           System.out.print(" " + bar[i]);
        System.out.println(); 
        System.out.println();         

        System.out.println("default charset: " + Charset.defaultCharset());
		
		System.out.println("======================");
        System.out.println("charset: default");
        System.out.println("======================");   
        
		byte[] ba = new byte[256];
		for (int i=0; i<256; i++)
		   ba[i] = (byte)i;
		   
        s = new String(ba);
//        System.out.println(s);
        byte[] ba2 = s.getBytes();
        
         
		for (int i=0; i<256; i++)
		   if (ba[i] != ba2[i])
		      System.out.println("" + i + ' ' + ba[i] + ' '+ ba2[i]);

/*           
        System.out.println("======================");
        System.out.println("charset: ASCII");
        System.out.println("======================");        


        s = new String(ba,"ASCII");      
//        System.out.println(s);
        ba2 = s.getBytes("ASCII");
        
         
        for (int i=0; i<256; i++)
           if (ba[i] != ba2[i])
              System.out.println("" + i + ' ' + ba[i] + ' '+ ba2[i]);
*/
        System.out.println("======================");
        System.out.println("charset: UTF-16");
        System.out.println("======================");    


        s = new String(ba,"UTF-16");
//        System.out.println(s);
        ba2 = s.getBytes("UTF-16");
        
         
        for (int i=0; i<256; i++)
           if (ba[i] != ba2[i])
              System.out.println("" + i + ' ' + ba[i] + ' '+ ba2[i]);      
           

        System.out.println("======================");
        System.out.println("charset: ISO-8859-1");
        System.out.println("======================");    


        s = new String(ba,"ISO-8859-1");
//        System.out.println(s);
        ba2 = s.getBytes("ISO-8859-1");
        
         
        for (int i=0; i<256; i++)
           if (ba[i] != ba2[i])
              System.out.println("" + i + ' ' + ba[i] + ' '+ ba2[i]);              
	} 
}