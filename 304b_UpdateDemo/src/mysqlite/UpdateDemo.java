// SelectQueryDemo.java
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
import java.io.FileReader;

public class UpdateDemo
{
    String database;
    Connection con = null;
    ResultSet rs = null;
    Statement stmt = null;
    static String queryFile;
    static String updateFile;

    UpdateDemo(String database)
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
        catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }    

        con = DriverManager.getConnection ("jdbc:sqlite:" + database);        

        showWarning(con.getWarnings());
        DatabaseMetaData dma = con.getMetaData();
        System.out.println("Connected to " + dma.getURL());
        System.out.println("Driver       " + dma.getDriverName());
        System.out.println("Version      " + dma.getDriverVersion());
        System.out.println("autocommit: " + con.getAutoCommit());
        System.out.println();
    }

    private void showResultSet(ResultSet rs) throws SQLException
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

        while (rs.next())
        {
            for (i = 1; i <= numCols; i++)
            {
                if (i > 1)
                    System.out.print(", ");
                System.out.print(rs.getString(i));
            }
            System.out.println();
        }
        System.out.println();
    }

    void runQuery(String update_query,String query) throws SQLException
    {
        stmt = con.createStatement();
        rs = stmt.executeQuery(query);
        showResultSet(rs);
        int rows = stmt.executeUpdate(update_query);
		System.out.println("rows: "+ rows);
		System.out.println();
        rs = stmt.executeQuery(query);
        showResultSet(rs);
    }

    public void run()
    {
        try
        {
            makeConnection();

            File f = new File(queryFile);
            FileReader fr = new FileReader(f);
            StringBuffer fileData = new StringBuffer(1000);
            int i;
            i = fr.read();
            while (i != -1)
            {
                fileData.append((char) i);
                i = fr.read();
            }
            fr.close();

            String query = fileData.toString();
            
            f = new File(updateFile);
            fr = new FileReader(f);
            fileData = new StringBuffer(1000);
            i = fr.read();
            while (i != -1)
            {
                fileData.append((char) i);
                i = fr.read();
            }
            fr.close();

            String update_query = fileData.toString();
            runQuery(update_query,query);
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
        queryFile = rb.getString("queryfile");
        updateFile = rb.getString("updatefile");
        
        UpdateDemo dd = new UpdateDemo(database);
        dd.run();
    }
}
