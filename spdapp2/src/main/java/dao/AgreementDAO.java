package dao;

import java.sql.SQLException;
import java.util.List;

import beans.Agreement;

public interface AgreementDAO {
	public void create(Agreement agreement) throws SQLException;
	public void delete(Agreement agreement) throws SQLException;
	public void update(Agreement agreement) throws SQLException;
	public Agreement selectById(int id) throws SQLException;
	public List<Agreement> selectAllBySPDId(int spdId) throws SQLException;
	
}
