package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.CompanyDirector;

public interface CompanyDirectorRepository extends JpaRepository<CompanyDirector, Integer> {
	
}