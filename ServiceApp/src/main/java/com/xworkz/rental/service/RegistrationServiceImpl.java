package com.xworkz.rental.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.xworkz.rental.dto.RegistrationDTO;
import com.xworkz.rental.entity.RegistrationEntity;
import com.xworkz.rental.repository.RegistrationRepository;
import com.xworkz.rental.utility.mailSender.JMS;
import com.xworkz.rental.utility.passwordGenerater.AutoGenerateString;
import com.xworkz.rental.utility.response.Response;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private Environment environment;

	@Autowired
	private JMS javaMailSender;

	@Autowired
	private RegistrationRepository registrationRepository;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public RegistrationServiceImpl() {
		logger.info("invoking " + this.getClass().getSimpleName());
	}

	@Override
	public Response clientRegistration(RegistrationDTO registrationDTO) {
		logger.info("invoking registrationServiceImpl.clientRegistration()");
		try {
			RegistrationEntity registrationEntity = new RegistrationEntity();
			RegistrationEntity entity = registrationRepository.findByCompanyName(registrationDTO.getCompanyName());
			logger.info("checkiing for client already there or not" + registrationDTO);
			if (entity == null) {
				logger.info("client not found , registering client");
				BeanUtils.copyProperties(registrationDTO, registrationEntity);
				logger.info("copyed property from registrationdto to registrationentity");
				registrationEntity.setPassword(AutoGenerateString.autoGenerateString());
				logger.info("set autogeneratedpassword to password");
				registrationEntity.setDate(new Date());
				logger.info("set current date and time to date");
				registrationEntity.setAuditStatus("INSERT");
				logger.info("set status as INSERT");
				javaMailSender.registrationMail(registrationDTO.getEmailId(), registrationEntity.getPassword());
				logger.info("sending mail to " + registrationDTO.getEmailId() + " with password");
				logger.info("returning response from registrationServiceImpl()");
				RegistrationEntity registered = registrationRepository.save(registrationEntity);
				logger.info("registration details saved");
				return new Response(environment.getProperty("USER_REGISTERD"),
						environment.getProperty("SERVER_CODE_SUCCESS"), registered);
			} else if (!registrationDTO.getEmailId().equals(entity.getEmailId())) {
				logger.info("client not found , registering client");
				BeanUtils.copyProperties(registrationDTO, registrationEntity);
				logger.info("copyed property from registrationdto to registrationentity");
				registrationEntity.setPassword(AutoGenerateString.autoGenerateString());
				logger.info("set autogeneratedpassword to password");
				registrationEntity.setDate(new Date());
				logger.info("set current date and time to date");
				registrationEntity.setAuditStatus("INSERT");
				logger.info("set status as INSERT");
				javaMailSender.registrationMail(registrationDTO.getEmailId(), registrationEntity.getPassword());
				logger.info("sending mail to " + registrationDTO.getEmailId() + " with password");
				logger.info("returning response from registrationServiceImpl()");
				RegistrationEntity registered = registrationRepository.save(registrationEntity);
				logger.info("registration details saved");
				return new Response(environment.getProperty("USER_REGISTERD"),
						environment.getProperty("SERVER_CODE_SUCCESS"), registered);
			} else {
				logger.info("client found can not registered details ");
				return new Response(environment.getProperty("CLIENT_PRESENT"),
						environment.getProperty("SERVER_CODE_ERROR"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response("you have an exception in " + this.getClass().getSimpleName(), e.getMessage());
		}
	}

	@Override
	public List<RegistrationEntity> getAllClients() {
		logger.info("invoking registrationServiceImpl.getAllClients()");
		List<RegistrationEntity> registrationEntity = null;
		try {
			registrationEntity = registrationRepository.findAllByAuditStatus("INSERT");
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return registrationEntity;
	}

	@Override
	public Response deleteClient(String companyName) {
		logger.info("invoking registrationServiceImpl.deleteClient()");
		try {
			RegistrationEntity registrationEntity = registrationRepository.findByCompanyName(companyName);
			logger.info("searching for company name");
			if (registrationEntity != null) {
				logger.info("company found " + registrationEntity);
				registrationEntity.setAuditStatus("DELETED");
				logger.info("Client successfully deleted");
				registrationRepository.save(registrationEntity);
				logger.info("returning response " + registrationEntity);
				return new Response(environment.getProperty("DELETED_CLIENT"),
						environment.getProperty("SERVER_CODE_SUCCESS"), registrationEntity);
			} else {

				return new Response(environment.getProperty("CLIENT_NOT_FOUND"),
						environment.getProperty("SERVER_CODE_ERROR"), registrationEntity);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response("you have an exception in " + this.getClass().getSimpleName(), e.getMessage());
		}
	}
}
