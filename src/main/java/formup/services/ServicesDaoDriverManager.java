package formup.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

import javax.sql.DataSource;

import formup.db.DriverManagerConnectionPool;

public class ServicesDaoDriverManager implements ServicesInterface {
	
	private static final String TABLE_NAME = "servizio";
	private DriverManagerConnectionPool dmcp = null;	


	public ServicesDaoDriverManager(DriverManagerConnectionPool dmcp) {
		this.dmcp = dmcp;
		System.out.println("Trying to create the DriverManager of the table Services");
	}
	
	@Override
	public void save(ServicesBean service) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ServicesDaoDriverManager.TABLE_NAME
				+ " (titolo, descrizione, costo, dataInizio, dataFine ) VALUES (?, ?, ?, ?, ?)";

		try {
			connection = dmcp.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, service.getTitolo());
			preparedStatement.setString(2, service.getDescrizione());
			preparedStatement.setDouble(3, service.getCosto());
			preparedStatement.setDate(4, java.sql.Date.valueOf( LocalDate.now() ) );
			preparedStatement.setDate(5, java.sql.Date.valueOf( LocalDate.now() ) );

			preparedStatement.executeUpdate();

		} finally {
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
	public ServicesBean retrieveByKey(int code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ServicesBean> retrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
