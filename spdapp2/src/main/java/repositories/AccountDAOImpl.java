package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import beans.Account;
import dao.AccountDAO;

public class AccountDAOImpl implements AccountDAO {

	private static final String CONTEXT_LOOKUP = "java:/comp/env/jdbc/spd";
	private static final String SELECT_ACCOUNT_BY_ID = "select * from account where id = ?";
	private static final String SELECT_ALL_ACCOUNTS_BY_SPD_ID = "select * from account where spd_id = ?";
	private static final String CREATE_ACCOUNT = "insert into account (spd_id, account_number, mfo, bank_name) "
			+ "values (?, ?, ?, ?)";
	private static final String UPDATE_ACCOUNT = "update account set spd_id=?, account_number=?, mfo=?, bank_name=? where id=?";
	private static final String DELETE_ACCOUNT = "delete from account where id=?";

	private final DataSource dataSource;

	public AccountDAOImpl() {
		try {
			Context context = new InitialContext();
			try {
				dataSource = (DataSource) context.lookup(CONTEXT_LOOKUP);
			} finally {
				context.close();
			}
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	public void create(Account account) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(CREATE_ACCOUNT,
					PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setInt(1, account.getSpdId());
			statement.setString(2, account.getAccountNumber());
			statement.setString(3, account.getMfo());
			statement.setString(4, account.getBankName());
			statement.executeUpdate();
			try {
				ResultSet generatedKeys = statement.getGeneratedKeys();
				try {
					if (generatedKeys.next())
						account.setId(generatedKeys.getInt(1));
				} finally {
					generatedKeys.close();
				}
			} finally {
				statement.close();
			}
		} finally {
			connection.close();
		}
	}

	public void update(Account account) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE_ACCOUNT);
			statement.setInt(1, account.getSpdId());
			statement.setString(2, account.getAccountNumber());
			statement.setString(3, account.getMfo());
			statement.setString(4, account.getBankName());
			statement.setInt(5, account.getId());
			try {
				statement.executeUpdate();
			} finally {
				statement.close();
			}
		} finally {
			connection.close();
		}
	}

	public void delete(Account account) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(DELETE_ACCOUNT);
			statement.setInt(1, account.getId());
			try {
				statement.executeUpdate();
			} finally {
				statement.close();
			}
		} finally {
			connection.close();
		}
	}

	public List<Account> selectAllBySPDId(int spdId) throws SQLException {
		List<Account> accounts = new ArrayList<Account>();
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ACCOUNTS_BY_SPD_ID);
			statement.setInt(1, spdId);
			try {
				ResultSet results = statement.executeQuery();
				try {
					while (results.next()) {
						Account account = unmarshal(results);
						accounts.add(account);
					}
				} finally {
					results.close();
				}
			} finally {
				statement.close();
			}
		} finally {
			connection.close();
		}
		return accounts;
	}

	public Account selectById(int id) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SELECT_ACCOUNT_BY_ID);
			statement.setInt(1, id);
			try {
				ResultSet results = statement.executeQuery();
				try {
					if (results.next()) {
						return unmarshal(results);
					} else {
						return null;
					}
				} finally {
					results.close();
				}
			} finally {
				statement.close();
			}
		} finally {
			connection.close();
		}
	}

	private static Account unmarshal(ResultSet results) throws SQLException {
		Account account = new Account();
		account.setId(results.getObject("id", Integer.class));
		account.setSpdId(results.getObject("spd_id", Integer.class));
		account.setAccountNumber(results.getObject("account_number", String.class));
		account.setMfo(results.getObject("mfo", String.class));
		account.setBankName(results.getObject("bank_name", String.class));
		return account;
	}

}
