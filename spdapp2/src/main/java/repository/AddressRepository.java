package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	
}
