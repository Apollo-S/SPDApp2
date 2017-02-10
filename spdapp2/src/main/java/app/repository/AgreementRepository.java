package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entity.Agreement;

public interface AgreementRepository extends JpaRepository<Agreement, Integer> {
	
	@Query(value = "SELECT COALESCE(max(specification_number),0) FROM specification WHERE agreement_id = ?1", 
			nativeQuery = true)
	  public Integer findMaxSpecificationNumberByAgreementId(Integer agreementId);
	
}
