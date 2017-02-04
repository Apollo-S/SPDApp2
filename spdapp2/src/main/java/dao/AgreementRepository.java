package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import entity.Agreement;

public interface AgreementRepository extends JpaRepository<Agreement, Integer> {
	
}
