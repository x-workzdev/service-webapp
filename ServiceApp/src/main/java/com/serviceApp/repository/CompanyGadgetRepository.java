package com.serviceApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serviceApp.entity.CompanyGadgetListEntity;

public interface CompanyGadgetRepository extends JpaRepository<CompanyGadgetListEntity, Integer>{

	List<CompanyGadgetListEntity> findAllByCompanyName(String companyName);

}
