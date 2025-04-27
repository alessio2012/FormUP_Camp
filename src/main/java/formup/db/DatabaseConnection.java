package formup.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DatabaseConnection {
	
	// Metodo per l'inizializzazione della connessione
	// Raccoglie tutte le eccezioni generate dalla classe SQLException e ClassNotFoundException
	public static Connection initConnection() throws SQLException, ClassNotFoundException  {
		

		String dbURL = "jdbc:mysql://localhost:3306/";

		// Dati di accesso
		String dbName = "db_formup";
		String dbUsername = "root";
		String dbPwd = "";
			
		System.out.println("[formup.db.DatabaseConnection:conn] Initializing connection to db ("+dbUsername+"@"+dbURL+dbName+")");
		Class.forName("com.mysql.jdbc.Driver");
		try { 
			Connection conn = DriverManager.getConnection(dbURL+dbName, dbUsername, dbPwd);
			return conn;
		} catch( Exception e ) { e.printStackTrace(); }
		
		return null;
		
		
		
	}
 
}
