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
import beans.SPD;
import dao.SPDDAO;

public class SPDDaoImpl implements SPDDAO {

	private static final String CONTEXT_LOOKUP = "java:/comp/env/jdbc/spd";
	private static final String SELECT_ALL_SPD = "select * from spd";
	private static final String SELECT_SPD_BY_ID = "select * from spd where id = ?";
	private static final String CREATE_SPD = "insert into spd (surname, firstname, lastname, alias, inn, passport, address_id, registration_info_id) "
			+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_SPD = "update spd set surname=?, firstname=?, lastname=?, alias=?, inn=?, passport=?, address_id=?, registration_info_id=? where id=?";
	private static final String DELETE_SPD = "delete from spd where id=?";

	private final DataSource dataSource;

	public SPDDaoImpl() {
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

	public void create(SPD spd) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(CREATE_SPD, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, spd.getSurname());
			statement.setString(2, spd.getFirstname());
			statement.setString(3, spd.getLastname());
			statement.setString(4, spd.getAlias());
			statement.setString(5, spd.getInn());
			statement.setString(6, spd.getPassport());
			statement.setInt(7, spd.getAddressId());
			statement.setInt(8, spd.getRegistrationInfoId());
			statement.executeUpdate();
			try {
				ResultSet generatedKeys = statement.getGeneratedKeys();
				try {
					if (generatedKeys.next())
						spd.setId(generatedKeys.getInt(1));
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

	public void update(SPD spd) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE_SPD);
			statement.setString(1, spd.getSurname());
			statement.setString(2, spd.getFirstname());
			statement.setString(3, spd.getLastname());
			statement.setString(4, spd.getAlias());
			statement.setString(5, spd.getInn());
			statement.setString(6, spd.getPassport());
			statement.setInt(7, spd.getAddressId());
			statement.setInt(8, spd.getRegistrationInfoId());
			statement.setInt(9, spd.getId());
			try {
				statement.executeUpdate();
			} finally {
				statement.close();
			}
		} finally {
			connection.close();
		}
	}

	public void delete(SPD spd) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(DELETE_SPD);
			statement.setInt(1, spd.getId());

			try {
				statement.executeUpdate();
			} finally {
				statement.close();
			}
		} finally {
			connection.close();
		}
	}

	public List<SPD> selectAll() throws SQLException {
		List<SPD> spdList = new ArrayList<SPD>();
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SPD);
			try {
				ResultSet results = statement.executeQuery();
				try {
					while (results.next()) {
						SPD spd = unmarshal(results);
						spdList.add(spd);
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
		return spdList;
	}

	public SPD selectById(int id) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SELECT_SPD_BY_ID);
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

	private static SPD unmarshal(ResultSet results) throws SQLException {
		SPD spd = new SPD();
		spd.setId(results.getObject("id", Integer.class));
		spd.setSurname(results.getObject("surname", String.class));
		spd.setFirstname(results.getObject("firstname", String.class));
		spd.setLastname(results.getObject("lastname", String.class));
		spd.setAlias(results.getObject("alias", String.class));
		spd.setInn(results.getObject("inn", String.class));
		spd.setPassport(results.getObject("passport", String.class));
		spd.setAddressId(results.getObject("address_id", Integer.class));
		spd.setRegistrationInfoId(results.getObject("registration_info_id", Integer.class));
		return spd;
	}

}
