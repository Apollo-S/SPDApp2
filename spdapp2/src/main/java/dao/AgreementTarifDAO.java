package dao;

import java.sql.SQLException;
import java.util.List;

import beans.AgreementTarif;

public interface AgreementTarifDAO {
	public void create(AgreementTarif tarif) throws SQLException;
	public void delete(AgreementTarif tarif) throws SQLException;
	public void update(AgreementTarif tarif) throws SQLException;
	public AgreementTarif selectById(int id) throws SQLException;
	public List<AgreementTarif> selectAllByAgreementId(int spdId) throws SQLException;
	
}
