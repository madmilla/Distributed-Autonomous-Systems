// AutoCommitDemo.java
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

public class AutoCommitDemo
{
    String database;
    Connection con = null;
    ResultSet rs = null;
    Statement stmt = null;

    AutoCommitDemo(String database)
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

    void runQuery(String query) throws SQLException
    {
        stmt = con.createStatement();
        rs = stmt.executeQuery(query);
        showResultSet(rs);
    }

    void runUpdate(String update) throws SQLException
    {
        System.out.println("update: " + update);
        Statement stmt = con.createStatement();
        int i = stmt.executeUpdate(update);
        System.out.println("return-code: " + i);
        System.out.println();
        stmt.close();
    }

    public void run()
    {
        try
        {
            makeConnection();

            con.setAutoCommit(false);
            System.out.println("autocommit: " + con.getAutoCommit());

            runUpdate("delete from LEDEN where NAAM = 'Beren Botje'");

            runQuery("select * from LEDEN");

            con.rollback();
            System.out.println("rollback");
            System.out.println();

            runQuery("select * from LEDEN");

            con.setAutoCommit(true);
            System.out.println("autocommit: " + con.getAutoCommit());

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

        AutoCommitDemo acd = new AutoCommitDemo(database);
        acd.run();
    }
}
