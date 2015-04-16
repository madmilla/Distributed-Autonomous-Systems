// BlobDemo.java
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

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.sql.PreparedStatement;

public class BlobDemo
{
    String database;
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    BlobDemo(String database)
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
    
    String toHexString(byte[] bar)
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bar.length; i++)
        {
            int j = bar[i];
            if (j < 0) j += 256;            
            sb = sb.append(Integer.toHexString(j/16));
            sb = sb.append(Integer.toHexString(j%16));        
        }
        return new String(sb);
    }
    
    byte[] toBytes(String hexString)
    {
        int n = hexString.length();
        byte[] bar = new byte[n/2];
        int i = 0;
        while (i < n)
        {
            bar[i/2] = (byte)Integer.parseInt(hexString.substring(i,i+2),16);
            i += 2;
        }
        return bar;
    }     
    

    void runFill() throws Exception
    {
        Statement stmt = con.createStatement();
          
        int i = stmt.executeUpdate("drop table if exists BLOBDEMOTABLE");
        System.out.println("rows: " + i);           
          
        String makeTable = "create table BLOBDEMOTABLE(BLOBVB blob)";

        i = stmt.executeUpdate(makeTable);
        System.out.println("rows: " + i);                   

        String query = "insert into BLOBDEMOTABLE(BLOBVB) values(?)";
        PreparedStatement pstmt = con.prepareStatement(query);

        File hFile = new File("elephant.jpg");        
        FileInputStream fis = new FileInputStream(hFile);
        byte[] fileData = new byte[(int) hFile.length()];
        fis.read(fileData);
        fis.close();

        pstmt.setBytes(1, fileData); 
        
        i = pstmt.executeUpdate();
        System.out.println("rows: " + i);
        System.out.println();
        
        query = "insert into BLOBDEMOTABLE(BLOBVB) values(x'01020304')";
          
        System.out.println(query);                        

        i = stmt.executeUpdate(query);
        System.out.println("rows: " + i);
        System.out.println();
        
        byte[] bar = new byte[256];
        
        for (i = 0; i < 256; i++)
        {
            bar[i] = (byte)i;
        }
      
        String hexs = toHexString(bar);
               
        query = "insert into BLOBDEMOTABLE(BLOBVB) values(x'" + hexs + "')";    
        
        System.out.println(query);                        

        i = stmt.executeUpdate(query);
        System.out.println("rows: " + i);
        System.out.println();
        
        
        rs = stmt.executeQuery("select * from BLOBDEMOTABLE");
        
        int t = 0;
        while (rs.next())
        {
            t++;
            fileData = rs.getBytes(1);
            hFile = new File("reserve" + t + ".bin");
            FileOutputStream fos = new FileOutputStream(hFile);
            fos.write(fileData);
            fos.close(); 
        }
        
        rs.close();
        rs = stmt.executeQuery("select * from BLOBDEMOTABLE");        
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

        BlobDemo dd = new BlobDemo(database);
        dd.run();
    }
}
