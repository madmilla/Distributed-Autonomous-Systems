// PreparedStatementDemo.java
// Gemaakt door J.Kaldeway

package mysqlite;

import java.sql.Connection;
import java.sql.Statement;

import java.sql.SQLWarning;
import java.sql.SQLException;

import java.sql.DriverManager;

import java.sql.DatabaseMetaData;

import java.util.ResourceBundle;

import java.sql.PreparedStatement;

public class PreparedStatementDemo
{
    String database;
    Connection con = null;    
    Statement stmt = null;
    PreparedStatement pstmt = null;
    static String queryFile;

    PreparedStatementDemo(String database)
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
        // System.out.println("Driver       " + dma.getDriverName());
        // System.out.println("Version      " + dma.getDriverVersion());
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

            runUpdate("drop table if exists LEDEN_SPORTEN");
            runUpdate("drop table if exists SPORTEN");
            runUpdate("drop table if exists LEDEN");
            
            runUpdate("create table LEDEN(\n" + 
                      "LIDNR integer not null,\n" +
                      "NAAM text not null,\n" +
                      "GEB_DATUM text not null,\n" +
                      "ADRES text,\n" +
                      "POSTCODE text,\n" +
                      "WOONPLAATS text,\n" +
                      "primary key (LIDNR),\n" +
                      "unique(NAAM,GEB_DATUM,ADRES,WOONPLAATS))");
            
            runUpdate("create table SPORTEN(\n" +
                      "SPORTNR integer not null,\n" +
                      "SPORTNAAM text not null,\n" +
                      "CONTRIBUTIE real,\n" +
                      "primary key (SPORTNR),\n" +
                      "unique (SPORTNAAM))");
                      
            runUpdate("create table LEDEN_SPORTEN(\n" +
                      "LIDNR integer,\n" +
                      "SPORTNR integer,\n" +
                      "foreign key (LIDNR)\n" +
                      "   references LEDEN(LIDNR)\n" +
                      "   on delete cascade\n" +
                      "   on update cascade,\n" +
                      "foreign key (SPORTNR)\n" +
                      "   references SPORTEN(SPORTNR)\n" +
                      "   on delete cascade\n" +
                      "   on update cascade," +
                      "primary key (LIDNR,SPORTNR))");
                      
                      
            runUpdate("create trigger CASCADE_DELETE_LEDEN after delete on LEDEN\n" +
                      "begin\n" +
                      "   delete from LEDEN_SPORTEN\n" +
                      "      where LIDNR = old.LIDNR;\n" +
                      "end");
                        
            runUpdate("create trigger CASCADE_DELETE_SPORTEN after delete on SPORTEN\n" +
                      "begin\n" +
                      "   delete from LEDEN_SPORTEN\n" +
                      "      where SPORTNR = old.SPORTNR;\n" +
                      "end");
            

            runUpdate("create trigger CASCADE_UPDATE_LEDEN after update on LEDEN\n" +
                      "begin\n" +
                      "   update LEDEN_SPORTEN\n" +
                      "      set LIDNR = new.LIDNR\n" +
                      "         where LIDNR = old.LIDNR;\n" +
                      "end");

            runUpdate("create trigger CASCADE_UPDATE_SPORTEN after update on SPORTEN\n" +
                      "begin\n" +
                      "   update LEDEN_SPORTEN\n" +
                      "      set SPORTNR = new.SPORTNR\n" +
                      "         where SPORTNR = old.SPORTNR;\n" +
                      "end");

            runUpdate("create trigger FK_INSERT_LEDEN_SPORTEN before insert on LEDEN_SPORTEN\n" +
                      "for each row\n" +
                      "begin\n" +
                      "    select raise(abort,'foreign key error')\n" +
                      "      where (select LIDNR from LEDEN where LIDNR = new.LIDNR) is null\n" +
                      "        or (select SPORTNR from SPORTEN where SPORTNR = new.SPORTNR) is null;\n" +
                      "end");

            runUpdate("create trigger FK_UPDATE_LEDEN_SPORTEN before update on LEDEN_SPORTEN\n" +
                      "for each row\n" +
                      "begin\n" +
                      "    select raise(abort,'foreign key error')\n" +
                      "      where (select LIDNR from LEDEN where LIDNR = new.LIDNR) is null\n" +
                      "        or (select SPORTNR from SPORTEN where SPORTNR = new.SPORTNR) is null;\n" +
                      "end");


            PreparedStatement pstmt = null;
            String query = "insert into LEDEN(NAAM,GEB_DATUM,ADRES,POSTCODE,WOONPLAATS)\n" + 
                       "values(?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(query);
            
            pstmt.setString(1, "Henk Smit"); 
            pstmt.setString(2, "1981-05-20");
            pstmt.setString(3, "Groenweg 43");
            pstmt.setString(4, "4590 MK");
            pstmt.setString(5, "Capelle aan den IJssel");              

            int i = pstmt.executeUpdate();
            System.out.println("rows: " + i);
            System.out.println();

            pstmt.setString(1, "Piet Paulusma"); 
            pstmt.setString(2, "1985-01-16");
            pstmt.setString(3, "Sneeuwstraat 33");
            pstmt.setString(4, "4553 TY");
            pstmt.setString(5, "Utrecht");              

            i = pstmt.executeUpdate();
            System.out.println("rows: " + i);
            System.out.println();
            
            pstmt.setString(1, "Beren Botje"); 
            pstmt.setString(2, "1961-06-07");
            pstmt.setString(3, "Rechte weg 4a");
            pstmt.setString(4, "2377 PL");
            pstmt.setString(5, "Delft");              

            i = pstmt.executeUpdate();
            System.out.println("rows: " + i);
            System.out.println();
            
            pstmt.setString(1, "Truus de Hulk"); 
            pstmt.setString(2, "1951-09-14");
            pstmt.setString(3, "Langa foetoe weg 5");
            pstmt.setString(4, "3096 MN");
            pstmt.setString(5, "Eindhoven");              

            i = pstmt.executeUpdate();
            System.out.println("rows: " + i);
            System.out.println();
            
            pstmt.setString(1, "Ger Flink"); 
            pstmt.setString(2, "1982-07-02");
            pstmt.setString(3, "Non weg 56");
            pstmt.setString(4, "3033 PR");
            pstmt.setString(5, "Rotterdam");              

            i = pstmt.executeUpdate();
            System.out.println("rows: " + i);
            System.out.println();

            pstmt.setString(1, "Gertje Pluis"); 
            pstmt.setString(2, "1964-08-22");
            pstmt.setString(3, "Goudsesingel 356");
            pstmt.setString(4, "3031 KL");
            pstmt.setString(5, "Rotterdam");              

            i = pstmt.executeUpdate();
            System.out.println("rows: " + i);
            System.out.println();
            
            query = "insert into SPORTEN(SPORTNAAM,CONTRIBUTIE) values(?, ?)";
            pstmt = con.prepareStatement(query);            
            
            pstmt.setString(1, "aerobics"); 
            pstmt.setDouble(2, 210.00);

            i = pstmt.executeUpdate();
            System.out.println("rows: " + i);
            System.out.println();

            pstmt.setString(1, "basketbal"); 
            pstmt.setDouble(2, 150.00);

            i = pstmt.executeUpdate();
            System.out.println("rows: " + i);
            System.out.println();
            
            pstmt.setString(1, "bodybuilding"); 
            pstmt.setDouble(2, 300.00);

            i = pstmt.executeUpdate();
            System.out.println("rows: " + i);
            System.out.println();
            
            pstmt.setString(1, "boksen"); 
            pstmt.setDouble(2, 120.00);

            i = pstmt.executeUpdate();
            System.out.println("rows: " + i);
            System.out.println();
                        
            pstmt.setString(1, "fitness"); 
            pstmt.setDouble(2, 250.00);

            i = pstmt.executeUpdate();
            System.out.println("rows: " + i);
            System.out.println();
            
            pstmt.setString(1, "gewichtheffen"); 
            pstmt.setDouble(2, 210.00);

            i = pstmt.executeUpdate();
            System.out.println("rows: " + i);
            System.out.println();
            
            pstmt.setString(1, "tae-bo"); 
            pstmt.setDouble(2, 210.00);

            i = pstmt.executeUpdate();
            System.out.println("rows: " + i);
            System.out.println();
            
            query = "insert into LEDEN_SPORTEN values(?, ?)";
            pstmt = con.prepareStatement(query);            
            
            pstmt.setInt(1, 1); 
            pstmt.setInt(2, 4);

            i = pstmt.executeUpdate();
            System.out.println("rows: " + i);
            System.out.println(); 
            
            pstmt.setInt(1, 2); 
            pstmt.setInt(2, 7);

            i = pstmt.executeUpdate();
            System.out.println("rows: " + i);
            System.out.println(); 
            
            pstmt.setInt(1, 2); 
            pstmt.setInt(2, 1);

            i = pstmt.executeUpdate();
            System.out.println("rows: " + i);
            System.out.println(); 
            
            pstmt.setInt(1, 4); 
            pstmt.setInt(2, 5);

            i = pstmt.executeUpdate();
            System.out.println("rows: " + i);
            System.out.println(); 
            
            pstmt.setInt(1, 3); 
            pstmt.setInt(2, 5);

            i = pstmt.executeUpdate();
            System.out.println("rows: " + i);
            System.out.println(); 
            
            pstmt.setInt(1, 5); 
            pstmt.setInt(2, 2);

            i = pstmt.executeUpdate();
            System.out.println("rows: " + i);
            System.out.println(); 
            
            pstmt.setInt(1, 5); 
            pstmt.setInt(2, 3);

            i = pstmt.executeUpdate();
            System.out.println("rows: " + i);
            System.out.println(); 
            pstmt.close();
            
        
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
                pstmt.close();
                pstmt = null;
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

        PreparedStatementDemo md = new PreparedStatementDemo(database);
        md.run();
    }
}
