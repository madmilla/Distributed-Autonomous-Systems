// DateTimeDemo.java
// Gemaakt door J.Kaldeway

package demo;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class DateTimeDemo
{
    public void run()
    {
        Calendar c = new GregorianCalendar();
        c.set(2009,3,1,0,0,0);
        System.out.println(c.getTimeInMillis()); 
        System.out.println(c.getTime());
        System.out.println(new Date(c.getTimeInMillis()));
        
        c = new GregorianCalendar();
        System.out.println(c.getTimeInMillis()); 
        System.out.println(c.getTime());
        System.out.println(new Date(c.getTimeInMillis()));
        System.out.println(new Time(c.getTimeInMillis()));        
        System.out.println(new Timestamp(c.getTimeInMillis())); 
        for(int i=0;i<10;i++)
        {
            c = new GregorianCalendar();
            try{Thread.sleep(100);}catch(Exception e){}
            System.out.println(new Timestamp(c.getTimeInMillis()));
        }
        
        long tl = (c.getTimeInMillis());
        System.out.println("tl: "+tl);

        Timestamp d = new Timestamp(tl);
        
        String s = d.toString();
        System.out.println("s: "+s);
       
        try
        {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                    
            java.util.Date d2 = formatter.parse(s);
            System.out.println(d2);
            long tl2 = d2.getTime(); 
            System.out.println("tl2: "+tl2); 
            System.out.println();  
        } 
        catch(Exception e)
        { e.printStackTrace();}    
    }    
        
    public static void main(String[] args)
    {
        DateTimeDemo dtd = new DateTimeDemo();
        dtd.run();
        System.out.println("5-3-1972".compareTo("14-3-1972"));
        System.out.println("05-03-1972".compareTo("14-03-1972"));
    }
}
