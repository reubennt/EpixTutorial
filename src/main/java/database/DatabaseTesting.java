package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DatabaseTesting {

	// Connection object
	static Connection conn = null;
	// Statement Object
	private static Statement stmt;
	// Result Set
	private static ResultSet results = null;
	// Constant for Database URL
	private static String DB_URL = "jdbc:mysql://localhost:3306/users";
	// Constant for Database Username
	public static String DB_USER = "root";
	// Constant for Database Password
	public static String DB_PASSWORD = null;
	// Driver
	public static String driver = "com.mysql.jdbc.Driver";

	// WebDriver
	// public static WebDriver dv;

	@BeforeClass
	public void beforeClass() {
		// Initialize WebDriver
		//dv = new FirefoxDriver();

		// Properties for creating connection to database

		Properties props = new Properties();
		props.setProperty("user", "Roobtoob");
		props.setProperty("password", "monkey787");

		try {
			// STEP 1: Register JDBC driver
			Class.forName(driver).newInstance();

			// STEP 2: Get Connection to DB
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			// You can you use either of these for connection
			// conn = DriverManager.getConnection(DB_URL, props);
			System.out.println("Connected database successfully...");

			// STEP 3: Statement object to send the SQL statement to the
			// Database
			System.out.println("Creating statement...");
			stmt = conn.createStatement();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
	
	@Test
	public void test() throws SQLException {
		String query = "select * from user_info";
		try{
			//STEP 4: Extract data from result set
			results = stmt.executeQuery(query);
			while (results.next()) {
				int id = results.getInt("user_id");
				String last = results.getString("last_name");
				String first = results.getString("first_name");
				String city = results.getString("city");
				
				//Display Values
				System.out.println("ID: " + id);
				System.out.println("Last Name: " + last);
				System.out.println("First Name: " + first);
				System.out.println("City: " + city);
				
				//WebElement element = dv.
				
			}
			results.close();
			
		}catch (SQLException se) {
			//Handle errors for JDBC
			se.printStackTrace();
		}catch (Exception e) {
			//Handle errors for Class.forName
			e.printStackTrace();
		}
	}
	
	//Needed to close connection for memory purposes
	@AfterClass
	public void afterClass() {
		try {
			if (results != null)
				results.close();
			if (stmt != null)
				conn.close();
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
	}

}
