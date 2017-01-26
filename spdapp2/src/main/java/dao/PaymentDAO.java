package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Payment;

public interface PaymentDAO {
	public void create(Payment payment) throws SQLException;
	public boolean delete(Payment payment) throws SQLException;
	public boolean update(Payment payment) throws SQLException;
	public Payment selectById(int id) throws SQLException;
	public List<Payment> selectAllBySPDId(int spdId) throws SQLException;
	
}
