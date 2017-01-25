package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Specification;

public interface SpecificationDAO {
	public void create(Specification specification) throws SQLException;
	public void delete(Specification specification) throws SQLException;
	public void update(Specification specification) throws SQLException;
	public Specification selectById(int specificationId) throws SQLException;
	public List<Specification> selectAllByAgreementId(int agreementId) throws SQLException;
	
}
