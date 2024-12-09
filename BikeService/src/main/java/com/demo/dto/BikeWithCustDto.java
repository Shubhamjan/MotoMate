package com.demo.dto;

public class BikeWithCustDto {
	
	private String model;
    private String registrationNumber;
    private String company;
    private String firstName;
	private String lastName;
	private String mobileNumber;
	
	public BikeWithCustDto(String model, String registrationNumber, String company, String firstName, String lastName,
			String mobileNumber) {
		super();
		this.model = model;
		this.registrationNumber = registrationNumber;
		this.company = company;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
	}

	public BikeWithCustDto() {
		super();
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "BikeWithCustDto [model=" + model + ", registrationNumber=" + registrationNumber + ", company=" + company
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", mobileNumber=" + mobileNumber + "]";
	}
}
