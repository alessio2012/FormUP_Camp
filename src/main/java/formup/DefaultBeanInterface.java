package formup;

import java.sql.SQLException;
import java.util.Collection;


public interface DefaultBeanInterface<T> {
	
	public void save(T product) throws SQLException;
	public boolean delete(int code) throws SQLException;
	public T retrieveByKey(int code) throws SQLException;
	public Collection<T> retrieveAll() throws SQLException;

}
