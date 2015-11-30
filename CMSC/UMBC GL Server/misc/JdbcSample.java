//package jdbc;
//Sample Java program for interacting with oracle at GL.

import java.sql.*;

public class JdbcSample {

  public static void main(String[] args) {
    String userLogin = null, userPasswd = null;
userLogin = "chrmai1";
userPasswd = "chaos8789";
/*
    if (args.length < 2) {
      System.err.println("usage :: java JdbcSample <username> <passwd>");
      System.exit(1);
    } 
    else {
      userLogin = args[0];
      userPasswd = args[1];
    }
    */
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
    } 
    catch(ClassNotFoundException ex) {
      ex.printStackTrace(System.err);
      System.exit(1);
    }

    String url = "jdbc:oracle:thin:@oracle.gl.umbc.edu:1521:GL";
    //String url = "jdbc:oracle:thin:@localhost:1521:xe";
    Connection con = null;
    try {
      con = DriverManager.getConnection(url, userLogin, userPasswd);
      System.out.println("Connected to Oracle.");
    }
    catch (SQLException se ){
      System.out.println("Unable to connect to Oracle.");
      se.printStackTrace();
      System.exit(1);
    }
    
    System.out.println("Tables for user " + userLogin);
    try {
      Statement stmt = con.createStatement();
      ResultSet rs =  stmt.executeQuery("select table_name from user_tables");
      while ( rs.next() ) {
        String name = rs.getString(1);
        System.out.println(name);
      }
      stmt.close();
    }
    catch (SQLException ex ){
      System.out.println("Error while executing query.");
      ex.printStackTrace(System.err);
      System.exit(1);
    }
  }
}        
