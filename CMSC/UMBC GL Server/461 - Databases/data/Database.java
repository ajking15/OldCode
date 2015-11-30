package data;

import java.sql.*;

public class Database {
	private String userLogin;
	private String userPasswd;
	//String url = "jdbc:oracle:thin:@oracle2.gl.umbc.edu:1521:GL";
	String url;  // = "jdbc:oracle:thin:@localhost:1521:xe";
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	Database() {
		this.userLogin = "user";
		this.userPasswd = "pass";
		this.url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(ClassNotFoundException ex) {
			ex.printStackTrace(System.err);
			System.exit(1);
		}
	}
	
	Database(boolean oracle, String user, String pass) {
		if(oracle) {
			this.url = "jdbc:oracle:thin:@localhost:1521:xe";
		} else {
			this.url = "jdbc:oracle:thin:@oracle2.gl.umbc.edu:1521:GL";	
		}
		
		this.userLogin = user;
		this.userPasswd = pass;
		
		try {
		      Class.forName("oracle.jdbc.driver.OracleDriver");
		    } 
		    catch(ClassNotFoundException ex) {
		      ex.printStackTrace(System.err);
		      System.exit(1);
		    }
	}
	
	public void connect() {
		//no need for a return. if failure system exit.
		try {
		      con = DriverManager.getConnection(url, userLogin, userPasswd);
		      System.out.println("Connected to Oracle.");
		    }
		    catch (SQLException se ){
		      System.out.println("Unable to connect to Oracle.");
		      processError(se);
		      se.printStackTrace();
		      System.exit(1);
		    }
	}
	
	public void execSQL(String sqlStatement) {
		try {
		      this.stmt = con.createStatement();
		      this.rs =  stmt.executeQuery(sqlStatement);
		      //move through rows?
		      //stmt.close();
		    }
		    catch (SQLException ex ){
		      processError(ex);
		      System.out.println("Error while executing query.");
		      ex.printStackTrace(System.err);
		      System.exit(1);
		    }
	}
	
	public void closeSQL() {
		//JDBC statement set close() method
		try {
			this.stmt.close();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			processError(ex);
			ex.printStackTrace();
		}
		this.stmt = null;
	}
	
	public ResultSet getResults() {
		return this.rs;
	}
	
	public void closeResults() {
		//JDBC result set close() method
		try {
			this.rs.close();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			processError(ex);
			ex.printStackTrace();
		}
		this.rs = null;
	}
	
	public void printResults() {
		//Print the result set using meta data
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int numberOfColumns = rsmd.getColumnCount();
			
			while (rs.next()) {
		        for (int i = 1; i <= numberOfColumns; i++) {
		          if (i > 1) System.out.print(",  ");
		          String columnValue = rs.getString(i);
		          System.out.print(columnValue);
		        }
		        System.out.println("");  
		      }
			//rs.next() moves through rows
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			processError(ex);
			ex.printStackTrace();
		}
	}
	
	public void disconnect() {
		//make sure result set and statement have been closed
		//JDBC connection close() method
		if(stmt != null) {
			closeSQL();
		}
		if(rs != null) {
			closeResults();
		}
		
		try {
			this.con.close();
			System.out.println("Disconnected from Oracle");
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			processError(ex);
			ex.printStackTrace();
		}
		this.con = null;
	}
	
	public void processError(SQLException ex) {
		System.err.println("SQLException information");

		while(ex!=null) {
			System.err.println ("Error msg: " + ex.getMessage());    	 
			System.err.println ("SQLSTATE: " + ex.getSQLState());
			System.err.println ("Error code: " + ex.getErrorCode());
			ex.printStackTrace();
			
			// For drivers that support chained exceptions
			ex = ex.getNextException();
		} // end while	     
		System.exit(1);
	}   // end processError ()
}
