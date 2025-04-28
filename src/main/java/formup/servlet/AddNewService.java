package formup.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// CUSTOM IMPORT
import formup.db.*;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.time.LocalDate; 
/**
 * Servlet implementation class addNewService
 */
@WebServlet("/AddNewService")
public class AddNewService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String GREEN = "\u001B[32m"; String RESET = "\u001B[0m";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		try { 
			
			System.out.println("INFO: [formup.servlet.AddNewService] Trying to connect to database");
			
			// inizializzazione della connessione
			Connection connection = DatabaseConnection.initConnection();
			if(connection != null ) {
				System.out.println(GREEN + "INFO: [formup.servlet.AddNewService] Connection established successfully " + RESET);
				
				
				System.out.println("INFO: [formup.servlet.AddNewService] Trying to querying the db (" + connection +")");
				
				PreparedStatement query = connection.prepareStatement("INSERT INTO servizio(titolo, descrizione, costo, dataInizio, dataFine) VALUES(?,?,1,?,?)");
				
				query.setString(1, request.getParameter("serviceTitle"));
				query.setString(2, request.getParameter("serviceDescription"));
				query.setString(3, LocalDate.now().toString());
				query.setString(4, LocalDate.now().toString());
				
				if( query.executeUpdate() != -1 ) System.out.println(GREEN + "INFO: [formup.servlet.AddNewService] Query executed  successfully " + RESET);
				else System.err.println("ERROR: [formup.servlet.AddNewService] An error occurred while executing the query");
				
				query.close();
				connection.close();
						
			}

		} catch( Exception e ) {
			System.err.println("ERROR: [formup.servlet.AddNewService] An error occurred while executing the query" + e.getMessage() );
		}
		
		request.setAttribute("Ok! Inserito", "message");
		
		 
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/pages/newEvent.jsp");
		dispatcher.forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
