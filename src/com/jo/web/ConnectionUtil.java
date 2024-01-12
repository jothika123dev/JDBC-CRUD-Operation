package com.jo.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//ConnectionUtil Class
public class ConnectionUtil {
	
	public static final String dburl="jdbc:mysql://localhost:3306/crud";
	public static final String dbname="root";
	public static final String dbpass="admin@123";
	
	public static Connection getConnection() throws SQLException{
		try {
  		  //used to loads the JDBC Driver
  			Class.forName("com.mysql.cj.jdbc.Driver");  		
  			
  		}catch(Exception sq) {
  			System.out.println(sq);		}
  	 //used to connect the mysql database
      return DriverManager.getConnection(dburl, dbname, dbpass);
	}

}
