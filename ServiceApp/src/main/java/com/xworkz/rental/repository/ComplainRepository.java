package com.xworkz.rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xworkz.rental.entity.ClientComplainEntity;

public interface ComplainRepository extends JpaRepository<ClientComplainEntity, String> {
	// change this to sql query

	@Query(value = "select company.* from ClientComplaint as company inner join Registration as reg on reg.ID = company.ID where reg.COMPANY_NAME= :companyName AND reg.EMAIL_ID= :emailId", nativeQuery = true)
	List<ClientComplainEntity> findAllByCompanyName(String companyName , String emailId);

	ClientComplainEntity findByComplaintId(String complaintId);

	List<ClientComplainEntity> findAllByEngineerEmail(String emailId);

}