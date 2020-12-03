package com.xworkz.rental.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

	@NotNull
	@Email
	private String emailId;
	@NotNull
	private String password;

	Logger logger = LoggerFactory.getLogger(getClass());

	public LoginDTO() {
		logger.info("invoking " + this.getClass().getSimpleName());
	}
}
