// TabelInfo.java
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

public class TabelInfo
{
    String database;
    Connection con = null;
    ResultSet rs = null;
    Statement stmt = null;
    static String tabelnaam;

    TabelInfo(String database)
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
        for (i = 1; i <= numCols; i++)
        {
            if (i > 1)
                System.out.print(", ");
            System.out.print(rsmd.getColumnTypeName(i));
        }
        System.out.print("\n");
        if (rs.next())
        {
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

    void runTabelInfo(String query) throws SQLException
    {
        stmt = con.createStatement();
        rs = stmt.executeQuery("select * from " + tabelnaam);
        showResultSet(rs);
    }

    public void run()
    {
        try
        {
            makeConnection();

            runTabelInfo(tabelnaam);
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
        tabelnaam = rb.getString("tabelnaam");

        TabelInfo ti = new TabelInfo(database);
        ti.run();
    }
}
