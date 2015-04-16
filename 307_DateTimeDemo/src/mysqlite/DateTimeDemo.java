// DateTimeDemo.java
// Gemaakt door J.Kaldeway

package mysqlite;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import java.sql.SQLWarning;
import java.sql.SQLException;

import java.sql.DriverManager;

import java.sql.DatabaseMetaData;
import java.sql.ResultSetMetaData;

import java.util.ResourceBundle;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateTimeDemo
{
    String database;
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    DateTimeDemo(String database)
    {
        this.database = database;
    }

    private void showWarning(SQLWarning warn)
    {
        if (warn != null)
        {
            System.out.println("*** Warning ***");
            while (warn != null)
            {
                System.out.println("Message:  " + warn.getMessage());
                warn = warn.getNextWarning();
            }
        }
    }

    void ShowSQLException(SQLException ex)
    {
        System.out.print("*** SQLException ***");
        while (ex != null)
        {
            System.out.println("Message:  " + ex.getMessage());
            ex = ex.getNextException();
        }
    }

    void makeConnection() throws SQLException
    {

        try
        {
            Class.forName("org.sqlite.JDBC");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        con = DriverManager.getConnection("jdbc:sqlite:" + database);

        showWarning(con.getWarnings());
        DatabaseMetaData dma = con.getMetaData();
        System.out.println("Connected to " + dma.getURL());
        System.out.println();
    }
    
    private void showTabelInfo(ResultSet rs) throws SQLException
    {
        int i;

        ResultSetMetaData rsmd = rs.getMetaData();

        int numCols = rsmd.getColumnCount();

        for (i = 1; i <= numCols; i++)
        {
            if (i > 1)
                System.out.print(", ");
            System.out.print(rsmd.getColumnLabel(i));
        }
        System.out.println();
        if (rs.next())
        {
            rsmd = rs.getMetaData();
            for (i = 1; i <= numCols; i++)
            {
               if (i > 1)
                   System.out.print(", ");
               System.out.print(rsmd.getColumnTypeName(i));
            }
            System.out.println();
            for (i = 1; i <= numCols; i++)
            {
                if (i > 1)
                    System.out.print(", ");
                System.out.print(rs.getObject(i).getClass().getName());
            }
            System.out.println();
        }
        System.out.println();
    }

    void runFill() throws Exception
    {
        Statement stmt = con.createStatement();
          
        int i = stmt.executeUpdate("drop table if exists DEMOTABLE");
        System.out.println("rows: " + i);           
          
        String makeTable = "create table DEMOTABLE(" +
                             "DATEVB text,"+
                             "TIMEVB text,"+
                             "TIMESTAMPVB text)";
                             
     
  
        i = stmt.executeUpdate(makeTable);
        System.out.println("rows: " + i);                   

        Calendar c = new GregorianCalendar();
        Date datevb = new Date(c.getTimeInMillis());
        Time timevb = new Time(c.getTimeInMillis());
        Timestamp timestampvb = new Timestamp(c.getTimeInMillis()); 
                
        // datevb, timevb en timstampvb moeten omsloten worden door "        
                       
        String query = "insert into DEMOTABLE(DATEVB,TIMEVB,TIMESTAMPVB)" + 
                       "values(" + "\""+ datevb  + "\",\"" + timevb + 
                                   "\",\"" + timestampvb + "\")";
        System.out.println(query);                        

        i = stmt.executeUpdate(query);
        System.out.println("rows: " + i);
        System.out.println();
        
        Thread.sleep(1100);
        
        // datetime('now','localtime') berekent de huidige tijd
        
        query = "insert into DEMOTABLE(DATEVB,TIMEVB,TIMESTAMPVB) " + 
                        "values(" +  "\""+ datevb + "\",\"" + timevb + 
                                     "\"," + "datetime('now','localtime')" + ")";
        System.out.println(query);                        

        i = stmt.executeUpdate(query);
        System.out.println("rows: " + i);
        System.out.println();
        
        // strftime(\"%Y-%m-%d %H:%M:%f\",'now','localtime') toonnt de huidige tijd op 
        // dezelfde manier als Java. 
        
        query = "insert into DEMOTABLE(DATEVB,TIMEVB,TIMESTAMPVB) " + 
                        "values(" +  "\""+ datevb + "\",\"" + timevb + 
                         "\"," + "strftime(\"%Y-%m-%d %H:%M:%f\",'now','localtime')" + ")";

        System.out.println(query);                        

        i = stmt.executeUpdate(query);
        System.out.println("rows: " + i);
        System.out.println();

        rs = stmt.executeQuery("select * from DEMOTABLE");
        while (rs.next())
        {
            String datevbstr = rs.getString(1);
            String timevbstr = rs.getString(2);
            String timestampvbstr = rs.getString(3);
                
            System.out.println("" + datevbstr + " " + timevbstr + " " + timestampvbstr);
        }
        System.out.println();
        
        rs.close();
        System.out.println();
        rs = stmt.executeQuery("select * from DEMOTABLE");        
        showTabelInfo(rs);
        
        
    }

    public void run()
    {
        try
        {
            makeConnection();

            runFill();
        }
        catch (SQLException ex1)
        {
            ShowSQLException(ex1);
        }
        catch (Exception ex2)
        {
            System.out.println(ex2.getMessage()); // ex2.printStackTrace();
        }
        finally
        {
            try
            {
                rs.close();
                rs = null;
            }
            catch (Exception e)
            {
            }
            try
            {
                stmt.close();
                stmt = null;
            }
            catch (Exception e)
            {
            }
            try
            {
                con.close();
                con = null;
                System.out.println("connection closed");
            }
            catch (Exception e)
            {
            }
        }
    }

    public static void main(String[] args)
    {

        ResourceBundle rb = ResourceBundle.getBundle("daba");

        String database = rb.getString("database");

        DateTimeDemo dd = new DateTimeDemo(database);
        dd.run();
    }
}
