package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.entity.Payment;
import app.entity.Specification;

public interface SpecificationRepository extends JpaRepository<Specification, Integer> {
	
	@Query("select coalesce(max(c.partNumber),0) from Calculation c where c.specification.id = :specificationId")
	public Integer findMaxCalculationNumberBySpecificationId(@Param("specificationId") Integer specificationId);
	
	@Query("select sum(c.turnover) from Specification s, Calculation c where s.id = c.specification.id and s.id = :specificationId")
	public Double findSumOfCalculationsBySpecificationId(@Param("specificationId") Integer specificationId);
	
	@Query(value = "SELECT COALESCE(c.closing_balance, 0) FROM specification_calculation c WHERE c.specification_id = :specificationId ORDER BY c.id DESC LIMIT 1", nativeQuery = true)
	public Double findClosingBalanceOfLastCalculationBySpecificationId(@Param("specificationId") Integer specificationId);
	
	
}
