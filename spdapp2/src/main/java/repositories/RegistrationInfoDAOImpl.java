package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import beans.RegistrationInfo;
import dao.RegistrationInfoDAO;

public class RegistrationInfoDAOImpl implements RegistrationInfoDAO {

	private static final String CONTEXT_LOOKUP = "java:/comp/env/jdbc/spd";
	private static final String SELECT_REGINFO_BY_ID = "select * from registration_info where id = ?";
	private static final String CREATE_REGINFO = "insert into registration_info (description, dated) values (?, ?)";
	private static final String UPDATE_REGINFO = "update registration_info set description=?, dated=? where id=?";
	private static final String DELETE_REGINFO = "delete from registration_info where id=?";

	private final DataSource dataSource;

	public RegistrationInfoDAOImpl() {
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

	public void create(RegistrationInfo regInfo) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(CREATE_REGINFO,
					PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, regInfo.getDescription());
			statement.setDate(2, regInfo.getDated());
			statement.executeUpdate();
			try {
				ResultSet generatedKeys = statement.getGeneratedKeys();
				try {
					if (generatedKeys.next())
						regInfo.setId(generatedKeys.getInt(1));
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

	public void update(RegistrationInfo regInfo) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE_REGINFO);
			statement.setString(1, regInfo.getDescription());
			statement.setDate(2, regInfo.getDated());
			statement.setInt(3, regInfo.getId());
			try {
				statement.executeUpdate();
			} finally {
				statement.close();
			}
		} finally {
			connection.close();
		}
	}

	public void delete(RegistrationInfo regInfo) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(DELETE_REGINFO);
			statement.setInt(1, regInfo.getId());

			try {
				statement.executeUpdate();
			} finally {
				statement.close();
			}
		} finally {
			connection.close();
		}
	}

	public RegistrationInfo selectById(int id) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SELECT_REGINFO_BY_ID);
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

	private static RegistrationInfo unmarshal(ResultSet results) throws SQLException {
		RegistrationInfo regInfo = new RegistrationInfo();
		regInfo.setId(results.getObject("id", Integer.class));
		regInfo.setDescription(results.getObject("description", String.class));
		regInfo.setDated(results.getObject("dated", Date.class));
		return regInfo;
	}

}
