package formup.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	static {
		
		// DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver() );
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("ERROR: [formup.db.DatabaseConnection] Driver not found " + e.getMessage());
		} 
	}
	
	// Metodo per l'inizializzazione della connessione
	// Raccoglie tutte le eccezioni generate dalla classe SQLException e ClassNotFoundException
	public static synchronized Connection initConnection() throws SQLException  {
		 Connection conn = null;


		// Dati di accesso
		String dbName = "db_formup";
		String dbUsername = "root";
		String dbPwd = "admin";
			
		try { 
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"+dbName, dbUsername, dbPwd);
		} catch( Exception e ) { 
			// Errore sulla connessione
			System.err.println("ERROR: [formup.db.DatabaseConnection] Unable to connect to db " +e.getMessage());
		}
		
		return conn;
			
	} 
	
	

	
		
		
		
	}
 
