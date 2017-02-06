package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	
}