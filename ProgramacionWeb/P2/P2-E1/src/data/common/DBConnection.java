package data.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A class to manage the MySQL connection (general methods and configuration).
 * @author developers
 * */

public class DBConnection {
	
	/* Acceso a la base de datos mediante archivo
	 * config.properties.
	 * Incluir las siguientes lineas dentro de la funcion
	 * getConnection().
	 */
	
	
	/*String rutaAbs = new File("").getAbsolutePath();
	String ruta = rutaAbs + "/P2/P2-E1/src/config.properties";
	try(InputStream input = new FileInputStream(ruta)){
	Properties prop = new Properties();
	prop.load(input);

	String url = prop.getProperty("url");
	String user = prop.getProperty("user");
	String password = prop.getProperty("password");
	} 
	catch (Exception e){
	System.err.println(e);
	e.printStackTrace();
	}*/

	protected Connection connection = null;

	// Important: This configuration is hard-coded here for illustrative purposes only
	
	protected String url = "jdbc:mysql://oraclepr.uco.es:3306/p92avavv";

	protected String user = "p92avavv";

	protected String password = "passwordpw";

	public Connection getConnection(){

		try{
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = (Connection) DriverManager.getConnection(url, user, password);
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

	// We can include here other methods to encapsulate CRUD commands...

	public void closeConnection() {
		try {
			if(this.connection != null && !this.connection.isClosed()) {
				this.connection.close();
			}
		} catch (SQLException e) {
			System.err.println("Error while trying to close the connection.");
			e.printStackTrace();
		}
	}
}