package com.xworkz.rental.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xworkz.rental.dto.CompanyRegistrationDTO;
import com.xworkz.rental.entity.CompanyLoginEntity;
import com.xworkz.rental.service.CompanyRegistrationService;
import com.xworkz.rental.utility.response.Response;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201","https://x-workzdev.github.io" })
public class CompanyRegistration {
	@Autowired
	private CompanyRegistrationService companyRegistrationService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public CompanyRegistration() {
		logger.info("invoking ---------" + this.getClass().getSimpleName());
	}

	@PostMapping("/companyRegistration")
	public ResponseEntity<Response> companyRegistration(@Valid @RequestBody CompanyRegistrationDTO registrationDTO) {
		logger.info("invoking registrationController.clientRegistration() ");
		Response response = null;
		try {
		//	if (registrationDTO != null) {
				logger.info("registrationDto not null" + registrationDTO);
				response = companyRegistrationService.companyRegistration(registrationDTO);
				logger.info("Returning respone " + response);
				return new ResponseEntity<Response>(response, HttpStatus.OK);
			//}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ResponseEntity<Response>(new Response(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.OK);
		}
		//return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getAllCompanyAccount")
	public List<CompanyLoginEntity> getAllCompanyAccount(){
		logger.info("invoking registrationController.getAllCompanyAccount() ");
		List<CompanyLoginEntity> companyLoginEntities = null;
		try {
			logger.info("getting company accounts");
			companyLoginEntities = companyRegistrationService.getAllCompanyAccount();
			logger.info("company account find and return list of account");
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return companyLoginEntities;
	}
}
