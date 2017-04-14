package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.entity.Calculation;
import app.entity.Payment;

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
			+ "where a.id = s.agreement.id and s.id = c.specification.id and c.id = ?1) and p.dateStart <= ("
			+ "select c.dateStart from Calculation c where c.id = ?1)";

	@Transactional
	@Query(FIND_ACTUAL_ESV_RATE_BY_CALCULATION_ID)
	public Double findActualEsvRateByCalculationId(Integer calculationId);

	@Transactional
	@Query(FIND_ACTUAL_SIMPLE_TAX_RATE_BY_CALCULATION_ID)
	public Double findActualSimpleTaxRateByCalculationId(Integer calculationId);

	@Transactional
	@Query(FIND_ACTUAL_BANK_COMISSION_RATE_BY_CALCULATION_ID)
	public Double getActualBankComissionRateByCalculationId(Integer calculationId);

}
