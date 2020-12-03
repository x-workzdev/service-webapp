package com.xworkz.rental.service;

import java.util.List;

import com.xworkz.rental.dto.AssignComplaintDTO;
import com.xworkz.rental.dto.LoginDTO;
import com.xworkz.rental.entity.ClientComplainEntity;
import com.xworkz.rental.entity.CompanyGadgetListEntity;
import com.xworkz.rental.entity.CompanyLoginEntity;
import com.xworkz.rental.utility.response.Response;

public interface CompanyLoginService {

	public Response login(LoginDTO loginDTO);
	
	public List<ClientComplainEntity> veiwAllTicketas();

	public List<CompanyGadgetListEntity> veiwAllGadgets();

	public List<CompanyLoginEntity> viewAllEngineer();

	public Response assignComplaint(AssignComplaintDTO assignComplaintDTO);

	public List<ClientComplainEntity> veiwAllTicketas(String emailId);
}
