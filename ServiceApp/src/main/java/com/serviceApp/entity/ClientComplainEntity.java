package com.serviceApp.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "ClientComplaint")
@JsonSerialize
public class ClientComplainEntity {

	@Id
	private String complaintId;
	private String model;
	private String serialNo;
	private String mcType;
	private String problem;
	private String complaintStatus;
	private Date date;
	private String engineerEmail;
	private String clientComment;
	private String adminComment;
	private String engineerComment;
	@ManyToOne
	@JoinColumn(name = "companyName")
	@JsonIgnoreProperties("clientComplainEntities")
	RegistrationEntity registration;

	public ClientComplainEntity() {
		System.out.println("invoking " + this.getClass().getSimpleName());
	}
}