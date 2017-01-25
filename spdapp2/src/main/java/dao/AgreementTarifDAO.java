package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import entity.AgreementTarif;

public interface AgreementTarifDAO {
	public void create(AgreementTarif tarif) throws SQLException;
	public void delete(AgreementTarif tarif) throws SQLException;
	public void update(AgreementTarif tarif) throws SQLException;
	public AgreementTarif selectById(int id) throws SQLException;
	public AgreementTarif selectByActualDate(int tarifId, Date date) throws SQLException;
	public List<AgreementTarif> selectAllByAgreementId(int agreementId) throws SQLException;
	
	
}
