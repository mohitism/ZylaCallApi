package in.zylaheath.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.zylaheath.spring.entity.PhoneDirectoryEntity;

@Repository
interface PhoneDirectoryRepository extends CrudRepository<PhoneDirectoryEntity,Integer>{

	PhoneDirectoryEntity findByPhoneNumber(String phoneNumber);

	@Query(value = "select * from phone_directory where status = 0 limit 1",nativeQuery=true)
	PhoneDirectoryEntity findInactivePhoneNumber();

}
