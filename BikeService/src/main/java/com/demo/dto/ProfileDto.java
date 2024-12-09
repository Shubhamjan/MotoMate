package com.demo.dto;

public class ProfileDto {

	private Long id;
	private String firstName;
	private String lastName;
	private String role; // Either "ADMIN" or "CUSTOMER"
	private String mobileNumber;
	private String address;
	private String email;
	private String gender;

	public ProfileDto() {
		super();
	}

	public ProfileDto(Long id, String firstName, String lastName, String role, String mobileNumber, String address,
			String email, String gender) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.email = email;
		this.gender = gender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "ProfileDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + role
				+ ", mobileNumber=" + mobileNumber + ", address=" + address + ", email=" + email + ", gender=" + gender
				+ "]";
	}
}
