package com.serviceApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serviceApp.dto.RegistrationDTO;
import com.serviceApp.entity.RegistrationEntity;
import com.serviceApp.service.RegistrationService;
import com.serviceApp.utility.response.Response;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201","https://x-workzdev.github.io"})
@RequestMapping("/api")
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public RegistrationController() {
		logger.info("invoking ---------" + this.getClass().getSimpleName());
	}

	@PostMapping("/registration")
	public ResponseEntity<Response> clientRegistration(@Valid  RegistrationDTO registrationDTO) {
		logger.info("invoking registrationController.clientRegistration() ");
		Response response = null ;
		if (registrationDTO != null) {
			logger.info("registrationDto not null" + registrationDTO);
			response=registrationService.clientRegistration(registrationDTO);
			logger.info("Returning respone "+response);
		}
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping("/registeredClient")
	public List<RegistrationEntity> getAllRegisteredClient() {
		logger.info("invoking registrationController.getAllRegisteredClient()");
		List<RegistrationEntity> response =registrationService.getAllClients();
		logger.info("returning response "+response);
		return response;
	}
	
	@PutMapping("/updateClient/{companyName}")
	public ResponseEntity<Response> deleteClient(@PathVariable ("companyName") String companyName){
		logger.info("invoking registrationController.updateClient()");
		Response response =registrationService.deleteClient(companyName);
		logger.info("returning response "+ response);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
}
