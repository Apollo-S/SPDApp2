package dao.impl;

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

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import dao.PaymentDAO;
import entity.Payment;
import utils.JdbcManager;

public class PaymentDAOImpl extends JdbcManager implements PaymentDAO {

	private static final String CONTEXT_LOOKUP = "java:/comp/env/jdbc/spd";
	private static final String SELECT_PAYMENT_BY_ID = "select * from payment where id = ?";
	private static final String SELECT_ALL_PAYMENTS_BY_SPD_ID = "select * from payment where spd_id = ?";
	private static final String CREATE_PAYMENT = "insert into payment ("
			+ "spd_id, payment_type_id, value, date_start, date_finish values (?, ?, ?, ?, ?)";
	private static final String UPDATE_PAYMENT = "update payment set "
			+ "spd_id=?, payment_type_id=?, value=?, date_start=?, date_finish=? where id=?";
	private static final String DELETE_PAYMENT = "delete from payment where id=?";
	private final DataSource dataSource;
	private JdbcManager jdbcManager = new JdbcManager();
	private QueryRunner dbAccess = new QueryRunner();

	public PaymentDAOImpl() {
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

	public void create(Payment payment) throws SQLException {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(CREATE_PAYMENT,
						PreparedStatement.RETURN_GENERATED_KEYS);) {
			jdbcManager.setParameters(statement, payment.getSpdId(), payment.getPaymentTypeId(), payment.getValue(),
					payment.getDateStart(), payment.getDateFinish());
			statement.executeUpdate();
			try (ResultSet generatedKeys = statement.getGeneratedKeys();) {
				if (generatedKeys.next())
					payment.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean delete(Payment payment) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			dbAccess.update(connection, DELETE_PAYMENT, payment.getId());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(connection);
		}
		return false;
	}

	@Override
	public boolean update(Payment payment) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			dbAccess.update(connection, UPDATE_PAYMENT, payment.getSpdId(), payment.getPaymentTypeId(),
					payment.getValue(), payment.getDateStart(), payment.getDateFinish(), payment.getId());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(connection);
		}
		return false;
	}

	@Override
	public Payment selectById(int id) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			ResultSetHandler<Payment> rsh = new BeanHandler<Payment>(Payment.class);
			Payment payment = dbAccess.query(connection, SELECT_PAYMENT_BY_ID, rsh, id);
			return payment;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(connection);
		}
		return null;
	}

	@Override
	public List<Payment> selectAllBySPDId(int spdId) throws SQLException {
		List<Payment> payments = new ArrayList<Payment>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PAYMENTS_BY_SPD_ID);) {
			statement.setInt(1, spdId);
			try (ResultSet results = statement.executeQuery();) {
				while (results.next()) {
					Payment payment = unmarshal(results);
					payments.add(payment);
				}
			}
		}
		return payments;
	}

	private static Payment unmarshal(ResultSet results) throws SQLException {
		Payment payment = new Payment();
		payment.setId(results.getObject("id", Integer.class));
		payment.setSpdId(results.getObject("spd_id", Integer.class));
		payment.setPaymentTypeId(results.getObject("payment_type_id", Integer.class));
		payment.setValue(results.getObject("value", Double.class));
		payment.setDateStart(results.getObject("date_start", Date.class));
		payment.setDateFinish(results.getObject("date_finish", Date.class));

		return payment;
	}

}
