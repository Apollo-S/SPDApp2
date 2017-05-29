package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.CompanyAccount;

public interface CompanyAccountRepository extends JpaRepository<CompanyAccount, Integer> {
	
}