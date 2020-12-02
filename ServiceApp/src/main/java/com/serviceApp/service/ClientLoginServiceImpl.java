package com.serviceApp.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.serviceApp.dto.ClientComplainDTO;
import com.serviceApp.dto.LoginDTO;
import com.serviceApp.entity.ClientComplainEntity;
import com.serviceApp.entity.CompanyGadgetListEntity;
import com.serviceApp.entity.CompanyLoginEntity;
import com.serviceApp.entity.RegistrationEntity;
import com.serviceApp.repository.CompanyGadgetRepository;
import com.serviceApp.repository.ComplainRepository;
import com.serviceApp.repository.LoginRepository;
import com.serviceApp.repository.RegistrationRepository;
import com.serviceApp.utility.mailSender.JMS;
import com.serviceApp.utility.passwordGenerater.AutoGenerateString;
import com.serviceApp.utility.response.Response;

@Service
@PropertySource(value = { "messages.properties" })
public class ClientLoginServiceImpl implements ClientLoginService {

	@Autowired
	private RegistrationRepository registrationRepository;

	@Autowired
	private CompanyGadgetRepository companyGadgetRepository;

	@Autowired
	private ComplainRepository complainRepository;

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private Environment environment;

	@Autowired
	private JMS jms;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public ClientLoginServiceImpl() {
		logger.info("invoking " + this.getClass().getSimpleName());
	}

	@Override
	public Response login(LoginDTO loginDTO) {
		logger.info("invoking ClientLoginServiceImpl.login();");
		RegistrationEntity registrationEntity = registrationRepository.findByEmailId(loginDTO.getEmailId());
		logger.info("Getting data from registration table");
		if (registrationEntity != null) {
			logger.info("registrationEntity not null " + registrationEntity);
			if (loginDTO.getPassword().equals(registrationEntity.getPassword())) {
				logger.info("emaailId and password match.");
				logger.info("return login success");
				registrationEntity.setPassword("");
				return new Response(environment.getProperty("LOGIN_SUCCESS"),
						environment.getProperty("SERVER_CODE_SUCCESS"), registrationEntity);
			}
			logger.info("password does't match.  Returning password does't match");
			return new Response(environment.getProperty("INVALID_PASSWORD"),
					environment.getProperty("SERVER_CODE_ERROR"));
		} else {
			logger.info("email not found ");
			return new Response(environment.getProperty("INVALID_CREDENTIALS"),
					environment.getProperty("SERVER_CODE_ERROR"));
		}
	}

	@Override
	public List<CompanyGadgetListEntity> getListOfGadgets(String CompanyName) {
		logger.info("invoking getListOfGadgets()");
		List<CompanyGadgetListEntity> companyGadgetListEntities = companyGadgetRepository
				.findAllByCompanyName(CompanyName);
		logger.info("Returning list of gadgets");
		return companyGadgetListEntities;
	}

	@Override
	public List<ClientComplainEntity> veiwTicketsByCompanyName(String companyName, String emailId) {
		logger.info("invoking veiwTicketsByCompanyName()");
		List<ClientComplainEntity> clientComplainEntity = complainRepository.findAllByCompanyName(companyName, emailId);

		logger.info("returning response");
		return clientComplainEntity;
	}

	@Override
	public Response createTicket(ClientComplainDTO clientComplainDTO) {
		// change methos by emailid and company name
		logger.info("invoked createTicket() {0}", clientComplainDTO);
		RegistrationEntity entity = registrationRepository.findByCompanyName(clientComplainDTO.getCompanyName());
		logger.info("" + entity);
		ClientComplainEntity created = null;
		logger.info("getting registration entity");
		if (entity != null) {
			logger.info("Entity is not null");
			ClientComplainEntity clientComplainEntity = new ClientComplainEntity();
			BeanUtils.copyProperties(clientComplainDTO, clientComplainEntity);
			logger.info("Copyed properties from dto to entity");
			clientComplainEntity.setComplaintId(AutoGenerateString.autoGenerateTicket());
			logger.info("Ticket generated");
			clientComplainEntity.setRegistration(entity);
			logger.info("Setted foreginkey ");
			clientComplainEntity.setDate(new Date());
			logger.info("Setted current date ");
			clientComplainEntity.setComplaintStatus("PENDING");
			logger.info("Setted status  ");
			created = complainRepository.save(clientComplainEntity);
			logger.info("saved clientComplainEntity()");
			CompanyLoginEntity loginEntity = loginRepository.findByRole("ADMIN");
			logger.debug("get all the email id base on role = ADMIN");
			jms.sendMail(loginEntity.getEmailId(), "Complaint raised", "complaint raised \n " + clientComplainEntity);
			logger.debug("sending the mail");
		}
		if (created != null) {
			return new Response(environment.getProperty("TICKET_CREATED"),
					environment.getProperty("SERVER_CODE_SUCCESS"), created);
		} else {
			return new Response(environment.getProperty("FAIL_TO_CREATE_TICKET"),
					environment.getProperty("SERVER_CODE_ERROR"));
		}

	}
}