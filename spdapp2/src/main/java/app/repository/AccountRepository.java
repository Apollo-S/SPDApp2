package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import app.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	
	static final String PARAM_SPD_ID = "spdId";
	static final String FIND_ACTUAL_SPD_ACCOUNT_BY_SPD_ID = "select ac from Account ac "
			+ "where ac.spd.id = :spdId";
	
	@Transactional
	@Query(FIND_ACTUAL_SPD_ACCOUNT_BY_SPD_ID)
	public Account findActualSpdAccountBySpdId(@Param(PARAM_SPD_ID) Integer spdId);
	
}