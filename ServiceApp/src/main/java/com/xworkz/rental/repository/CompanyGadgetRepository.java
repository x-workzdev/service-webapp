package com.xworkz.rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xworkz.rental.entity.CompanyGadgetListEntity;

public interface CompanyGadgetRepository extends JpaRepository<CompanyGadgetListEntity, Integer>{

	List<CompanyGadgetListEntity> findAllByCompanyName(String companyName);

}
