package formup.servlet;

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
/**
 * Servlet implementation class addNewService
 */
@WebServlet("/AddNewService")
public class AddNewService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
			
			System.out.println("[formup.servlet.AddNewService] Trying to connect to database");
			Connection connection = DatabaseConnection.initConnection();
			System.out.println("[formup.servlet.AddNewService] Trying to querying the db (" + connection +")");
			PreparedStatement query = connection.prepareStatement("INSERT INTO servizio(titolto, descrizione) VALUES(?,?)");
			
			query.setString(1, request.getParameter("serviceTitle"));
			query.setString(2, request.getParameter("serviceDescription"));
			
			query.executeUpdate();
			
			query.close();
			connection.close();
			
			
			out.println("Inserito con i seguenti valori: " +request.getParameter("serviceTitle")+", "+request.getParameter("serviceDescription"));
			
		
		} catch( Exception e ) {
			out.println("Ups! Si e\' verificato un errore");
			out.println( e.getMessage() );
		}
		
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
