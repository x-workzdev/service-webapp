package com.xworkz.rental.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.xworkz.rental.dto.CompanyRegistrationDTO;
import com.xworkz.rental.entity.CompanyLoginEntity;
import com.xworkz.rental.repository.LoginRepository;
import com.xworkz.rental.utility.response.Response;

@Service
public class CompanyRegistrationServiceImpl implements CompanyRegistrationService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private Environment environment;

	@Autowired
	private LoginRepository loginRepository;

	public CompanyRegistrationServiceImpl() {
		logger.info("invoking " + this.getClass().getSimpleName());
	}

	@Override
	public Response companyRegistration(CompanyRegistrationDTO registrationDTO) {
		logger.info("invoking CompanyRegistrationServiceImpl.companyRegistration()");
		List<CompanyLoginEntity> companyLoginEntity = null;
		companyLoginEntity = loginRepository.findAllByEmailId(registrationDTO.getEmailId());
		logger.info("getting data based on mail id ");
		if (companyLoginEntity != null) {
			logger.info("mail id not found ");
			CompanyLoginEntity loginEntity = new CompanyLoginEntity();
			logger.info("checking password and conform password both are same ");
			if (registrationDTO.getPassword().equals(registrationDTO.getCnfPassword())) {
				logger.info("password and conform password both are same");
				BeanUtils.copyProperties(registrationDTO, loginEntity);
				logger.info("copying properties from dto to entity");
				logger.info("saving data into databse");
				loginRepository.save(loginEntity);
				logger.info("data saved to database ");
				logger.info("registration successfully");
				return new Response(environment.getProperty("USER_REGISTERD"),
						environment.getProperty("SERVER_CODE_SUCCESS"), loginEntity);
			} else {
				return new Response(environment.getProperty("INVALID_PASSWORD"),
						environment.getProperty("SERVER_CODE_ERROR"));
			}
		} else {
			return new Response(environment.getProperty("COMPANY_FOUND"),
					environment.getProperty("SERVER_CODE_ERROR"));
		}
	}

}
