package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Account;

public interface AccountDAO {
	public void create(Account account) throws SQLException;
	public void delete(Account account) throws SQLException;
	public void update(Account account) throws SQLException;
	public Account selectById(int id) throws SQLException;
	public List<Account> selectAllBySPDId(int spdId) throws SQLException;
	
}
