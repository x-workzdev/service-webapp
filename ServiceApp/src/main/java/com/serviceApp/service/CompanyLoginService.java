package com.serviceApp.service;

import java.util.List;

import com.serviceApp.dto.AssignComplaintDTO;
import com.serviceApp.dto.LoginDTO;
import com.serviceApp.entity.ClientComplainEntity;
import com.serviceApp.entity.CompanyGadgetListEntity;
import com.serviceApp.entity.CompanyLoginEntity;
import com.serviceApp.utility.response.Response;

public interface CompanyLoginService {

	public Response login(LoginDTO loginDTO);
	
	public List<ClientComplainEntity> veiwAllTicketas();

	public List<CompanyGadgetListEntity> veiwAllGadgets();

	public List<CompanyLoginEntity> viewAllEngineer();

	public Response assignComplaint(AssignComplaintDTO assignComplaintDTO);

	public List<ClientComplainEntity> veiwAllTicketas(String emailId);
}
