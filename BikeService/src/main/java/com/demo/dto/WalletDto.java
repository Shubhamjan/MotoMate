package com.demo.dto;

import com.demo.entities.User;

public class WalletDto {

	private Long id;

	private Double balance;

	private String firstName;
	
	private String lastName;
	
	private String number;

	public WalletDto() {
		super();
	}

	

	public WalletDto(Long id, Double balance, String firstName, String lastName, String number) {
		super();
		this.id = id;
		this.balance = balance;
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
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



	public String getNumber() {
		return number;
	}



	public void setNumber(String number) {
		this.number = number;
	}



	@Override
	public String toString() {
		return "WalletDto [id=" + id + ", balance=" + balance + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", number=" + number + "]";
	}

	
}
