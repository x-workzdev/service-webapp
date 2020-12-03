package com.xworkz.rental.service;

import java.util.List;

import com.xworkz.rental.dto.ClientComplainDTO;
import com.xworkz.rental.dto.LoginDTO;
import com.xworkz.rental.entity.ClientComplainEntity;
import com.xworkz.rental.entity.CompanyGadgetListEntity;
import com.xworkz.rental.utility.response.Response;

public interface ClientLoginService {

	public Response login(LoginDTO clientLoginDTO);
	
	public List<CompanyGadgetListEntity	> getListOfGadgets(String companyName);
	
	public Response createTicket(ClientComplainDTO clientComplainDTO);

	List<ClientComplainEntity> veiwTicketsByCompanyName(String companyName , String emailId );
}
