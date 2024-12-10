package com.demo.dto;

import java.util.List;

import com.demo.entities.Bike;
import com.demo.entities.Booking;
import com.demo.entities.Wallet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

public class UserDto {

	private Long id;
	private String firstName;
	private String lastName;
	private String role; // Either "ADMIN" or "CUSTOMER"
	private String mobileNumber;
	private String address;
	private String email;
	private String password;
	private String gender;

	public UserDto() {
		super();
	}

	public UserDto(Long id, String password, String role, String mobileNumber, String address, String email,
			String firstName, String lastName, String gender) {
		super();
		this.id = id;
		this.password = password;
		this.role = role;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", password=" + password + ", role=" + role + ", mobileNumber=" + mobileNumber
				+ ", address=" + address + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + "]";
	}

}
