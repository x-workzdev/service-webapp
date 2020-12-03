package com.xworkz.rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xworkz.rental.entity.RegistrationEntity;

public interface RegistrationRepository extends JpaRepository<RegistrationEntity, String> {

	RegistrationEntity findByCompanyName(String companyName);
	
	RegistrationEntity findByEmailId(String emailId);

	RegistrationEntity deleteByCompanyName(String companyName);

	List<RegistrationEntity> findAllByAuditStatus(String auditStatus);

}
