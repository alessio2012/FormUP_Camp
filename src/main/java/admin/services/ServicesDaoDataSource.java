package admin.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import admin.DefaultBeanInterface;
import formup.utilities.ColoredText;

public class ServicesDaoDataSource implements DefaultBeanInterface<ServicesBean> {
	
	
	private static final String TABLE_NAME = "servizio"; // nome della tabella di riferimento
	private DataSource ds = null; // datasource


	public ServicesDaoDataSource(DataSource ds) {
		this.ds = ds;
		System.out.println("INFO: [formup.services.ServicesDaoDataSource] Initialized a new on ds: "+ds);
	}
	
	@Override
	public void save(ServicesBean service) throws SQLException {
		// System.out.println(service.toString());
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ServicesDaoDataSource.TABLE_NAME
				+ " (titolo, descrizione, costo, dataInizio, dataFine, disponibilita ) VALUES (?, ?, ?, ?, ?, ?)";
		
		
		System.out.println("INFO: [formup.services.ServicesDaoDataSource:bean] Trying to inserto into database a new service");
		try {
			connection = ds.getConnection();
			System.out.println(connection);
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, service.getTitolo());
			preparedStatement.setString(2, service.getDescrizione());
			preparedStatement.setDouble(3, service.getCosto());
			preparedStatement.setDate(4, service.getDataInizio() );
			preparedStatement.setDate(5, service.getDatafine() );
			preparedStatement.setBoolean(6, service.isDisponibilita() );
			preparedStatement.executeUpdate();
			
			System.out.println(ColoredText.ANSI_GREEN_BG + "INFO: [formup.services.ServicesDaoDataSource:bean] Bean inserted into database successfully" + ColoredText.ANSI_RESET);
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
	public ServicesBean retrieveByKey(int code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ServicesBean> retrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<ServicesBean> services = new LinkedList<ServicesBean>();

		String selectSQL = "SELECT * FROM " + ServicesDaoDataSource.TABLE_NAME;
		
		
		System.out.println("INFO: [formup.services.ServicesDaoDataSource:bean] Trying to SELECT FROM database a ALL services");
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ServicesBean bean = new ServicesBean();

				bean.setIdServizio(rs.getInt("idServizio"));
				bean.setTitolo(rs.getString("titolo"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setCosto(rs.getDouble("costo"));
				bean.setDataInizio(rs.getDate("dataInizio"));
				bean.setDatafine(rs.getDate("dataFine"));
				bean.setDisponibilita(rs.getBoolean("disponibilita"));
				
				services.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return services;
	}
	

}
