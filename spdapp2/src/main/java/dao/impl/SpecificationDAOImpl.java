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

public class SpecificationDAOImpl implements SpecificationDAO {

	private static final String CONTEXT_LOOKUP = "java:/comp/env/jdbc/spd";
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

	private final DataSource dataSource;

	public SpecificationDAOImpl() {
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

	public void create(Specification specification) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(CREATE_SPECIFICATION,
					PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setInt(1, specification.getAgreementId());
			statement.setInt(2, specification.getAgreementTarifId());
			statement.setInt(3, specification.getSpecificationNumber());
			statement.setDate(4, specification.getDateStart());
			statement.setDate(5, specification.getDateFinish());
			statement.setDouble(6, specification.getSpecificationSum());
			statement.setInt(7, specification.getConfiguringHours());
			statement.setInt(8, specification.getProgrammingHours());
			statement.setInt(9, specification.getArchitectingHours());
			statement.setInt(10, specification.getCompanyId());
			statement.executeUpdate();
			try {
				ResultSet generatedKeys = statement.getGeneratedKeys();
				try {
					if (generatedKeys.next())
						specification.setId(generatedKeys.getInt(1));
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

	public void update(Specification specification) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE_SPECIFICATION);
			statement.setInt(1, specification.getAgreementId());
			statement.setInt(2, specification.getAgreementTarifId());
			statement.setInt(3, specification.getSpecificationNumber());
			statement.setDate(4, specification.getDateStart());
			statement.setDate(5, specification.getDateFinish());
			statement.setDouble(6, specification.getSpecificationSum());
			statement.setInt(7, specification.getConfiguringHours());
			statement.setInt(8, specification.getProgrammingHours());
			statement.setInt(9, specification.getArchitectingHours());
			statement.setInt(10, specification.getCompanyId());
			statement.setInt(11, specification.getId());
			try {
				statement.executeUpdate();
			} finally {
				statement.close();
			}
		} finally {
			connection.close();
		}
	}

	public void delete(Specification specification) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(DELETE_SPECIFICATION);
			statement.setInt(1, specification.getId());
			try {
				statement.executeUpdate();
			} finally {
				statement.close();
			}
		} finally {
			connection.close();
		}
	}

	public List<Specification> selectAllByAgreementId(int agreementId) throws SQLException {
		List<Specification> specifications = new ArrayList<Specification>();
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SPECIFICATIONS_BY_AGREEMENT_ID);
			statement.setInt(1, agreementId);
			try {
				ResultSet results = statement.executeQuery();
				try {
					while (results.next()) {
						Specification specification = unmarshal(results);
						specifications.add(specification);
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
		return specifications;
	}
	
	public int getLastSpecificationNumberByAgreementId(int agreementId) throws SQLException {
		int specificationNumber = 0;
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SELECT_MAX_SPECIFICATION_NUMBER_BY_AGREEMENT_ID);
			statement.setInt(1, agreementId);
			try {
				ResultSet results = statement.executeQuery();
				try {
					while (results.next()) {
						specificationNumber = results.getInt(1);
					}
					if (specificationNumber == 0) {
						return 0;
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
		return specificationNumber;
	}
		


	public Specification selectById(int specificationId) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SELECT_SPECIFICATION_BY_ID);
			statement.setInt(1, specificationId);
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
