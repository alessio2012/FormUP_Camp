package formup;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import javax.sql.DataSource;

import formup.conf.db.DriverManagerConnectionPool;
import formup.utilities.ColoredText;

@WebListener
public class MainContext implements ServletContextListener {

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		
		System.out.println(""
				+ ""
				+ "███████  ██████  ██████  ███    ███ ██    ██ ██████  \r\n"
				+ "██      ██    ██ ██   ██ ████  ████ ██    ██ ██   ██ \r\n"
				+ "█████   ██    ██ ██████  ██ ████ ██ ██    ██ ██████  \r\n"
				+ "██      ██    ██ ██   ██ ██  ██  ██ ██    ██ ██      \r\n"
				+ "██       ██████  ██   ██ ██      ██  ██████  ██     \n\n"
				+ ""
				+ "@dicaprioale - v. 1.0 - UNISA 24/25 \n"
				+ "");
		
		System.out.println("INFO: [formup.MainContext] Starting the main context");

		// acquisizione del contesto dell'applicazione
		ServletContext context = servletContextEvent.getServletContext();

		// Per usare il DataSource
		DataSource datasource = null;
		
		System.out.println("INFO: [formup.MainContext] Trying to initialize the DataSource");
		try {
			
			// nuova inizializzazione
			Context initCtx = new InitialContext();
			
			// ricerca del naming nel contesto iniziale
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			// ricerca del naming nel contesto env
			datasource = (DataSource) envCtx.lookup("jdbc/db_formup");

		} catch (NamingException e) {
			System.err.println("ERROR: [formup.MainContext] An error occurred" + e.getMessage());
		}		

		// Dataset Creato
		context.setAttribute("DataSource", datasource);
		System.out.println(ColoredText.ANSI_GREEN_BG + "INFO: [formup.MainContext] Data Source created successfully - " + datasource.toString()+ ColoredText.ANSI_RESET);
		
		// Per usare il DriverManager
		DriverManagerConnectionPool driverManager = new DriverManagerConnectionPool();
		context.setAttribute("DriverManager", driverManager);
		System.out.println(ColoredText.ANSI_GREEN_BG + "INFO: [formup.MainContext] Driver Manager created successfully - " + driverManager.toString()+ ColoredText.ANSI_RESET);
	
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}
}
