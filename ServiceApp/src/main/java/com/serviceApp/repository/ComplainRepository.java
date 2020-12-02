package com.serviceApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.serviceApp.entity.ClientComplainEntity;

public interface ComplainRepository extends JpaRepository<ClientComplainEntity, String> {
	// change this to sql query

	@Query(value = "select company.* from CLIENT_COMPLAINT as company inner join REGISTRATION as \r\n"
			+ "reg on reg.COMPANY_NAME=company.COMPANY_NAME \r\n"
			+ "where reg.COMPANY_NAME= :companyName AND reg.EMAIL_ID= :emailId", nativeQuery = true)
	List<ClientComplainEntity> findAllByCompanyName(String companyName , String emailId);

	ClientComplainEntity findByComplaintId(String complaintId);

	List<ClientComplainEntity> findAllByEngineerEmail(String emailId);

}