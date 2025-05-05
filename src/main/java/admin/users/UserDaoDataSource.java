package admin.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

import admin.DefaultBeanInterface;
import admin.services.ServicesDaoDataSource;
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

}
