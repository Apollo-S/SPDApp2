package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import entity.Specification;

public interface SpecificationRepository extends JpaRepository<Specification, Integer> {
	
}
