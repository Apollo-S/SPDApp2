package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.entity.Calculation;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Integer> {

	static final String FIND_ACTUAL_ESV_RATE_BY_CALCULATION_ID = "select e.value from ESVTax e "
			+ "where e.dateStart = (select max(e.dateStart) from ESVTax e where e.dateStart <= ("
			+ "select c.dateStart from Calculation c where c.id = ?1))";
	static final String FIND_ACTUAL_SIMPLE_TAX_RATE_BY_CALCULATION_ID = "select s.value from SimpleTax s "
			+ "where s.dateStart = (select max(s.dateStart) from SimpleTax s where s.dateStart <= ("
			+ "select c.dateStart from Calculation c where c.id = ?1))";
	static final String FIND_ACTUAL_BANK_COMISSION_RATE_BY_CALCULATION_ID = "select p.value from Payment p, PaymentType pt "
			+ "where p.paymentType.id = pt.id and pt.isBankComission = true and p.spd.id = ("
			+ "select a.spd.id from Agreement a, Specification s, Calculation c "
			+ "where a.id = s.agreement.id and s.id = c.specification.id and c.id = ?1) and p.dateStart = ("
			+ "select max(c.dateStart) from Calculation c where c.dateStart <= (select c.dateStart from Calculation c where c.id = ?1))";
	static final String FIND_ACTUAL_RATE_BY_ALIAS_AND_CALCULATION_ID = "select coalesce(p.value,0) from payment p, payment_type pt where p.payment_type_id = pt.id and pt.alias = :alias and p.spd_id = ("
			+ "SELECT a.spd_id FROM agreement a, specification s, specification_calculation c WHERE a.id = s.agreement_id AND s.id = c.specification_id and c.id = :calculationId) and p.date_start = ("
			+ "select max(p.date_start) from payment p, payment_type pt where p.payment_type_id = pt.id and pt.alias = :alias and p.spd_id = ("
			+ "SELECT a.spd_id FROM agreement a, specification s, specification_calculation c WHERE a.id = s.agreement_id AND s.id = c.specification_id and c.id = :calculationId) and p.date_start <= ("
			+ "select max(c.date_start) from specification_calculation c where c.id = :calculationId))";

	@Transactional
	@Query(FIND_ACTUAL_ESV_RATE_BY_CALCULATION_ID)
	public Double findActualEsvRateByCalculationId(Integer calculationId);

	@Transactional
	@Query(FIND_ACTUAL_SIMPLE_TAX_RATE_BY_CALCULATION_ID)
	public Double findActualSimpleTaxRateByCalculationId(Integer calculationId);

	@Transactional
	@Query(FIND_ACTUAL_BANK_COMISSION_RATE_BY_CALCULATION_ID)
	public Double findActualBankComissionRateByCalculationId(Integer calculationId);
	
	@Transactional
	@Query(value = FIND_ACTUAL_RATE_BY_ALIAS_AND_CALCULATION_ID, nativeQuery = true)
	Double findActualRateByAliasAndCalculationId(@Param("calculationId") int calculationId, @Param("alias") String alias);
	
}
