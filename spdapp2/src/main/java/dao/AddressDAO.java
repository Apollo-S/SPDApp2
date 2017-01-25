package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Address;

public interface AddressDAO {
	public void create(Address address) throws SQLException;
	public void delete(Address address) throws SQLException;
	public void update(Address address) throws SQLException;
	public Address selectById(int id) throws SQLException;
	
}
