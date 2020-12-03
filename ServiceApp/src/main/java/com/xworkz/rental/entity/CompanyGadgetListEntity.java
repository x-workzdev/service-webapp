package com.xworkz.rental.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "CompanyGadgetList")
@ToString
public class CompanyGadgetListEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String companyName;
	private String model;
	private String serialNo;
	private String mcType;
	private Date dateOfAssigne;
	private String status;//assigned/returned

	public CompanyGadgetListEntity() {
		System.out.println();
	}
}
