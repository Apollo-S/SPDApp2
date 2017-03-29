package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.entity.Calculation;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Integer> {

}
