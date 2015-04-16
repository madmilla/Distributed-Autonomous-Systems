package mysqlite;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class QueryTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<String[]> cache; // will hold String[] objects . . .
	int colCount;
	String[] headers;
	Connection db;
	Statement statement;
	String currentURL;

	public QueryTableModel() {
		cache = new ArrayList<String[]>();
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		// new gsl.sql.driv.Driver();
	}

	public String getColumnName(int i) {
		return headers[i];
	}

	public int getColumnCount() {
		return colCount;
	}

	public int getRowCount() {
		return cache.size();
	}

	public Object getValueAt(int row, int col) {
		return ((String[]) cache.get(row))[col];
	}

	public void setHostURL(String url) {
		if (url.equals(currentURL)) {
			// same database, we can leave the current connection open
			return;
		}
		// Oops . . . new connection required
		closeDB();
		initDB(url);
		currentURL = url;
	}

	// All the real work happens here; in a real application,
	// we'd probably perform the query in a separate thread.
	public void setQuery(String q) {
		cache = new ArrayList<String[]>();
		try {
			// Execute the query and store the result set and its metadata
			Statement stmt = db.createStatement();
			ResultSet rs = stmt.executeQuery(q);
			ResultSetMetaData meta = rs.getMetaData();
			colCount = meta.getColumnCount();

			// Now we must rebuild the headers array with the new column names
			headers = new String[colCount];
			for (int h = 1; h <= colCount; h++) {
				headers[h - 1] = meta.getColumnName(h);
			}

			// and file the cache with the records from our query. This would
			// not be
			// practical if we were expecting a few million records in response
			// to our
			// query, but we aren't, so we can do this.
			while (rs.next()) {
				String[] record = new String[colCount];
				for (int i = 0; i < colCount; i++) {
					record[i] = rs.getString(i + 1);
				}
				cache.add(record);
			}
			fireTableChanged(null); // notify everyone that we have a new table.
		} catch (Exception e) {
			cache = new ArrayList<String[]>(); // blank it out and keep going.
			e.printStackTrace();
		}
	}

	public void initDB(String url) {
		try {
			db = DriverManager.getConnection(url);
			statement = db.createStatement();
		} catch (Exception e) {
			System.out.println("Could not initialize the database.");
			e.printStackTrace();
		}
	}

	public void closeDB() {
		try {
			if (statement != null) {
				statement.close();
			}
			if (db != null) {
				db.close();
			}
		} catch (Exception e) {
			System.out.println("Could not close the current connection.");
			e.printStackTrace();
		}
	}
}
