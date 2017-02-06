package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Agreement;

public interface AgreementRepository extends JpaRepository<Agreement, Integer> {
	
}
