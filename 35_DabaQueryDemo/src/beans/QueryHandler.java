package beans;

// QueryDemo.java
// Gemaakt door J.Kaldeway

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.DriverManager;

import java.sql.ResultSetMetaData;

import java.util.LinkedList;
import java.util.Iterator;

public class QueryHandler
{
   Connection con = null;
   String query;
   String[][] resultTable = null;
   String[] labels = null;
   
   QueryHandler(String query)
   {
      this.query = query;
   }
   
   void makeConnection() throws SQLException
   {
   
       try
       {
           Class.forName ("org.sqlite.JDBC");
       }
       catch(Exception e){}
       String url = "jdbc:sqlite:" + "d:/sqlite/database/sportvereniging.sqlite";
       con = DriverManager.getConnection(url);
   }
   
   void closeConnection() throws SQLException
   {
         con.close();
   }
   
   public void doQuery() throws SQLException
   {
	    makeConnection();
        Statement stmt = con.createStatement ();
        ResultSet rs = stmt.executeQuery (query);
        LinkedList<String[]> resultList = new LinkedList<String[]>();

        ResultSetMetaData rsmd = rs.getMetaData ();

        int numCols = rsmd.getColumnCount ();

        String[] labelrow = new String[numCols];

        for (int i=1; i<=numCols; i++) 
        {
           labelrow[i-1] = rsmd.getColumnLabel(i);
        }

        this.labels = labelrow;
        boolean more = rs.next ();
        while (more) 
        {
           String[] row = new String[numCols];
           for (int i=1; i<=numCols; i++) 
           {
              row[i-1] = rs.getString(i);
           }
           resultList.add(row); 
           more = rs.next ();
        }
         
        String[][] resultTable = new String[resultList.size()][numCols];
        Iterator<String[]> li = resultList.iterator();
        int i = 0;
        while (li.hasNext())
        {
            resultTable[i] = li.next();
            i = i+1;
        }  
        rs.close();
        stmt.close();
        closeConnection();
        
        this.resultTable = resultTable;
   } 
   
   public String[] getLabels() throws SQLException
   {
	    if (labels ==null)
	    	doQuery();
	    return labels;
   }

   public String[][] getResultTable() throws SQLException
   {
	    if (resultTable ==null)
	    	doQuery();
	    return resultTable;
   }
   
}
  
