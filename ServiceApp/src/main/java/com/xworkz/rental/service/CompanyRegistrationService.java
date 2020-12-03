package com.xworkz.rental.service;

import com.xworkz.rental.dto.CompanyRegistrationDTO;
import com.xworkz.rental.utility.response.Response;

public interface CompanyRegistrationService {

	Response companyRegistration(CompanyRegistrationDTO registrationDTO);

	
}
