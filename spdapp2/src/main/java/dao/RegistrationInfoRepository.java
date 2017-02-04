package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import entity.RegistrationInfo;

public interface RegistrationInfoRepository extends JpaRepository<RegistrationInfo, Integer> {

}
