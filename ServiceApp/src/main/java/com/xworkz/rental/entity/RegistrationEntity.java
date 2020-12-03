package com.xworkz.rental.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Registration")
@JsonSerialize
public class RegistrationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String companyName;
	private String customerName;
	private String emailId;
	private long contactNumber;
	private String address;
	@JsonIgnore
	private String password;
	private Date date;
	private String auditStatus;// insert/delete

	@OneToMany(mappedBy = "registration", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnoreProperties("registration")
	Set<ClientComplainEntity> clientComplainEntities;

	public RegistrationEntity() {
		System.out.println("invoking " + this.getClass().getSimpleName());
	}

	@Override
	public String toString() {
		return "companyName=" + companyName + ",\n customerName=" + customerName + ",\n emailId=" + emailId
				+ ",\n contactNumber=" + contactNumber + ",\n address=" + address + ",\n password=" + password
				+ ",\n date=" + date + ",\n auditStatus=" + auditStatus;
	}
}