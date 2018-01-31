package in.zylaheath.spring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.zylaheath.spring.entity.PhoneDirectoryEntity;

@Service(value="phoneDirectotyDAOService")
public class PhoneDirectotyDAOService {
	
	@Autowired
	PhoneDirectoryRepository phoneDirectoryRepository;

	public PhoneDirectoryEntity findByPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		return phoneDirectoryRepository.findByPhoneNumber(phoneNumber);
	}

	public PhoneDirectoryEntity save(PhoneDirectoryEntity phoneNumberEntity) {
		// TODO Auto-generated method stub
		return phoneDirectoryRepository.save(phoneNumberEntity);
	}
	
	
}
