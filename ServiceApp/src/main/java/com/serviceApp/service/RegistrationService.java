package com.serviceApp.service;

import java.util.List;

import com.serviceApp.dto.RegistrationDTO;
import com.serviceApp.entity.RegistrationEntity;
import com.serviceApp.utility.response.Response;

public interface RegistrationService {
	public Response clientRegistration(RegistrationDTO registrationDTO);

	public List<RegistrationEntity> getAllClients();

	public Response deleteClient(String companyName);
}
