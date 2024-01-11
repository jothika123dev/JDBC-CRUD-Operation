package com.jo.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/crud";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin@123";
  
   //main method
    public static void main(String args[]) throws SQLException {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter your choice:\n");
        System.out.println("1.insert \n");
        System.out.println("2.Update\n");
        System.out.println("3.Delete \n");
        System.out.println("4.Print All\n");
        System.out.println("5.Exit\n");
        int choice = s.nextInt();

        switch (choice) {
            case 1:
            	System.out.println("The Databse Records :");
            	printRecords();//to user check the database record details before insert
                insertData();
                break;
            case 2:
            	System.out.println("The Databse Records :");
            	printRecords();//to user check the database record details before update
                updateData();
                break;
            case 3:
            	System.out.println("The Databse Records :");
            	printRecords();//to user check the database record details before delete
                deleteData();
                break;
            case 4:
                printAllRecords();
                break;
            case 5:
                System.out.println("Thank You");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private static Connection getConnection() throws SQLException {
    	
    	  try {
    		  //used to loads the JDBC Driver
    			Class.forName("com.mysql.cj.jdbc.Driver");  		
    			
    		}catch(Exception sq) {
    			System.out.println(sq);		}
    	 //used to connect the mysql database
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    //insert
    private static void insertData() throws SQLException {
        System.out.println("1. Insert New Data");
        try (Connection con = getConnection();//to check connection of database
        	//prepareStatement is interface used to execute query
            PreparedStatement st = con.prepareStatement("INSERT INTO student (name, class, mark) VALUES (?, ?, ?)")) {
        	Scanner s = new Scanner(System.in);
            System.out.println("Enter Name : ");
            String name = s.next();

            System.out.println("Enter Class : ");
            String stud_class = s.next();

            System.out.println("Enter Mark : ");
            int mark = s.nextInt();
            
          //to set parameter
            st.setString(1, name);
            st.setString(2, stud_class);
            st.setInt(3, mark);
            
            //to execute the query
            st.executeUpdate();
            System.out.println("Data Insert Success");
            System.out.println("The database records after Insertion :");
            printRecords();
        }
    }
//update
    private static void updateData() throws SQLException {
        System.out.println("2. Updating a Data");
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("UPDATE student SET name=?, class=?, mark=? WHERE id=?")) {
        	 Scanner s = new Scanner(System.in);
            System.out.println("Enter ID : ");
            int id = s.nextInt();
            System.out.println("Enter Name : ");
            String name = s.next();
            System.out.println("Enter Class : ");
            String stud_class = s.next();
            System.out.println("Enter Mark : ");
            int mark = s.nextInt();

            st.setString(1, name);
            st.setString(2, stud_class);
            st.setInt(3, mark);
            st.setInt(4, id);

            st.executeUpdate();
            System.out.println("Data Update Success");
            System.out.println("The database records after Updation :");
            printRecords();
        }
    }
//delete
    private static void deleteData() throws SQLException {
        System.out.println("3. Deleting a Data");
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("DELETE FROM student WHERE id=?")) {
        	 Scanner s = new Scanner(System.in);
            System.out.println("Enter ID : ");
            int id = s.nextInt();

            st.setInt(1, id);

            st.executeUpdate();
            System.out.println("Data Deleted Success");
            System.out.println("The database records after deletion :");
            printRecords();
        }
    }
//printAllrecords
    private static void printAllRecords() throws SQLException {
        System.out.println("4. Print all Records");
        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name, class, mark FROM student")) {
            //list to store map
            List<Map<String, Object>> recordsList = new ArrayList<>();
        	
            while (rs.next()) {
            	//map to store the key value pair
            	 Map<String, Object> recordMap = new HashMap<>();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String stud_class = rs.getString("class");
                int mark = rs.getInt("mark");
                
                //to insert database values into map
                recordMap.put("id", id);
                recordMap.put("name", name);
                recordMap.put("class", stud_class);
                recordMap.put("mark", mark);

                recordsList.add(recordMap);

                System.out.print(id + " ");
                System.out.print(name + " ");
                System.out.print(stud_class + " ");
                System.out.println(mark + " ");
            }
            System.out.println("HashMap Records: " + recordsList);
            
            
        }
        
    }
        
        private static void printRecords() throws SQLException {
        	
        	
        	try (Connection con = getConnection();
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT id, name, class, mark FROM student")) {
                   while (rs.next()) {
                       
                       int id = rs.getInt("id");
                       String name = rs.getString("name");
                       String stud_class = rs.getString("class");
                       int mark = rs.getInt("mark");
                       

                       System.out.print(id + " ");
                       System.out.print(name + " ");
                       System.out.print(stud_class + " ");
                       System.out.println(mark + " ");
                      
                   }
                   System.out.println();
        }
    }
}
