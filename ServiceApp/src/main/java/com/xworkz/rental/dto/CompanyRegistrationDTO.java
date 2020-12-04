package com.xworkz.rental.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CompanyRegistrationDTO {

	private String fullName;
	private String role;
	private String emailId;
	private String password;
	private String cnfPassword;
	

	public CompanyRegistrationDTO() {
		System.out.println("invoking " + this.getClass().getSimpleName());
	}
}


