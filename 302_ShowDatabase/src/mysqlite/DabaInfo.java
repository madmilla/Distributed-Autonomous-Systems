// DabaInfo.java
// Gemaakt door J.Kaldeway

package mysqlite;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.SQLWarning;
import java.sql.SQLException;

import java.sql.DriverManager;

import java.sql.DatabaseMetaData;
import java.sql.ResultSetMetaData;

import java.util.ResourceBundle;

public class DabaInfo
{
    String database;
    Connection con = null;
    ResultSet rs = null;
    static String queryFile;

    DabaInfo(String database)
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
        ResultSetMetaData rsmd = rs.getMetaData();

        System.out.println(rsmd.getColumnLabel(1) + ", "
                + rsmd.getColumnLabel(3));

        boolean more = rs.next();
        while (more)
        {
            System.out.println(rs.getString(1) + ", " + rs.getString(3));
            more = rs.next();
        }
        System.out.println();
    }

    private void showResultSet2(ResultSet rs) throws SQLException
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
        boolean more = rs.next();
        while (more)
        {
            for (i = 1; i <= numCols; i++)
            {
                if (i > 1)
                    System.out.print(", ");
                System.out.print(rs.getString(i));
            }
            System.out.println();

            more = rs.next();
        }
        System.out.println();
    }

    void showDabaInfo() throws SQLException
    {
        if (con != null)
        {
            DatabaseMetaData dma = con.getMetaData();

            rs = dma.getTables(null, null, null, new String[] { "TABLE" });
            showResultSet(rs);

            rs = dma.getTables(null, null, null, new String[] { "TABLE" });
            showResultSet2(rs);
        }
    }

    public void run()
    {
        try
        {
            makeConnection();

            showDabaInfo();

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

        DabaInfo di = new DabaInfo(database);
        di.run();
    }
}
