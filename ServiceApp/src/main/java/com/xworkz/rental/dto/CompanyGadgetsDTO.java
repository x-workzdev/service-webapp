package com.xworkz.rental.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CompanyGadgetsDTO {

	private String companyName;
	private String model;
	private String serialNo;
	private String mcType;
	private String status;
}
