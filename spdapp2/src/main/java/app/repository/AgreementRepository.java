package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.entity.Agreement;
import app.entity.AgreementTarif;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, Integer> {

	@Query(value = "SELECT COALESCE(max(specification_number),0) FROM specification WHERE agreement_id = ?1", nativeQuery = true)
	public Integer findMaxSpecificationNumberByAgreementId(Integer agreementId);

	@Transactional
	@Query("select a from AgreementTarif a where a.agreement.id = (select s.agreement.id from Specification s where s.id = ?1) and a.dateStart = (select max(a.dateStart) from AgreementTarif a where a.agreement.id = (select s.agreement.id from Specification s where s.id = ?1) and a.dateStart <= (select s.dateStart from Specification s where s.id = ?1))")
	public AgreementTarif findAgreementTarifBySpecificationId(Integer specificationId);
	
}
