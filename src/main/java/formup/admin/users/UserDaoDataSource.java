package formup.admin.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

import formup.DefaultBeanInterface;
import formup.utilities.ColoredText;

public class UserDaoDataSource implements DefaultBeanInterface<UserBean>{

	
	private static final String TABLE_NAME = "utente"; // nome della tabella di riferimento
	private DataSource ds = null; // datasource


	public UserDaoDataSource(DataSource ds) {
		this.ds = ds;
		System.out.println("INFO: [formup.UserDaoDataStructure] Initialized a new on ds: "+ds);
	}
	
	public void save(UserBean user) throws SQLException {
		// System.out.println(service.toString());
				Connection connection = null;
				PreparedStatement preparedStatement = null;

				String insertSQL = "INSERT INTO " + UserDaoDataSource.TABLE_NAME
						+ " ( nome, cognome, nomeUtente, password ) VALUES ( ?, ?, ?, ? )";
				
				
				System.out.println("INFO: [formup.UserDaoDataStructure:bean] Trying to inserto into database a new user");
				try {
					connection = ds.getConnection();
					System.out.println(connection);
					preparedStatement = connection.prepareStatement(insertSQL);
					preparedStatement.setString(1, user.getNome() );
					preparedStatement.setString(2, user.getCognome() );
					preparedStatement.setString(3, user.getUsername() );
					preparedStatement.setString(4, user.getPassword() );
					preparedStatement.executeUpdate();
					
					System.out.println(ColoredText.ANSI_GREEN_BG + "INFO: [formup.services.UserDaoDataStructure:bean] Bean inserted into database successfully" + ColoredText.ANSI_RESET);
				} 
				
				finally {
					try {
						if (preparedStatement != null)
							preparedStatement.close();
					} finally {
						if (connection != null)
							connection.close();
					} 
				}
				
		
	}

	
	@Override
	public boolean delete(int code) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserBean retrieveByKey(int code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<UserBean> retrieveAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public UserBean retrieveByEmail( String email ) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		UserBean user = null;

		String obtainSQL = "SELECT * FROM " + UserDaoDataSource.TABLE_NAME
				+ " WHERE email = ?";
		
				try {
					
			connection = ds.getConnection();
			System.out.println(connection);
			preparedStatement = connection.prepareStatement(obtainSQL);
			preparedStatement.setString(1, email.toString() );
			
			System.out.println( obtainSQL );
			ResultSet rs = preparedStatement.executeQuery();
			
			if( rs.next() ) {
				user = new UserBean();
				user.setCognome(rs.getString("cognome"));
				user.setNome(rs.getString("nome"));
				user.setUsername(rs.getString("nomeUtente"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
			}
			
			
			
			System.out.println(ColoredText.ANSI_GREEN_BG + "INFO: [formup.services.UserDaoDataStructure:bean] Bean obrained successfully" + ColoredText.ANSI_RESET);
			
			return user;
		} 
		
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			} 
		}
		
	}

}
