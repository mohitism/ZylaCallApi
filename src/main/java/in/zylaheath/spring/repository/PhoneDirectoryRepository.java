package in.zylaheath.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.zylaheath.spring.entity.PhoneDirectoryEntity;

@Repository
interface PhoneDirectoryRepository extends CrudRepository<PhoneDirectoryEntity,Integer>{

	PhoneDirectoryEntity findByPhoneNumber(String phoneNumber);

}
