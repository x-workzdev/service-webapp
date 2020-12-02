package com.serviceApp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.serviceApp.dto.AssignComplaintDTO;
import com.serviceApp.dto.LoginDTO;
import com.serviceApp.entity.ClientComplainEntity;
import com.serviceApp.entity.CompanyGadgetListEntity;
import com.serviceApp.entity.CompanyLoginEntity;
import com.serviceApp.repository.CompanyGadgetRepository;
import com.serviceApp.repository.ComplainRepository;
import com.serviceApp.repository.LoginRepository;
import com.serviceApp.utility.mailSender.JMS;
import com.serviceApp.utility.response.Response;

@Service
@PropertySource(value = { "messages.properties" })
public class CompanyLoginServiceImpl implements CompanyLoginService {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private ComplainRepository complainRepository;

	@Autowired
	private CompanyGadgetRepository companyGadgetRepository;

	@Autowired
	private Environment environment;
	
	@Autowired
	private JMS jms;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public CompanyLoginServiceImpl() {
		logger.info("invoking " + this.getClass().getSimpleName());
	}

	@Override
	public Response login(LoginDTO loginDTO) {
		logger.info("invokiing companyLoginServiceImpl.loginDTO");
		CompanyLoginEntity companyLoginEntity = loginRepository.findByEmailId(loginDTO.getEmailId());
		logger.info("serching for credantials based on email id");
		if (companyLoginEntity != null) {
			logger.info("credantials found");
			if (loginDTO.getPassword().equals(companyLoginEntity.getPassword())) {
				logger.info("email and password mathch with credantials");
				return new Response(environment.getProperty("LOGIN_SUCCESS"),
						environment.getProperty("SERVER_CODE_SUCCESS"), companyLoginEntity);
			}
			logger.info("password does't match");
			return new Response(environment.getProperty("INVALID_PASSWORD"),
					environment.getProperty("SERVER_CODE_ERROR"));
		} else {
			logger.info("email id not found in databased");
			return new Response(environment.getProperty("INVALID_CREDENTIALS"),
					environment.getProperty("SERVER_CODE_ERROR"));
		}
	}

	@Override
	public List<ClientComplainEntity> veiwAllTicketas() {
		logger.info("invoking companyLoginServiceImpl.veiwAllTicketas()");
		List<ClientComplainEntity> clientComplainEntity = complainRepository.findAll();
		logger.info("returning response");
		return clientComplainEntity;
	}

	@Override
	public List<ClientComplainEntity> veiwAllTicketas(String emailId) {
		logger.info("invoking companyLoginServiceImpl.veiwAllTicketas()");
		List<ClientComplainEntity> clientComplainEntity = complainRepository.findAllByEngineerEmail(emailId);
		logger.info("returning response");
		return clientComplainEntity;
	}

	@Override
	public List<CompanyGadgetListEntity> veiwAllGadgets() {
		logger.info("invoking companyLoginServiceImpl.veiwAllGadgets()");
		List<CompanyGadgetListEntity> gadgetListEntities = companyGadgetRepository.findAll();
		logger.info("returning response");
		return gadgetListEntities;
	}

	@Override
	public List<CompanyLoginEntity> viewAllEngineer() {
		logger.info("invoking companyLoginServiceImpl.viewAllEngineer()");
		List<CompanyLoginEntity> response = loginRepository.findAllByRole("ENGINEER");
		logger.info("returning response");
		return response;
	}

	@Override
	public Response assignComplaint(AssignComplaintDTO assignComplaintDTO) {
		String msg =null;
		logger.info("invoking companyLoginServiceImpl.assignComplaint()");
		ClientComplainEntity complainEntity = complainRepository.findById(assignComplaintDTO.getComplaintId()).get();
		logger.info("searching for complaint based on complaintId");
		if (complainEntity != null) {
			logger.info("complaint found");
			if (assignComplaintDTO.getComplaintStatus() != null) {
				logger.info("updating complaint status");
				complainEntity.setComplaintStatus(assignComplaintDTO.getComplaintStatus());
			}
			if (assignComplaintDTO.getEngineerEmail() != null) {
				logger.info("updating engineerEmsilId");
				complainEntity.setEngineerEmail(assignComplaintDTO.getEngineerEmail());
			}
			if (assignComplaintDTO.getAdminComment() != null) {
				logger.info("updating admincomment");
				complainEntity.setAdminComment(assignComplaintDTO.getAdminComment());
			}
			if (assignComplaintDTO.getEngineerComment() != null) {
				logger.info("updating engineerCimment "+ assignComplaintDTO.getEngineerComment());
				complainEntity.setEngineerComment(assignComplaintDTO.getEngineerComment());
			}
			complainRepository.save(complainEntity);
			logger.info("sending mail to "+complainEntity.getEngineerEmail());
			msg="complaintId : "+ complainEntity.getComplaintId()+"\n mcType : "+complainEntity.getMcType()+"\n model : "+complainEntity.getModel()+"\n Problem : "+complainEntity.getProblem()+"\n serialNo : "+complainEntity.getSerialNo();
			jms.sendMail(complainEntity.getEngineerEmail(), "Complain Assigned ", msg);
			logger.info("complaint successfully assigned to "+complainEntity.getEngineerEmail());
			return new Response(environment.getProperty("UPDATED"), environment.getProperty("SERVER_CODE_SUCCESS"),
					complainEntity);
		} else {
			logger.info("Complaint not found ");
			return new Response(environment.getProperty("COMPLAINT_NOT_FOUND"),
					environment.getProperty("SERVER_CODE_ERROR"));
		}
	}

}
