package com.xworkz.rental.service;

import java.util.List;

import com.xworkz.rental.dto.RegistrationDTO;
import com.xworkz.rental.entity.RegistrationEntity;
import com.xworkz.rental.utility.response.Response;

public interface RegistrationService {
	public Response clientRegistration(RegistrationDTO registrationDTO);

	public List<RegistrationEntity> getAllClients();

	public Response deleteClient(String companyName);
}
