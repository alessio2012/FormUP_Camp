package formup;



import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import formup.admin.users.UserBean;
import formup.admin.users.UserDaoDataSource;
import formup.utilities.HashingMD5;
import formup.utilities.UserToken;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("INFO: [formup.Login] An user is trying to connect");

		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
	
    	RequestDispatcher dispatcherToLoginPage = request.getRequestDispatcher("login.jsp"); // ridirezionamento verso la pagina di login

		if(email == null || email.trim().isEmpty()) { /* fai qualcosa */}
        if(password == null || password.trim().isEmpty()) { /* fai altro  */ }
        
        email = email.trim();
        password = password.trim();
        
		String hashedPassword = HashingMD5.getMd5( password );
        
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        UserBean user = new UserBean();
        UserDaoDataSource userDAO = new UserDaoDataSource(ds);
        
        try {
			user = userDAO.retrieveByEmail(email);
			
			if( user.getPassword().equals(hashedPassword) && user.getEmail().equals(email) ) {
				request.getSession().setAttribute("token", UserToken.COMMON); //inserisco il token nella sessione
				response.sendRedirect("common/index.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
        
        
        /*
        try {
			userDAO.retrieveByName("llexio");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
 
        
        
        
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
