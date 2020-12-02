package com.serviceApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serviceApp.entity.CompanyLoginEntity;

public interface LoginRepository extends JpaRepository<CompanyLoginEntity, Integer> {

	CompanyLoginEntity findByEmailId(String emailId);

	List<CompanyLoginEntity> findAllByRole(String role);

	CompanyLoginEntity findByRole(String string);

}
