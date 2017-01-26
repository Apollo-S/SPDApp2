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
import dao.SpecificationDAO;
import entity.Specification;
import utils.JdbcManager;

public class SpecificationDAOImpl implements SpecificationDAO {

	private static final String SELECT_SPECIFICATION_BY_ID = "select * from specification where id = ?";
	private static final String SELECT_MAX_SPECIFICATION_NUMBER_BY_AGREEMENT_ID = "select max(specification_number) FROM specification "
			+ "where agreement_id = ?";
	private static final String SELECT_ALL_SPECIFICATIONS_BY_AGREEMENT_ID = "select * from specification where agreement_id = ?";
	private static final String CREATE_SPECIFICATION = "insert into specification ("
			+ "agreement_id, agreement_tarif_id, specification_number, date_start, date_finish, "
			+ "specification_sum, configuring_hours, programming_hours, architecting_hours, company_id) "
			+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_SPECIFICATION = "update specification set "
			+ "agreement_id=?, agreement_tarif_id=?, specification_number=?, date_start=?, date_finish=? , "
			+ "specification_sum=?, configuring_hours=?, programming_hours=?, architecting_hours=?, company_id=? "
			+ "where id=?";
	private static final String DELETE_SPECIFICATION = "delete from specification where id=?";
	private final JdbcManager jdbcManager = new JdbcManager();

	public void create(Specification specification) throws SQLException {
		try (Connection connection = jdbcManager.getConnection();
				PreparedStatement statement = connection.prepareStatement(CREATE_SPECIFICATION,
						PreparedStatement.RETURN_GENERATED_KEYS);) {
			jdbcManager.setParameters(statement, specification.getAgreementId(), specification.getAgreementTarifId(),
					specification.getSpecificationNumber(), specification.getDateStart(), specification.getDateFinish(),
					specification.getSpecificationSum(), specification.getConfiguringHours(),
					specification.getProgrammingHours(), specification.getArchitectingHours(),
					specification.getCompanyId());
			statement.executeUpdate();
			try (ResultSet generatedKeys = statement.getGeneratedKeys();) {
				if (generatedKeys.next())
					specification.setId(generatedKeys.getInt(1));
			}
		}
	}

	public void update(Specification specification) throws SQLException {
		try (Connection connection = jdbcManager.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_SPECIFICATION);) {
			jdbcManager.setParameters(statement, specification.getAgreementId(), specification.getAgreementTarifId(),
					specification.getSpecificationNumber(), specification.getDateStart(), specification.getDateFinish(),
					specification.getSpecificationSum(), specification.getConfiguringHours(),
					specification.getProgrammingHours(), specification.getArchitectingHours(),
					specification.getCompanyId(), specification.getId());
			statement.executeUpdate();
		}
	}

	public void delete(Specification specification) throws SQLException {
		try (Connection connection = jdbcManager.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_SPECIFICATION);) {
			jdbcManager.setParameters(statement, specification.getId());
			statement.executeUpdate();
		}
	}

	public List<Specification> selectAllByAgreementId(int agreementId) throws SQLException {
		List<Specification> specifications = new ArrayList<Specification>();
		try (Connection connection = jdbcManager.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SPECIFICATIONS_BY_AGREEMENT_ID);) {
			jdbcManager.setParameters(statement, agreementId);
			try (ResultSet results = statement.executeQuery();) {
				while (results.next()) {
					Specification specification = unmarshal(results);
					specifications.add(specification);
				}
			}
		}
		return specifications;
	}

	public int getLastSpecificationNumberByAgreementId(int agreementId) throws SQLException {
		int specificationNumber = 0;
		try (Connection connection = jdbcManager.getConnection();
				PreparedStatement statement = connection
						.prepareStatement(SELECT_MAX_SPECIFICATION_NUMBER_BY_AGREEMENT_ID);) {
			jdbcManager.setParameters(statement, agreementId);
			try (ResultSet results = statement.executeQuery();) {
				while (results.next()) {
					specificationNumber = results.getInt(1);
				}
				if (specificationNumber == 0) {
					return 0;
				}
			}
		}
		return specificationNumber;
	}

	public Specification selectById(int specificationId) throws SQLException {
		try (Connection connection = jdbcManager.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_SPECIFICATION_BY_ID);) {
			jdbcManager.setParameters(statement, specificationId);
			try (ResultSet results = statement.executeQuery();) {
				if (results.next()) {
					return unmarshal(results);
				}
			}
		}
		return null;
	}

	private static Specification unmarshal(ResultSet results) throws SQLException {
		Specification specification = new Specification();
		specification.setId(results.getObject("id", Integer.class));
		specification.setAgreementId(results.getObject("agreement_id", Integer.class));
		specification.setAgreementTarifId(results.getObject("agreement_tarif_id", Integer.class));
		specification.setSpecificationNumber(results.getObject("specification_number", Integer.class));
		specification.setDateStart(results.getObject("date_start", Date.class));
		specification.setDateFinish(results.getObject("date_finish", Date.class));
		specification.setSpecificationSum(results.getObject("specification_sum", Double.class));
		specification.setConfiguringHours(results.getObject("configuring_hours", Integer.class));
		specification.setProgrammingHours(results.getObject("programming_hours", Integer.class));
		specification.setArchitectingHours(results.getObject("architecting_hours", Integer.class));
		specification.setCompanyId(results.getObject("company_id", Integer.class));
		return specification;
	}
}
