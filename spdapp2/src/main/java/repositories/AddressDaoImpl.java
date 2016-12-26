package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.Address;
import dao.AddressDAO;
import jdbc.DBUtil;

public class AddressDaoImpl implements AddressDAO {

	private static final String CONTEXT_LOOKUP = "java:/comp/env/jdbc/spd";
	private static final String SELECT_ADDRESS_BY_ID = "select * from address where id = ?";
	private static final String CREATE_ADDRESS = "insert into address (country, region, city, street, building, flat, zip) "
			+ "values (?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_ADDRESS = "update address set country=?, region=?, city=?, street=?, building=?, flat=?, zip=? where id=?";
	private static final String DELETE_ADDRESS = "delete from address where id=?";

	private final DataSource dataSource;

	public AddressDaoImpl() {
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

	public void create(Address address) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(CREATE_ADDRESS, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, address.getCountry());
			statement.setString(2, address.getRegion());
			statement.setString(3, address.getCity());
			statement.setString(4, address.getStreet());
			statement.setString(5, address.getBuilding());
			statement.setString(6, address.getFlat());
			statement.setString(7, address.getZip());
			statement.executeUpdate();
			try {
				ResultSet generatedKeys = statement.getGeneratedKeys();
				try {
					if (generatedKeys.next())
						address.setId(generatedKeys.getInt(1));
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

	public void update(Address address) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE_ADDRESS);
			statement.setString(1, address.getCountry());
			statement.setString(2, address.getRegion());
			statement.setString(3, address.getCity());
			statement.setString(4, address.getStreet());
			statement.setString(5, address.getBuilding());
			statement.setString(6, address.getFlat());
			statement.setString(7, address.getZip());
			statement.setInt(8, address.getId());
			try {
				statement.executeUpdate();
			} finally {
				statement.close();
			}
		} finally {
			connection.close();
		}
	}

	public void delete(Address address) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(DELETE_ADDRESS);
			statement.setInt(1, address.getId());

			try {
				statement.executeUpdate();
			} finally {
				statement.close();
			}
		} finally {
			connection.close();
		}
	}

	public Address selectById(int id) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SELECT_ADDRESS_BY_ID);
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

	private static Address unmarshal(ResultSet results) throws SQLException {
		Address address = new Address();
		address.setId(results.getObject("id", Integer.class));
		address.setCountry(results.getObject("country", String.class));
		address.setRegion(results.getObject("region", String.class));
		address.setCity(results.getObject("city", String.class));
		address.setStreet(results.getObject("street", String.class));
		address.setBuilding(results.getObject("building", String.class));
		address.setFlat(results.getObject("flat", String.class));
		address.setZip(results.getObject("zip", String.class));
		return address;
	}

}
