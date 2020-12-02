package com.serviceApp.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

public class RegistrationDTO {

	@NotNull
	private String customerName;
	@NotNull
	private String companyName;
	@NotNull
	@Email
	private String emailId;
	@NotNull
	private long contactNumber;
	@NotNull
	private String address;

	//Logger logger = LoggerFactory.getLogger(getClass());

	public RegistrationDTO() {
		//logger.info("invoking " + this.getClass().getSimpleName());
	}

	@Override
	public String toString() {
		return "RegistrationDTO [customerName=" + customerName + ", companyName=" + companyName + ", emailId=" + emailId
				+ ", contactNumber=" + contactNumber + ", address=" + address + "]";
	}

}
