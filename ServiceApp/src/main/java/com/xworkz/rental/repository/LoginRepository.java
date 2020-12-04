package com.xworkz.rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xworkz.rental.entity.CompanyLoginEntity;

public interface LoginRepository extends JpaRepository<CompanyLoginEntity, Integer> {

	CompanyLoginEntity findByEmailId(String emailId);

	@Query(value = "select email_id from companylogin where role= :role " , nativeQuery = true)
	List<String> findAllByRole(String role);

	CompanyLoginEntity findByRole(String string);

	List<CompanyLoginEntity> findAllByEmailId(String emailId);

}
