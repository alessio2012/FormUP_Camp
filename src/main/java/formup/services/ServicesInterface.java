package formup.services;

import java.sql.SQLException;
import java.util.Collection;

public interface ServicesInterface {
	
	public void save(ServicesBean product) throws SQLException;
	public boolean delete(int code) throws SQLException;
	public ServicesBean retrieveByKey(int code) throws SQLException;
	public Collection<ServicesBean> retrieveAll(String order) throws SQLException;

}
