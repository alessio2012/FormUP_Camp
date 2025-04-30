package formup.db;

import java.sql.Connection;
import formup.utilities.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool  {

	// Struttura per la lista delle connessioni al database libere
	private List<Connection> dbConnections;

	
	// Metodo statico (simil costruttore)
	static {	
		System.out.println("INFO: [formup.DriverManagerConnectionPool] Trying to registering the driver com.mysql.cj.jdbc.Driver");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // caricamento della libreria mysql
		} catch (ClassNotFoundException e) {
			System.err.println("Error: [formup.DriverManagerConnectionPool] An error occurred while trying to register the driver"+ e.getMessage()); // Errore
		} 
		
		System.out.println(ColoredText.ANSI_GREEN_BG + "INFO: [formup.DriverManagerConnectionPool] The com.mysql.cj.jdbc.Driver registered successfully" + ColoredText.ANSI_RESET);

	}
	
	public DriverManagerConnectionPool() { dbConnections = new LinkedList<Connection>(); }
	
	private synchronized Connection initializeNewConnection() throws SQLException {
		Connection newConnection = null;
		String ip = "localhost";
		String port = "3306";
		String db = "db_formup";
		String username = "root";
		String password = "admin";
		
		System.out.println("Connecting to db");
		newConnection = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+db, username, password);
		return newConnection; // restituisci la connessione
	}


	public synchronized Connection getConnection() throws SQLException {
		Connection connection;
		
		// ci sono connessioni libere?
		if (!dbConnections.isEmpty()) {
			
			// Prendi la prima disponibile
			connection = (Connection) dbConnections.get(0);
			dbConnections.remove(0);

			try { if(connection.isClosed()) { connection = getConnection();} } 
			catch (SQLException e) {
				connection.close(); // Errore! Chiudi la connessione
				connection = getConnection();
			}
		} else { connection = initializeNewConnection(); }	 // inizializza una nuova connessione	 }

		return connection; // restituisci
	}

	public synchronized void releaseConnection(Connection connection) throws SQLException {
		if(connection != null) dbConnections.add(connection);
	}
}
