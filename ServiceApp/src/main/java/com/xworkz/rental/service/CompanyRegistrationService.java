package com.xworkz.rental.service;

import java.util.List;

import com.xworkz.rental.dto.CompanyRegistrationDTO;
import com.xworkz.rental.entity.CompanyLoginEntity;
import com.xworkz.rental.utility.response.Response;

public interface CompanyRegistrationService {

	Response companyRegistration(CompanyRegistrationDTO registrationDTO);

	List<CompanyLoginEntity> getAllCompanyAccount();

	
}
