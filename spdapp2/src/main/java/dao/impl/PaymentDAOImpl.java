package dao.impl;

import java.sql.Connection;
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
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dao.PaymentDAO;
import entity.Payment;

public class PaymentDAOImpl implements PaymentDAO {

	private static final String CONTEXT_LOOKUP = "java:/comp/env/jdbc/spd";
	private static final String SELECT_PAYMENT_BY_ID = "select * from payment where id = ?";
	private static final String SELECT_ALL_PAYMENTS_BY_SPD_ID = "select * from payment where spd_id = ?";
	private static final String CREATE_PAYMENT = "insert into payment ("
			+ "spd_id, payment_type_id, value, date_start, date_finish values (?, ?, ?, ?, ?)";
	private static final String UPDATE_PAYMENT = "update payment set "
			+ "spd_id=?, payment_type_id=?, value=?, date_start=?, date_finish=? where id=?";
	private static final String DELETE_PAYMENT = "delete from payment where id=?";
	private static final List<Payment> EMPTY_LIST = new ArrayList<Payment>();
	private final DataSource dataSource;
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

	@Override
	public int create(Payment payment) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			int id = dbAccess.insert(connection, CREATE_PAYMENT, new ScalarHandler<Integer>(), payment.getSpdId(),
					payment.getPaymentTypeId(), payment.getValue(), payment.getDateStart(), payment.getDateFinal());
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(connection);
		}
		return -1;
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
					payment.getValue(), payment.getDateStart(), payment.getDateFinal(), payment.getId());
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
		Connection connection = dataSource.getConnection();
		try {
			return dbAccess.query(connection, SELECT_ALL_PAYMENTS_BY_SPD_ID, 
					new BeanListHandler<Payment>(Payment.class), Integer.valueOf(spdId));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(connection);
		}
		return EMPTY_LIST;
	}

}
