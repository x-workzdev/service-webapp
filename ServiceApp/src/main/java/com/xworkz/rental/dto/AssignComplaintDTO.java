package com.xworkz.rental.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AssignComplaintDTO {
	
	private String complaintId;
	private String complaintStatus;
	private String engineerEmail;
	private String adminComment;
	private String engineerComment;
	Logger logger = LoggerFactory.getLogger(getClass());
	public AssignComplaintDTO() {
		logger.info("invoking "+this.getClass().getSimpleName());
	}	
}