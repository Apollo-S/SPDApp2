package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.entity.Account;
import app.entity.Agreement;
import app.entity.AgreementTarif;
import app.entity.CompanyAccount;
import app.entity.CompanyAddress;
import app.entity.CompanyDirector;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, Integer> {
	
	static final String FIND_MAX_SPECIFICATION_NUMBER_BY_AGREEMENT_ID = "SELECT COALESCE(max(specification_number),0) FROM specification WHERE agreement_id = :agreementId";
	static final String FIND_AGREEMENT_TARIF_BY_SPECIFICATION_ID = "select a from AgreementTarif a where a.agreement.id = ("
			+ "select s.agreement.id from Specification s where s.id = ?1) and a.dateStart = ("
			+ "select max(a.dateStart) from AgreementTarif a where a.agreement.id = ("
			+ "select s.agreement.id from Specification s where s.id = ?1) and a.dateStart <= ("
			+ "select s.dateStart from Specification s where s.id = ?1))";
	static final String FIND_ACTUAL_COMPANY_DIRECTOR_BY_SPECIFICATION_ID = "select cd from CompanyDirector cd where cd.company.id = ("
			+ "select a.company.id from Agreement a, Specification s where a.id = s.agreement.id and s.id = :specificationId) and cd.employmentDate = ("
			+ "select max(cd.employmentDate) from CompanyDirector cd where cd.company.id = ("
			+ "select a.company.id from Agreement a, Specification s where a.id = s.agreement.id and s.id = :specificationId) and cd.employmentDate <= ("
			+ "select s.dateStart from Specification s where s.id = :specificationId))";
	static final String FIND_ACTUAL_COMPANY_ADDRESS_BY_SPECIFICATION_ID = "select ca from CompanyAddress ca where ca.company.id = ("
			+ "select a.company.id from Agreement a, Specification s where a.id = s.agreement.id and s.id = :specificationId) and ca.dateStart = ("
			+ "select max(ca.dateStart) from CompanyAddress ca where ca.company.id = ("
			+ "select a.company.id from Agreement a, Specification s where a.id = s.agreement.id and s.id = :specificationId) and ca.dateStart <= ("
			+ "select s.dateStart from Specification s where s.id = :specificationId))";
	static final String FIND_ACTUAL_COMPANY_ACCOUNT_BY_SPECIFICATION_ID = "select ca from CompanyAccount ca where ca.company.id = ("
			+ "select a.company.id from Agreement a, Specification s where a.id = s.agreement.id and s.id = :specificationId) and ca.dateStart = ("
			+ "select max(ca.dateStart) from CompanyAccount ca where ca.company.id = ("
			+ "select a.company.id from Agreement a, Specification s where a.id = s.agreement.id and s.id = :specificationId) and ca.dateStart <= ("
			+ "select s.dateStart from Specification s where s.id = :specificationId))";
	static final String FIND_ACTUAL_SPD_ACCOUNT_BY_SPD_ID = "select ac from Account ac where ac.spd.id = :spdId";
	@Query(value = FIND_MAX_SPECIFICATION_NUMBER_BY_AGREEMENT_ID, nativeQuery = true)
	public Integer findMaxSpecificationNumberByAgreementId(@Param("agreementId") Integer agreementId);

	@Transactional
	@Query(FIND_AGREEMENT_TARIF_BY_SPECIFICATION_ID)
	public AgreementTarif findAgreementTarifBySpecificationId(@Param("specificationId") Integer specificationId);
	
	@Transactional
	@Query(FIND_ACTUAL_COMPANY_DIRECTOR_BY_SPECIFICATION_ID)
	public CompanyDirector findActualDirectorBySpecificationId(@Param("specificationId") Integer specificationId);
	
	@Transactional
	@Query(FIND_ACTUAL_COMPANY_ADDRESS_BY_SPECIFICATION_ID)
	public CompanyAddress findActualCompanyAddressBySpecificationId(@Param("specificationId") Integer specificationId);
	
	@Transactional
	@Query(FIND_ACTUAL_COMPANY_ACCOUNT_BY_SPECIFICATION_ID)
	public CompanyAccount findActualCompanyAccountBySpecificationId(@Param("specificationId") Integer specificationId);
	
	@Transactional
	@Query(FIND_ACTUAL_SPD_ACCOUNT_BY_SPD_ID)
	public Account findActualSpdAccountBySpdId(@Param("spdId") Integer spdId);
	
}
