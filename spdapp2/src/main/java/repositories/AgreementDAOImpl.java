package repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.Agreement;
import dao.AgreementDAO;

public class AgreementDAOImpl implements AgreementDAO {

	private static final String CONTEXT_LOOKUP = "java:/comp/env/jdbc/spd";
	private static final String SELECT_AGREEMENT_BY_ID = "select * from agreement where id = ?";
	private static final String SELECT_ALL_AGREEMENTS_BY_SPD_ID = "select * from agreement where spd_id = ?";
	private static final String CREATE_AGREEMENT = "insert into agreement (spd_id, number, datestart) "
			+ "values (?, ?, ?)";
	private static final String UPDATE_AGREEMENT = "update agreement set spd_id=?, number=?, datestart=? where id=?";
	private static final String DELETE_AGREEMENT = "delete from agreement where id=?";

	private final DataSource dataSource;

	public AgreementDAOImpl() {
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

	@Override
	public void create(Agreement agreement) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(CREATE_AGREEMENT,
					PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setInt(1, agreement.getSpdId());
			statement.setString(2, agreement.getNumber());
			statement.setDate(3, agreement.getDateStart());
			statement.executeUpdate();
			try {
				ResultSet generatedKeys = statement.getGeneratedKeys();
				try {
					if (generatedKeys.next())
						agreement.setId(generatedKeys.getInt(1));
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

	@Override
	public void update(Agreement agreement) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE_AGREEMENT);
			statement.setInt(1, agreement.getSpdId());
			statement.setString(2, agreement.getNumber());
			statement.setDate(3, agreement.getDateStart());
			statement.setInt(4, agreement.getId());
			try {
				statement.executeUpdate();
			} finally {
				statement.close();
			}
		} finally {
			connection.close();
		}
	}

	@Override
	public void delete(Agreement agreement) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(DELETE_AGREEMENT);
			statement.setInt(1, agreement.getId());
			try {
				statement.executeUpdate();
			} finally {
				statement.close();
			}
		} finally {
			connection.close();
		}
	}

	@Override
	public Agreement selectById(int id) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SELECT_AGREEMENT_BY_ID);
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

	@Override
	public List<Agreement> selectAllBySPDId(int spdId) throws SQLException {
		List<Agreement> agreements = new ArrayList<Agreement>();
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SELECT_ALL_AGREEMENTS_BY_SPD_ID);
			statement.setInt(1, spdId);
			try {
				ResultSet results = statement.executeQuery();
				try {
					while (results.next()) {
						Agreement agreement = unmarshal(results);
						agreements.add(agreement);
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
		return agreements;
	}

	private static Agreement unmarshal(ResultSet results) throws SQLException {
		Agreement agreement = new Agreement();
		agreement.setId(results.getObject("id", Integer.class));
		agreement.setSpdId(results.getObject("spd_id", Integer.class));
		agreement.setNumber(results.getObject("number", String.class));
		agreement.setDateStart(results.getObject("datestart", Date.class));
		return agreement;
	}

}
