package formup.admin.users;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;
import formup.utilities.ColoredText;
import formup.utilities.HashingMD5;

/**
 * Servlet implementation class UserControl
 */
@WebServlet("/admin/UserControl")
public class UserControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDaoDataSource userDao = null;
	boolean isDataSource = true;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SONO NEL GET!");

		if (isDataSource) {
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			userDao = new UserDaoDataSource(ds);			
		} 
		
		if( request.getParameter("operation").equals("insert") ) {
			System.out.println("INFO: [formup.UserControl] Trying to add a new User Bean ");
			try {
				UserBean bean = new UserBean();
				bean.setNome( request.getParameter("nome") );
				bean.setEmail( request.getParameter("email") );
				bean.setCognome( request.getParameter("cognome") );
				bean.setPassword( HashingMD5.getMd5( request.getParameter("password") ) );
				bean.setUsername( request.getParameter("username") );
				
				System.out.println(ColoredText.ANSI_GREEN_BG + "INFO: [formup.services.UserControl] Bean addedd Successfully" + ColoredText.ANSI_RESET );
				System.out.println( bean.toString() );
				
				userDao.save(bean);
			} catch (SQLException e) {
				System.err.println("ERROR: [formup.UserControl] An error occurred while trying to adding a new bean" + e.getMessage());
			}
			
			request.setAttribute("status", "successfull");
			//request.setAttribute("operation", "none");
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/index.jsp");
			dispatcher.forward(request, response);

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
