// MakeDatabase.java
// Gemaakt door J.Kaldeway

package mysqlite;

import java.sql.Connection;
import java.sql.Statement;

import java.sql.SQLWarning;
import java.sql.SQLException;

import java.sql.DriverManager;

import java.sql.DatabaseMetaData;

import java.util.ResourceBundle;

import java.io.File;
import java.io.FileReader;

public class MakeDatabase
{
    String database;
    Connection con = null;    
    Statement stmt = null;
    static String updateFile;

    MakeDatabase(String database)
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

        // Class.forName ("org.gjt.mm.mysql.Driver");
        // con = DriverManager.getConnection ("jdbc:mysql://" + host + ":" +
        // port +
        // "/" + database, user, password);

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
        System.out.println("Driver       " + dma.getDriverName());
        System.out.println("Version      " + dma.getDriverVersion());
        System.out.println();
    }

    void runUpdate(String update) throws SQLException
    {
        System.out.println("update: " + update);
        stmt = con.createStatement();
        int i = stmt.executeUpdate(update);
        System.out.println("rows: " + i);
        System.out.println();
        stmt.close();
    }

    public void run()
    {
        try
        {
            makeConnection();

            File f = new File(updateFile);
            FileReader fr = new FileReader(f);
            int i;
            i = fr.read();
            while (i != -1)
            {
                StringBuffer fileData = new StringBuffer(1000);
                while (i != -1 && (char) i != '#')
                {
                    fileData.append((char) i);
                    i = fr.read();
                }
                String update = fileData.toString();
                if (!(update.trim().equals("")))
                   runUpdate(update);
                if ((char) i == '#')
                    i = fr.read();
            }
            fr.close();

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
        updateFile = rb.getString("updatefile");

        MakeDatabase md = new MakeDatabase(database);
        md.run();
    }
}
