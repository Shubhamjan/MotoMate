package com.demo.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//    private String username;
	private String firstName;
	private String lastName;
	private String gender;
	private String password;
	private String role; // Either "ADMIN" or "CUSTOMER"
	private String mobileNumber;
	private String address;
	private String email;

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

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Wallet wallet;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Bike> bikes;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Booking> bookings;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Payment> payment;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private PasswordResetToken passwordResetToken;

	public User() {
		super();
	}

	public User(Long id, String firstName, String lastName, String gender, String password, String role,
			String mobileNumber, String address, String email, Wallet wallet, List<Bike> bikes, List<Booking> bookings,
			List<Payment> payment, PasswordResetToken passwordResetToken) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.password = password;
		this.role = role;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.email = email;
		this.wallet = wallet;
		this.bikes = bikes;
		this.bookings = bookings;
		this.payment = payment;
		this.passwordResetToken = passwordResetToken;
	}

	public PasswordResetToken getPasswordResetToken() {
		return passwordResetToken;
	}

	public void setPasswordResetToken(PasswordResetToken passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
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

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public List<Bike> getBikes() {
		return bikes;
	}

	public void setBikes(List<Bike> bikes) {
		this.bikes = bikes;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
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

	public List<Payment> getPayment() {
		return payment;
	}

	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", password=" + password + ", role=" + role + ", mobileNumber=" + mobileNumber + ", address="
				+ address + ", email=" + email + ", wallet=" + wallet + ", bikes=" + bikes + ", bookings=" + bookings
				+ ", payment=" + payment + ", passwordResetToken=" + passwordResetToken + "]";
	}

}
