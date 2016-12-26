package dao;

import java.sql.SQLException;
import beans.RegistrationInfo;

public interface RegistrationInfoDAO {
	public void create(RegistrationInfo regInfo) throws SQLException;
	public void delete(RegistrationInfo regInfo) throws SQLException;
	public void update(RegistrationInfo regInfo) throws SQLException;
	public RegistrationInfo selectById(int id) throws SQLException;
	
}
