package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import app.entity.Calculation;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Integer> {
	
	static final String PARAM_AGREEMENT_ID = "agreementId";
	static final String PARAM_SPECIFICATION_ID = "specificationId";
	static final String PARAM_CALCULATION_ID = "calculationId";
	static final String PARAM_ALIAS = "alias";
	static final String FIND_MAX_CALCULATION_NUMBER_BY_SPECIFICATION_ID = "select coalesce(max(c.partNumber),0) from Calculation c "
			+ "where c.specification.id = :specificationId";
	static final String FIND_SUM_OF_CALCULATIONS_BY_SPECIFICATION_ID = "select sum(c.turnover) from Specification s, Calculation c "
			+ "where s.id = c.specification.id and s.id = :specificationId";
	static final String FIND_CLOSING_BALANCE_OF_LAST_CALCULATION_BY_AGREEMENT_ID = "select COALESCE(sc.closing_balance, 0) " 
			+ "from agreement a join specification s on s.agreement_id = a.id join specification_calculation sc on sc.specification_id = s.id "
			+ "where a.id = :agreementId order by sc.id desc limit 1";
	static final String FIND_ACTUAL_ESV_RATE_BY_CALCULATION_ID = "select e.value from ESVTax e "
			+ "where e.dateStart = (select max(e.dateStart) from ESVTax e where e.dateStart <= ("
			+ "select c.dateStart from Calculation c where c.id = ?1))";
	static final String FIND_ACTUAL_SIMPLE_TAX_RATE_BY_CALCULATION_ID = "select s.value from SimpleTax s "
			+ "where s.dateStart = (select max(s.dateStart) from SimpleTax s where s.dateStart <= ("
			+ "select c.dateStart from Calculation c where c.id = :calculationId))";
	static final String FIND_ACTUAL_BANK_COMISSION_RATE_BY_CALCULATION_ID = "select p.value from Payment p, PaymentType pt "
			+ "where p.paymentType.id = pt.id and pt.isBankComission = true and p.spd.id = ("
			+ "select a.spd.id from Agreement a, Specification s, Calculation c "
			+ "where a.id = s.agreement.id and s.id = c.specification.id and c.id = :calculationId) and p.dateStart = ("
			+ "select max(c.dateStart) from Calculation c where c.dateStart <= (select c.dateStart from Calculation c where c.id = :calculationId))";
	static final String FIND_ACTUAL_RATE_BY_ALIAS_AND_CALCULATION_ID = "select coalesce(p.value,0) from payment p, payment_type pt where p.payment_type_id = pt.id and pt.alias = :alias and p.spd_id = ("
			+ "SELECT a.spd_id FROM agreement a, specification s, specification_calculation c WHERE a.id = s.agreement_id AND s.id = c.specification_id and c.id = :calculationId) and p.date_start = ("
			+ "select max(p.date_start) from payment p, payment_type pt where p.payment_type_id = pt.id and pt.alias = :alias and p.spd_id = ("
			+ "SELECT a.spd_id FROM agreement a, specification s, specification_calculation c WHERE a.id = s.agreement_id AND s.id = c.specification_id and c.id = :calculationId) and p.date_start <= ("
			+ "select max(c.date_start) from specification_calculation c where c.id = :calculationId))";
	
	@Transactional
	@Query(FIND_MAX_CALCULATION_NUMBER_BY_SPECIFICATION_ID)
	public Integer findMaxCalculationNumberBySpecificationId(@Param(PARAM_SPECIFICATION_ID) Integer specificationId);
	
	@Transactional
	@Query(FIND_SUM_OF_CALCULATIONS_BY_SPECIFICATION_ID)
	public Double findSumOfCalculationsBySpecificationId(@Param(PARAM_SPECIFICATION_ID) Integer specificationId);
	
	@Transactional
	@Query(value = FIND_CLOSING_BALANCE_OF_LAST_CALCULATION_BY_AGREEMENT_ID, nativeQuery = true)
	public Double findClosingBalanceOfLastCalculationByAgreementId(@Param(PARAM_AGREEMENT_ID) Integer agreementId);
	
	@Transactional
	@Query(FIND_ACTUAL_ESV_RATE_BY_CALCULATION_ID)
	public Double findActualEsvRateByCalculationId(@Param(PARAM_CALCULATION_ID) Integer calculationId);

	@Transactional
	@Query(FIND_ACTUAL_SIMPLE_TAX_RATE_BY_CALCULATION_ID)
	public Double findActualSimpleTaxRateByCalculationId(@Param(PARAM_CALCULATION_ID) Integer calculationId);

	@Transactional
	@Query(FIND_ACTUAL_BANK_COMISSION_RATE_BY_CALCULATION_ID)
	public Double findActualBankComissionRateByCalculationId(@Param(PARAM_CALCULATION_ID) Integer calculationId);
	
	@Transactional
	@Query(value = FIND_ACTUAL_RATE_BY_ALIAS_AND_CALCULATION_ID, nativeQuery = true)
	public Double findActualRateByAliasAndCalculationId(@Param(PARAM_CALCULATION_ID) int calculationId, @Param(PARAM_ALIAS) String alias);
	
}
