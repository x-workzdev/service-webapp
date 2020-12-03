package com.xworkz.rental.service;

import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.xworkz.rental.dto.CompanyGadgetsDTO;
import com.xworkz.rental.entity.CompanyGadgetListEntity;
import com.xworkz.rental.entity.RegistrationEntity;
import com.xworkz.rental.repository.CompanyGadgetRepository;
import com.xworkz.rental.repository.RegistrationRepository;
import com.xworkz.rental.utility.response.Response;

@Service
public class CompanyGadgetsServiceImpl implements CompanyGadgetsService {

	@Autowired
	private Environment environment;

	@Autowired
	private RegistrationRepository registrationRepository;

	@Autowired
	private CompanyGadgetRepository companyGadgetRepository;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public CompanyGadgetsServiceImpl() {
		logger.info("invoking ---------" + this.getClass().getSimpleName());
	}

	@Override
	public Response addGadgets(@Valid CompanyGadgetsDTO gadgetsDTO) {
		logger.info("invoking CompanyGadgetsServiceImpl addGadgets()");
		RegistrationEntity entity = null;
		CompanyGadgetListEntity listEntity = new CompanyGadgetListEntity();
		logger.info(""+gadgetsDTO);
		try {
			entity = registrationRepository.findByCompanyName(gadgetsDTO.getCompanyName());
			logger.info("checking for company details");
			if (entity != null) {
				logger.info("Company found");
				BeanUtils.copyProperties(gadgetsDTO, listEntity);
				logger.info("copy properteies from dto to entity");
				listEntity.setDateOfAssigne(new Date());
				logger.info("Setting todays date");
				listEntity = companyGadgetRepository.save(listEntity);
				logger.info("gadgets list added successfully");
				return new Response(environment.getProperty("GADGETS_ADDE"), environment.getProperty("SERVER_CODE_SUCCESS"), listEntity);
			}else {
				logger.info("Company not found");
				return new Response(environment.getProperty("CLIENT_NOT_FOUND"), environment.getProperty("SERVER_CODE_ERROR"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Response(environment.getProperty("CLIENT_NOT_FOUND"), environment.getProperty("SERVER_CODE_ERROR"));
		}
	}
}
