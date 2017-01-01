package dao;

import java.sql.SQLException;
import beans.Account;

public interface AccountDAO {
	public void create(Account account) throws SQLException;
	public void delete(Account account) throws SQLException;
	public void update(Account account) throws SQLException;
	public Account selectById(int id) throws SQLException;
	
}
