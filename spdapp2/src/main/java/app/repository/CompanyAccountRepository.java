package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import app.entity.CompanyAccount;

public interface CompanyAccountRepository extends JpaRepository<CompanyAccount, Integer> {
	
	static final String PARAM_SPECIFICATION_ID = "specificationId";
	static final String FIND_ACTUAL_COMPANY_ACCOUNT_BY_SPECIFICATION_ID = "select ca from CompanyAccount ca where ca.company.id = ("
			+ "select a.company.id from Agreement a, Specification s where a.id = s.agreement.id and s.id = :specificationId) and ca.dateStart = ("
			+ "select max(ca.dateStart) from CompanyAccount ca where ca.company.id = ("
			+ "select a.company.id from Agreement a, Specification s where a.id = s.agreement.id and s.id = :specificationId) and ca.dateStart <= ("
			+ "select s.dateStart from Specification s where s.id = :specificationId))";
	
	@Transactional
	@Query(FIND_ACTUAL_COMPANY_ACCOUNT_BY_SPECIFICATION_ID)
	public CompanyAccount findActualCompanyAccountBySpecificationId(@Param(PARAM_SPECIFICATION_ID) Integer specificationId);

}