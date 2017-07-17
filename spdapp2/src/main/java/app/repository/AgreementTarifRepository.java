package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import app.entity.AgreementTarif;

public interface AgreementTarifRepository extends JpaRepository<AgreementTarif, Integer>{
	
	static final String PARAM_SPECIFICATION_ID = "specificationId";
	static final String FIND_AGREEMENT_TARIF_BY_SPECIFICATION_ID = "select a from AgreementTarif a where a.agreement.id = ("
			+ "select s.agreement.id from Specification s where s.id = :specificationId) and a.dateStart = ("
			+ "select max(a.dateStart) from AgreementTarif a where a.agreement.id = ("
			+ "select s.agreement.id from Specification s where s.id = :specificationId) and a.dateStart <= ("
			+ "select s.dateStart from Specification s where s.id = :specificationId))";
	
	@Transactional
	@Query(FIND_AGREEMENT_TARIF_BY_SPECIFICATION_ID)
	public AgreementTarif findAgreementTarifBySpecificationId(@Param(PARAM_SPECIFICATION_ID) Integer specificationId);
	

}