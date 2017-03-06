package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.CompanyAddress;

public interface CompanyAddressRepository extends JpaRepository<CompanyAddress, Integer> {
	
}