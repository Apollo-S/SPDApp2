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

import dao.AgreementTarifDAO;
import entity.AgreementTarif;

public class AgreementTarifDAOImpl implements AgreementTarifDAO {

	private static final String CONTEXT_LOOKUP = "java:/comp/env/jdbc/spd";
	private static final String SELECT_AGREEMENT_TARIF_BY_ID = "select * from agreement_tarif where id = ?";
	private static final String SELECT_AGREEMENT_TARIF_BY_ACTUAL_DATE = "select * from agreement_tarif WHERE agreement_id = ? and datestart = "
			+ "(select max(datestart) FROM agreement_tarif where agreement_id = ? and datestart <= ?)";
	private static final String SELECT_ALL_AGREEMENT_TARIFS_BY_AGREEMENT_ID = "select * from agreement_tarif where agreement_id = ?";
	private static final String CREATE_AGREEMENT_TARIF = "insert into agreement_tarif (agreement_id, configuring, programming, architecting, datestart) "
			+ "values (?, ?, ?, ?, ?)";
	private static final String UPDATE_AGREEMENT = "update agreement_tarif set agreement_id=?, configuring=?, programming=?, architecting=?, datestart=? where id=?";
	private static final String DELETE_AGREEMENT = "delete from agreement_tarif where id=?";

	private final DataSource dataSource;

	public AgreementTarifDAOImpl() {
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
	public void create(AgreementTarif tarif) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(CREATE_AGREEMENT_TARIF,
					PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setInt(1, tarif.getAgreementId());
			statement.setDouble(2, tarif.getConfiguring());
			statement.setDouble(3, tarif.getProgramming());
			statement.setDouble(4, tarif.getArchitecting());
			statement.setDate(5, tarif.getDateStart());
			statement.executeUpdate();
			try {
				ResultSet generatedKeys = statement.getGeneratedKeys();
				try {
					if (generatedKeys.next())
						tarif.setId(generatedKeys.getInt(1));
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

	@Override
	public void update(AgreementTarif tarif) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE_AGREEMENT);
			statement.setInt(1, tarif.getAgreementId());
			statement.setDouble(2, tarif.getConfiguring());
			statement.setDouble(3, tarif.getProgramming());
			statement.setDouble(4, tarif.getArchitecting());
			statement.setDate(5, tarif.getDateStart());
			statement.setInt(6, tarif.getId());
			try {
				statement.executeUpdate();
			} finally {
				statement.close();
			}
		} finally {
			connection.close();
		}
	}

	@Override
	public void delete(AgreementTarif tarif) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(DELETE_AGREEMENT);
			statement.setInt(1, tarif.getId());
			try {
				statement.executeUpdate();
			} finally {
				statement.close();
			}
		} finally {
			connection.close();
		}
	}

	@Override
	public AgreementTarif selectById(int tarifId) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SELECT_AGREEMENT_TARIF_BY_ID);
			statement.setInt(1, tarifId);
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

	@Override
	public AgreementTarif selectByActualDate(int tarifId, Date date) throws SQLException {
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SELECT_AGREEMENT_TARIF_BY_ACTUAL_DATE);
			statement.setInt(1, tarifId);
			statement.setInt(2, tarifId);
			statement.setDate(3, date);
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
	
	@Override
	public List<AgreementTarif> selectAllByAgreementId(int agreementId) throws SQLException {
		List<AgreementTarif> tarifs = new ArrayList<AgreementTarif>();
		Connection connection = dataSource.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SELECT_ALL_AGREEMENT_TARIFS_BY_AGREEMENT_ID);
			statement.setInt(1, agreementId);
			try {
				ResultSet results = statement.executeQuery();
				try {
					while (results.next()) {
						AgreementTarif tarif = unmarshal(results);
						tarifs.add(tarif);
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
		return tarifs;
	}

	private static AgreementTarif unmarshal(ResultSet results) throws SQLException {
		AgreementTarif tarif = new AgreementTarif();
		tarif.setId(results.getObject("id", Integer.class));
		tarif.setAgreementId(results.getObject("agreement_id", Integer.class));
		tarif.setConfiguring(results.getObject("configuring", Double.class));
		tarif.setProgramming(results.getObject("programming", Double.class));
		tarif.setArchitecting(results.getObject("architecting", Double.class));
		tarif.setDateStart(results.getObject("datestart", Date.class));
		return tarif;
	}

}
