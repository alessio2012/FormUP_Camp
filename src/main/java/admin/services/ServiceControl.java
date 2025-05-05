package admin.services;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import admin.DefaultBeanInterface;
import formup.db.DriverManagerConnectionPool;
import formup.utilities.ColoredText;

/**
 * Servlet implementation class ServiceControl
 */
@WebServlet("/admin/ServiceControl")
public class ServiceControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServicesDaoDataSource servicesDao = null;
	boolean isDataSource = true;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceControl() {
        super();
        
	
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if (isDataSource) {
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			System.out.println(ds);
			servicesDao = new ServicesDaoDataSource(ds);			
		} 
		
		
		
		
		if( request.getParameter("operation").equals("insert") ) {
			System.out.println("INFO: [formup.services.ServiceControl] Trying to add a new Service Bean ");
			try {
				ServicesBean bean = new ServicesBean();
				bean.setTitolo( request.getParameter("titolo") );
				bean.setDescrizione( request.getParameter("descrizione") );
				bean.setCosto( Double.valueOf(request.getParameter("costo") ) );
				bean.setDataInizio( Date.valueOf( request.getParameter("dataInizio")  ) );
				bean.setDatafine(Date.valueOf( request.getParameter("dataFine")  )  );
				
				System.out.println(ColoredText.ANSI_GREEN_BG + "INFO: [formup.services.ServiceControl] Bean addedd Successfully" + ColoredText.ANSI_RESET );
				System.out.println( bean.toString() );
				
				servicesDao.save(bean);
			} catch (SQLException e) {
				System.err.println("ERROR: [formup.services.ServiceControl] An error occurred while trying to adding a new bean" + e.getMessage());
			}
			
			request.setAttribute("status", "successfull");
			request.setAttribute("operation", "none");
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/index.jsp");
			dispatcher.forward(request, response);

		} else if( request.getParameter("operation").equals("selectAll") ) {
			
			Collection<ServicesBean> services = new LinkedList<ServicesBean>();
			services = null;
			
			System.out.println("INFO: [formup.services.ServiceControl] Trying to select all bean ");
			
			try {
				services = servicesDao.retrieveAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("ERROR: [formup.services.ServiceControl] An error occurred while trying to retrieve all the services" + e.getMessage());
			} 
			
			System.out.println(ColoredText.ANSI_GREEN_BG + "INFO: [formup.services.ServiceControl] Obtained all the bean" + ColoredText.ANSI_RESET );
			
			request.setAttribute("status", "successfull");
			request.setAttribute("operation", "none");
			request.setAttribute("collection", services);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/index.jsp");
			dispatcher.forward(request, response);
			
		} else if(  request.getParameter("operation") == null ) { 
			System.err.println("ERROR: [formup.services.ServiceControl] An error occurred. Parameter 'operation' is null ");
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
