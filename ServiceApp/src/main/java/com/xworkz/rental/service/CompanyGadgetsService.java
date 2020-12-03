package com.xworkz.rental.service;

import javax.validation.Valid;

import com.xworkz.rental.dto.CompanyGadgetsDTO;
import com.xworkz.rental.utility.response.Response;

public interface CompanyGadgetsService {

	Response addGadgets(@Valid CompanyGadgetsDTO gadgetsDTO);

}
