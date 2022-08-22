package biling.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static String url = "jdbc:mysql://localhost:3306/billingdb";
	private static String username = "root";
	private static String password = "Mydb!001";
	
	public static Connection connectDB() {
		try{
			Connection con = DriverManager.getConnection(url, username, password);		
			//System.out.println("Connected to DB!!!");
			return con;
		}catch (SQLException ex) {
			throw new Error("Error ", ex);
		}
	}
}
