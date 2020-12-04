package com.xworkz.rental.entity;

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
	@JoinColumn(name = "id")
	@JsonIgnoreProperties("clientComplainEntities")
	RegistrationEntity registration;

	public ClientComplainEntity() {
		System.out.println("invoking " + this.getClass().getSimpleName());
	}

	@Override
	public String toString() {
		return "complaintId=" + complaintId + ",\n model=" + model + ",\n serialNo=" + serialNo
				+ ",\n mcType=" + mcType + ",\n problem=" + problem + ",\n complaintStatus=" + complaintStatus + ",\n date="
				+ date + ",\n engineerEmail=" + engineerEmail + ",\n clientComment=" + clientComment + ",\n adminComment="
				+ adminComment + ",\n engineerComment=" + engineerComment;
	}
	
}