package com.demo.entities;

import jakarta.persistence.*;

@Entity
public class Bike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    @Column(unique=true)
    private String registrationNumber;
    private String company; // New attribute for the bike's company

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

	public Bike() {
		super();
	}

	public Bike(Long id, String model, String registrationNumber, String company, User user) {
		super();
		this.id = id;
		this.model = model;
		this.registrationNumber = registrationNumber;
		this.company = company;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Bike [id=" + id + ", model=" + model + ", registrationNumber=" + registrationNumber + ", company="
				+ company + ", user=" + user + "]";
	}

  
}

