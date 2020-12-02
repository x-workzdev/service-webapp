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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serviceApp.dto.AssignComplaintDTO;
import com.serviceApp.dto.LoginDTO;
import com.serviceApp.entity.ClientComplainEntity;
import com.serviceApp.entity.CompanyGadgetListEntity;
import com.serviceApp.entity.CompanyLoginEntity;
import com.serviceApp.service.CompanyLoginService;
import com.serviceApp.utility.response.Response;

@RestController
//@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201" })
@CrossOrigin(origins = {"https://x-workzdev.github.io"})
@RequestMapping("/api")
public class CompanyLoginController {

	@Autowired
	private CompanyLoginService companyLoginService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public CompanyLoginController() {
		logger.info("invoking ---------" + this.getClass().getSimpleName());
	}

	@GetMapping("/viewAllEngineer")
	public List<CompanyLoginEntity> viewAllEngineer(){
		logger.info("invoking companyLoginController.viewAllEngineer()");
		List<CompanyLoginEntity> response = companyLoginService.viewAllEngineer();
		logger.info("returning response "+ response);
		return response;
	}
	
	@PostMapping("/login")
	public ResponseEntity<Response> companyLogin(@Valid @RequestBody LoginDTO logindto) {
		logger.info("invoking companyLoginController.companyLogin()");
		Response response = null;
		if (logindto != null) {
			logger.info("");
			response = companyLoginService.login(logindto);
			logger.info("");
		}
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping("/viewAllGadgets")
	public List<CompanyGadgetListEntity> viewAllGadgets() {
		logger.info("invoking companyLoginController.viewAllGadgets()");
		List<CompanyGadgetListEntity> response = companyLoginService.veiwAllGadgets();
		logger.info("Returning response");
		return response;
	}
	
	@GetMapping("/viewAllTickets")
	public List<ClientComplainEntity> viewAllTickets() {
		logger.info("invoking companyLoginController.viewAllTickets()");
		List<ClientComplainEntity> response = companyLoginService.veiwAllTicketas();
		logger.info("Returning response");
		return response;
	}
	
	@GetMapping("/viewAllTickets/{emailId}")
	public List<ClientComplainEntity> viewTicketsByEngineerEmail(@PathVariable ("emailId") String emailId) {
		logger.info("invoking companyLoginController.viewTicketsByEngineerEmail()");
		List<ClientComplainEntity> response = companyLoginService.veiwAllTicketas(emailId);
		logger.info("Returning response");
		return response;
	}
	
	@PutMapping ("/assignComplaint")
	public ResponseEntity<Response> assignComplaint(@RequestBody AssignComplaintDTO assignComplaintDTO){
		logger.info("invoking companyLoginController.assignComplaint");
		Response response = null;
		logger.info(""+assignComplaintDTO);
		if(assignComplaintDTO != null) {
			logger.info("dto is not null");
			response = companyLoginService.assignComplaint(assignComplaintDTO);
		}
		return new ResponseEntity<Response> (response , HttpStatus.OK);
	}
}