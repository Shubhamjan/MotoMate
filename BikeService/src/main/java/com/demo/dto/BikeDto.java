package com.demo.dto;

import com.demo.entities.User;

public class BikeDto {

	private Long id;

	private String model;
	private String registrationNumber;
	private String company;
	
	public BikeDto() {
		super();
	}
	
	public BikeDto(Long id, String model, String registrationNumber, String company) {
		super();
		this.id = id;
		this.model = model;
		this.registrationNumber = registrationNumber;
		this.company = company;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "BikeDto [id=" + id + ", model=" + model + ", registrationNumber=" + registrationNumber + ", company="
				+ company + "]";
	}
	




	
	
}
