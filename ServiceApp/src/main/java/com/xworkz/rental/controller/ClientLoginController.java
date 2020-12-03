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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xworkz.rental.dto.ClientComplainDTO;
import com.xworkz.rental.dto.LoginDTO;
import com.xworkz.rental.entity.ClientComplainEntity;
import com.xworkz.rental.entity.CompanyGadgetListEntity;
import com.xworkz.rental.service.ClientLoginService;
import com.xworkz.rental.utility.response.Response;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201","https://x-workzdev.github.io" })
public class ClientLoginController {

	@Autowired
	private ClientLoginService clientLoginService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public ClientLoginController() {
		logger.info("invoking ---------" + this.getClass().getSimpleName());
	}

	@PostMapping("/clientlogin")
	public ResponseEntity<Response> clientLogin(@Valid  @RequestBody  LoginDTO loginDTO) {
		Response response = null;
		try {
			logger.info("invoking clientLoginController.clientLogin()");
			if (loginDTO != null) {
				logger.info("loginDTO not null");
				response = clientLoginService.login(loginDTO);
				logger.info("return response " + response);
			}
		} catch (Exception e) {
			logger.error(e.getClass().getSimpleName());
		}
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping("/viewGadgets/{companyName}")
	public List<CompanyGadgetListEntity> listOfGadgetsByEmailId(@PathVariable("companyName") String companyName) {
		logger.info("invoking clientLoginController.listOfGadgets()");
		List<CompanyGadgetListEntity> response = null;
		try {
			if (companyName != null) {
				logger.info("String emailId = " + companyName);
				response = clientLoginService.getListOfGadgets(companyName);
				logger.info("return response " + response);
			}
		} catch (Exception e) {
			logger.error(e.getClass().getSimpleName());
		}
		return response;
	}

	@PostMapping("/createTicket")
	public ResponseEntity<Response> createTicket(@Valid @RequestBody ClientComplainDTO clientComplainDTO) {
		logger.info("invoking clientLoginController.createTicket()");
		Response response = null;
		try {
			if (clientComplainDTO != null) {
				logger.info("clientComplainDTO not null" + clientComplainDTO);
				response = clientLoginService.createTicket(clientComplainDTO);
				logger.info("returned response " + response);
			}
		} catch (Exception e) {
			logger.error(e.getClass().getSimpleName());
		}
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@GetMapping("/viewTicketsByCompanyName/{companyName}/{emailId}")
	public List<ClientComplainEntity> viewTicketsByCompanyName(@PathVariable("companyName") String companyName,
			@PathVariable("emailId") String emailId) {
		logger.info("invoking clientLoginController.viewTicketsByCompanyName()");
		List<ClientComplainEntity> response = null;
		try {
			if (companyName != null) {
				logger.info("company name is not null " + companyName);
				response = clientLoginService.veiwTicketsByCompanyName(companyName, emailId);
				logger.info("returning response " + response);
			}
		} catch (Exception e) {
			logger.error(e.getClass().getSimpleName());
		}
		return response;
	}
}
