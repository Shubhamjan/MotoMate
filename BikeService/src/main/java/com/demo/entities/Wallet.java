package com.demo.entities;

import jakarta.persistence.*;

@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double balance=0.00;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

	public Wallet() {
		super();
	}

	public Wallet(Long id, Double balance, User user) {
		super();
		this.id = id;
		this.balance = balance;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Wallet [id=" + id + ", balance=" + balance + ", user=" + user + "]";
	}

    // Method to add and deduct funds
//    public void addFunds(double amount) {
//        this.balance += amount;
//    }
//
//    public boolean deductFunds(double amount) {
//        if (this.balance >= amount) {
//            this.balance -= amount;
//            return true;
//        }
//        return false;
//    }

    // Getters and setters
}
