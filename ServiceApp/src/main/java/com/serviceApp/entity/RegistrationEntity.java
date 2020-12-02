package com.serviceApp.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;

@Entity
/*
 * @Getter
 * 
 * @Setter
 */
@Table(name = "Registration")
@JsonSerialize
public class RegistrationEntity {

	@Id
	private String companyName;
	private String customerName;
	private String emailId;
	private long contactNumber;
	private String address;
	private String password;
	private Date date;
	private String auditStatus;// insert/delete

	@OneToMany(mappedBy = "registration", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnoreProperties("registration")
	Set<ClientComplainEntity> clientComplainEntities;

	public RegistrationEntity() {
		System.out.println("invoking " + this.getClass().getSimpleName());
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Set<ClientComplainEntity> getClientComplainEntities() {
		return clientComplainEntities;
	}

	public void setClientComplainEntities(Set<ClientComplainEntity> clientComplainEntities) {
		this.clientComplainEntities = clientComplainEntities;
	}

}
