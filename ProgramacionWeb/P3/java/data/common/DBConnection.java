package data.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A class to manage the MySQL connection (general methods and configuration. 
 * @author Developers
 * */

public class DBConnection {

	protected Connection connection = null;
	
	protected String url;
	protected String user;
	protected String password;
	
	public DBConnection(String url, String user, String passw){
		this.url = url;
		this.user = user;
		this.password = passw;
	}
	
	public Connection getConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = (Connection) DriverManager.getConnection(url, user, password);
			System.out.println("Database connection successfully opened!");
		} 
		catch (SQLException e) {
			System.err.println("Connection to MySQL has failed!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found.");
			e.printStackTrace();
		}
		return this.connection;
	}

	public void closeConnection() {
		try {
			if(this.connection != null && !this.connection.isClosed()) {
				this.connection.close();
				System.out.println("Database connection successfully closed!");
			}
		} catch (SQLException e) {
			System.err.println("Error while trying to close the connection.");
			e.printStackTrace();
		}
	}
}