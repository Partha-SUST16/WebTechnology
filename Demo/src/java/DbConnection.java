
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class DbConnection { 
	protected static Connection initializeDatabase() 
		throws SQLException, ClassNotFoundException 
	{ 
		// Initialize all the information regarding 
		// Database Connection 
		String dbDriver = "com.mysql.jdbc.Driver"; 
		String dbURL = "jdbc:mysql:// http://localhost:3306/phpmyadmin/tbl_sql.php?db=nam"; 
		// Database name to access 
		String dbName = "nam"; 
		String dbUsername = ""; 
		String dbPassword = ""; 

		Class.forName(dbDriver); 
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nam", "root", ""); 
		return con; 
	} 
} 

