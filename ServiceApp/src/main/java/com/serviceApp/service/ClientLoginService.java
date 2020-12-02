package com.serviceApp.service;

import java.util.List;

import com.serviceApp.dto.ClientComplainDTO;
import com.serviceApp.dto.LoginDTO;
import com.serviceApp.entity.ClientComplainEntity;
import com.serviceApp.entity.CompanyGadgetListEntity;
import com.serviceApp.utility.response.Response;

public interface ClientLoginService {

	public Response login(LoginDTO clientLoginDTO);
	
	public List<CompanyGadgetListEntity	> getListOfGadgets(String companyName);
	
	public Response createTicket(ClientComplainDTO clientComplainDTO);

	List<ClientComplainEntity> veiwTicketsByCompanyName(String companyName , String emailId );
}
