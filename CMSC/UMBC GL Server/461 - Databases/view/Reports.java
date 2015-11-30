package view;

import java.sql.*;

public class Reports {

	public static void SummaryBySeller(ResultSet rs) {
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int numberOfColumns = rsmd.getColumnCount();
			
			
			for(int i = 1; i <= numberOfColumns; i++) {
				if(i > 1) {
					System.out.print(String.format(" | %-10s", rsmd.getColumnLabel(i)));
				} else {
					System.out.print(String.format("%-15s", rsmd.getColumnLabel(i)));
				}
			}
			System.out.println("");
			for(int x = 0; x < 95; x++) {
				System.out.print("-");
			}
			System.out.println("");
			while (rs.next()) {
		        for (int i = 1; i <= numberOfColumns; i++) {
		        	String columnValue = rs.getString(i);

	        		if (i > 1){
	        			System.out.print(String.format(" | %10s", columnValue));
	        		} else {
	        			if(columnValue == null) {
	        				for(int x = 0; x < 95; x++) {
	        					System.out.print("-");
	        				}
	        				System.out.println("");
	        				System.out.print(String.format("%-15s", ""));
	        			} else {
	        				System.out.print(String.format("%-15s", columnValue));
	        			}
	        		}
	        	//String columnValue = rs.getString(i);
	          
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
	
	private static void processError(SQLException ex) {
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
