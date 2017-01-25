package dao;

import java.sql.SQLException;
import java.util.List;

import entity.SPD;

public interface SPDDAO {
	public void create(SPD spd) throws SQLException;
	public void delete(SPD spd) throws SQLException;
	public void update(SPD spd) throws SQLException;
	public List<SPD> selectAll() throws SQLException;
	public SPD selectById(int id) throws SQLException;
	
}
